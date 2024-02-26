package org.openjfx.bioreactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Représente un serveur TCP, qui écoute sur un numéro de port
 *
 */

public class ServeurTCP extends Thread {

    private static int nbConnexions = 0;

    /** Maximum de connexions client autorisées */
    private int maxConnexions;

    /** Socket du serveur client */
    private Socket clientSocket;

    /** Numéro de port d'ecoute */
    private int numeroPort;

    public ServeurTCP(int numeroPort, int maxConnexions){
        this.numeroPort=numeroPort;
        this.maxConnexions=maxConnexions;
    }

    @Override
    public String toString(){
        return "[ServeurTCP] Port : " + numeroPort+" Nombre de connexions"+nbConnexions+" Connecté au client"+clientSocket;
    }

    @Override
    public void run() {

        // initialisation
        ServerSocket serverSocket=null; //necessaire car si fail initialisation, pas de serveurSocket pour la while
        try {
            serverSocket = new ServerSocket(numeroPort);
        }
        catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }

        // autorisation des connexions
        while (nbConnexions <= maxConnexions) {
            try {
                System.out.println(" Attente du serveur pour la communication d'un client ");
                clientSocket = serverSocket.accept();
                nbConnexions++;
                System.out.println("Nb automates : " + nbConnexions);
            }
            catch (IOException e) {
                System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
                System.exit(1);
            }
        }
        System.out.println("Deja " + nbConnexions + " clients. Maximum autorisé atteint");

        // fermeture du serveur
        try {
            serverSocket.close();
            nbConnexions--;
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }
}
