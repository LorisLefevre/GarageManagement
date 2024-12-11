package ApplicationWeb;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Serveur {

    public static void main(String[] args) throws IOException {
        // Création du serveur HTTP sur le port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Point de terminaison pour la page de connexion
        server.createContext("/login", new LoginHandler());

        // Point de terminaison pour vehicules.html
        server.createContext("/vehicules.html", new VehiculesHandler());

        // Gestion des fichiers statiques (images, CSS, etc.)
        server.createContext("/static", new StaticFileHandler());

        // Démarrage du serveur
        server.setExecutor(null); // Utilisation du thread par défaut
        server.start();
        System.out.println("Serveur démarré sur http://localhost:8080/login");
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Récupération des données du formulaire
                String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String[] params = requestBody.split("&");
                String username = null;
                String password = null;

                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2) {
                        if (keyValue[0].equals("username")) {
                            username = keyValue[1];
                        } else if (keyValue[0].equals("password")) {
                            password = keyValue[1];
                        }
                    }
                }

                if ("admin".equals(username) && "password".equals(password)) {
                    // Redirection vers vehicules.html
                    exchange.getResponseHeaders().add("Location", "/vehicules.html");
                    exchange.sendResponseHeaders(302, -1); // Code HTTP 302 : redirection
                } else {
                    // Identifiants incorrects
                    String response = "<h1>Identifiants incorrects</h1>";
                    exchange.sendResponseHeaders(401, response.getBytes(StandardCharsets.UTF_8).length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes(StandardCharsets.UTF_8));
                    os.close();
                }
            } else {
                // Formulaire de connexion
                String response = """
                        <html>
                        <body>
                            <form method="POST" action="/login">
                                <label>Username:</label><br>
                                <input type="text" name="username"><br>
                                <label>Password:</label><br>
                                <input type="password" name="password"><br><br>
                                <button type="submit">Login</button>
                            </form>
                        </body>
                        </html>
                        """;
                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        }
    }

    static class VehiculesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Chemin vers le fichier vehicules.html
            Path filePath = Path.of("C:/Users/Loris/IdeaProjects/GarageManagement/Vehicules/vehicules.html");

            if (Files.exists(filePath)) {
                // Lecture du fichier HTML
                String response = Files.readString(filePath, StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            } else {
                // En cas d'erreur, fichier introuvable
                String response = "<h1>Erreur : fichier vehicules.html introuvable</h1>";
                exchange.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        }
    }

    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Chemin racine pour les fichiers statiques
            Path staticDir = Path.of("C:/Users/Loris/IdeaProjects/GarageManagement/Vehicules/");

            // Remplace /static par le chemin relatif du fichier
            String requestedPath = exchange.getRequestURI().getPath().replace("/static", "");

            // Chemin complet du fichier demandé dans les sous-répertoires
            Path filePath = staticDir.resolve(requestedPath);

            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                // Détection du type MIME (par exemple, image/jpeg ou text/css)
                String contentType = Files.probeContentType(filePath);
                exchange.getResponseHeaders().set("Content-Type", contentType);

                // Lecture du fichier et envoi de la réponse
                byte[] fileBytes = Files.readAllBytes(filePath);
                exchange.sendResponseHeaders(200, fileBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(fileBytes);
                os.close();
            } else {
                // Fichier introuvable
                String response = "<h1>Erreur : fichier introuvable</h1>";
                exchange.sendResponseHeaders(404, response.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        }
    }
}
