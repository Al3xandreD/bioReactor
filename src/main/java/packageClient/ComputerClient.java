package packageClient;

import org.openjfx.bioreactor.Computer;

import java.util.ArrayList;

/**
 * Simulates a computer to store the data in an hardDrive and send it to the ClientGUI
 */
public class ComputerClient extends Computer {

    private ServeurClient serveurClient;

    public ComputerClient() {
        super();
        this.serveurClient=new ServeurClient("localhost", port);
    }


    // getters and setters
    public ServeurClient getServeurClient() {
        return serveurClient;
    }

    public void setServeurClient(ServeurClient serveurClient) {
        this.serveurClient = serveurClient;
    }

}
