package org.openjfx.bioreactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


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

    /** Représente un protocole de communication*/
    private IProtocole protocole;

    private IContext context;

    public ServeurTCP(IProtocole protocole, IContext context,int numeroPort, int maxConnexions){
        this.protocole=protocole;
        this.context=context;
        this.numeroPort=numeroPort;
        this.maxConnexions=maxConnexions;
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

                ConnectedClientThread st = new ConnectedClientThread( clientSocket , this );
                st.start();

                System.out.println("Nb automates : " + nbConnexions);

            }
            catch (IOException e) {
                System.out.println("Accept failed: " + serverSocket.getLocalPort() + ", " + e);
                System.exit(1);
            }

        }
        System.out.println("Deja " + nbConnexions + " clients. Maximum autorisé atteint");

        // closing when too many connexions
        try {
            serverSocket.close();
            nbConnexions--;
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }

    @Override
    public String toString(){
        return "[ServeurTCP] Port : " + numeroPort+" Nombre de connexions"+nbConnexions+" Connecté au client"+clientSocket;
    }

    // getters and setters


    public IProtocole getProtocole() {
        return protocole;
    }

    public void setProtocole(IProtocole protocole) {
        this.protocole = protocole;
    }

    public IContext getContext() {
        return context;
    }

    public void setContext(IContext context) {
        this.context = context;
    }
}
