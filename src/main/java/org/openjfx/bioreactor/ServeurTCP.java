package org.openjfx.bioreactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Représente un serveur TCP, qui écoute sur un numéro de port
 *
 */

public class ServeurTCP extends Thread {

    private ArrayList<String> hardDrive=new ArrayList<>();  // hard drive for storing data from tcp serveur

    private static int nbConnexions = 0;

    /** Maximum de connexions client autorisées */
    private int maxConnexions;

    /** Socket du serveur client */
    private Socket clientSocket;

    /** Numéro de port d'ecoute */
    private int numeroPort;
    private PrintStream socOut;
    private BufferedReader socIn;

    private boolean hasClient=false;

    public ServeurTCP(int numeroPort, int maxConnexions){
        this.numeroPort=numeroPort;
        this.maxConnexions=maxConnexions;
    }

    @Override
    public void run() {
        String msg_client=null;
        String chain_client="";
        int index_request=0;
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
                hasClient=true;
                System.out.println("Nb automates : " + nbConnexions);

                // test to see if request of state from client
                msg_client=socIn.readLine();
                if(msg_client!=null){ // message receive from client
                    chain_client+=msg_client;
                    msg_client=socIn.readLine();
                }
                if(chain_client=="state_bio_reactor"){
                    this.send(this.hardDrive.get(index_request));   //sending data from harddrive to tcp clients
                    index_request++;
                }
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

    public void saveData(String incoming){
        /**
         * saves the data in the hard drive
         */
        this.hardDrive.add(incoming);
    }

    public void send(String msg){

        System.out.println("Requete client: "+msg);
        socOut.println(msg);
        socOut.flush();

    }

    @Override
    public String toString(){
        return "[ServeurTCP] Port : " + numeroPort+" Nombre de connexions"+nbConnexions+" Connecté au client"+clientSocket;
    }

    public ArrayList<String> getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(ArrayList<String> hardDrive) {
        this.hardDrive = hardDrive;
    }
}
