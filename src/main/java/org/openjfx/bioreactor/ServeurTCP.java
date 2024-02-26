/**
 * ServeurTCP est une classe qui gère le serveur TCP.
 * Elle possède une méthode startServer qui écoute en permanence les connexions entrantes et traite
 * les données à l'aide de la classe Bioreacteur. La méthode main dans
 * TCPServer est utilisée pour démarrer le serveur sur le port spécifié.
 *
 *
 * */


import org.openjfx.bioreactor.BioReactor;

import java.io.*;
import java.net.*;

public class ServeurTCP {

    private ServerSocket serverSocket;

    public ServeurTCP(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Serveur en attente de connexions sur le port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis " + clientSocket.getInetAddress());

                // Créer une instance de la classe de traitement des données
                BioReactor bioreacteur = new BioReactor();

                // Lire les données avec la classe de traitement des données
                String data = BioReactor.readingFile();

                // Envoyer les données au client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println(data);

                // Fermer les flux et la connexion avec le client
                writer.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServeurTCP tcpServer = new ServeurTCP(12345);
        tcpServer.startServer();
    }
}