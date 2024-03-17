package org.openjfx.bioreactor;

import java.io.*;

/**
 * Implements the interface IProtocole for sending the data from the computers' serveurs associated to the bioreactor
 * to the clients
 */

public class ProtocoleSendState implements IProtocole{
    @Override
    public void execute(InputStream inputStream, OutputStream outputStream, IContext c) {

        ComputerBio computer=(ComputerBio) c;
        String inputReq;    // input from client

        BufferedReader is = new BufferedReader(new InputStreamReader(inputStream));
        PrintStream os = new PrintStream(outputStream);

        try{
            if ((inputReq = is.readLine()) != null)  {
                System.out.println( " Message recu: " + inputReq );


                if(inputReq.contentEquals("state_bio_reactor")){
                    os.println(computer.getHardDrive().get(computer.getIndexRequest()));
                    os.flush();
                    System.out.println("Protocol SendSate done");
                    computer.setIndexRequest(computer.getIndexRequest()+1);
                }

                os.close();
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
    }
    }
}
