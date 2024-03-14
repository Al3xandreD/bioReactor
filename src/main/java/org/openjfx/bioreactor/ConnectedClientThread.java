package org.openjfx.bioreactor;

import packageClient.ServeurClient;

import java.io.*;
import java.net.*;

public class ConnectedClientThread extends Thread {

    private Socket clientSocket;
    private ServeurTCP myServer;

    public ConnectedClientThread(Socket aClientSocket, ServeurTCP aServer) {
        clientSocket = aClientSocket;
        myServer = aServer;
    }

    public void run() {

        try {
            myServer.getProtocole().execute(clientSocket.getInputStream(), clientSocket.getOutputStream(), );
            System.out.println("Protocol SendSate done");
        } catch (IOException e) {
            System.err.println("[Protocol SendState] Exception : " + e);
            e.printStackTrace();
        }
    }

}