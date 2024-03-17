package org.openjfx.bioreactor;

import java.util.ArrayList;

/** Représente un ordinateur avec un stockage, UI, réseau comm,
 * associé à un bioréacteur */

public abstract class Computer implements IComputer {
    public int port = 6666;

    /** hard drive for storing data from bioreactor */
    public ArrayList<String> hardDrive=new ArrayList<>();

    public Computer(){
        }


    public void saveData (String incoming){
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
