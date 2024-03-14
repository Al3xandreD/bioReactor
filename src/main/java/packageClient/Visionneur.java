package packageClient;

import java.util.ArrayList;

/**
 * Simulates a computer to store the data in an hardDrive and send it to the ClientGUI
 */
public class Visionneur {
    private int port = 6666;
    private ServeurClient serveurClient;
    private ArrayList<String> hardDrive=new ArrayList<>();  // hard drive for storing data from tcp serveur

    public Visionneur() {
        this.serveurClient=new ServeurClient("localhost", port);
    }

    public void saveData(String incoming){
        /**
         * saves the data in the hard drive
         */
        this.hardDrive.add(incoming);
    }




    // getters and setters
    public ServeurClient getServeurClient() {
        return serveurClient;
    }

    public void setServeurClient(ServeurClient serveurClient) {
        this.serveurClient = serveurClient;
    }

    public ArrayList<String> getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(ArrayList<String> hardDrive) {
        this.hardDrive = hardDrive;
    }
}
