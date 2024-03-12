package org.openjfx.bioreactor;

import packageClient.ServeurClient;

import java.io.*;
import java.net.*;

public class ConnectedClientThread extends Thread {

    private Socket clientSocket;
    private ServeurTCP myServer;

    public ConnectedClientThread( Socket aClientSocket , ServeurTCP aServer ) {
        clientSocket = aClientSocket;
        myServer = aServer;
    }

    public void run() {
        String inputReq;

        try {
            /* Ouverture des objets de type Stream sur la socket du client r�seau  */
            BufferedReader  is = new BufferedReader ( new InputStreamReader (clientSocket.getInputStream()));
            PrintStream os = new PrintStream(clientSocket.getOutputStream());

            System.out.println( "Client Thread " );

            if ((inputReq = is.readLine()) != null)  {
                System.out.println( " Message recu: " + inputReq );
                String chaines[] = inputReq.split( " " );

                for( int i = 0 ; i < chaines.length ; i++ )
                    System.out.println( " Indice : " + i + " Mot : " + chaines[i] );
            }
            clientSocket.close();
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}