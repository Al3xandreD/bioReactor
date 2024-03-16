package org.openjfx.bioreactor;

import java.io.*;

/**
 * Implements the interface IProtocole for sending the data from the computers' serveurs associated to the bioreactor
 * to the clients
 */

public class ProtocoleSendState implements IProtocole{
    @Override
    public void execute(InputStream inputStream, OutputStream outputStream, IContext c) {

        Computer computer=(Computer) c; //TODO:comprendre
        String inputReq;
        String msg_client;
        String chain_client=null;

        BufferedReader is = new BufferedReader(new InputStreamReader(inputStream));
        PrintStream os = new PrintStream(outputStream);

        try{
            if ((inputReq = is.readLine()) != null)  {
                System.out.println( " Message recu: " + inputReq );
                String chaines[] = inputReq.split( " " );

                for( int i = 0 ; i < chaines.length ; i++ )
                    System.out.println( " Indice : " + i + " Mot : " + chaines[i] );
            }
                msg_client=is.readLine();
                if(msg_client!=null){ // message receive from client
                    chain_client+=msg_client;
                    msg_client=is.readLine();
                    System.out.println("Received from client: "+ chain_client);
                }
            assert chain_client != null;
            if(chain_client.equals("state_bio_reactor")) {
                System.out.println("envoie des données");
                os.println(computer.getHardDrive().get(computer.getIndexRequest()));
                os.flush();//sending data to client
                computer.setIndexRequest(computer.getIndexRequest() + 1);

                os.close();
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
    }
    }
}
