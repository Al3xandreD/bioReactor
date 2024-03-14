package org.openjfx.bioreactor;

import java.util.ArrayList;

public class Computer {
    private int port = 6666;
    private ArrayList<String> hardDrive=new ArrayList<>();  // hard drive for storing data from bioreacteur

    public Computer(){

    }
    public void saveData(String incoming){
        /**
         * saves the data in the hard drive
         */
        this.hardDrive.add(incoming);
    }


    //getters and setters
    public ArrayList<String> getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(ArrayList<String> hardDrive) {
        this.hardDrive = hardDrive;
    }
}
