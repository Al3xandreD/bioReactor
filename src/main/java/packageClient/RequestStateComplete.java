package packageClient;

import org.openjfx.bioreactor.IComputer;
import packageClient.IRequestState;

import java.util.ArrayList;

public class RequestStateComplete implements IRequestState {
    @Override
    public void requestS(IComputer computer) {
        int i;
        ComputerClient comp=(ComputerClient) computer;
        ArrayList<String> initHardDrive=comp.getHardDrive();    //pattern Observer

        for(i=0;i<5812;i++){
            comp.getServeurClient().connecterAuServeur();
            String state = comp.getServeurClient().send("state_bio_reactor"); //request of state
            comp.saveData(state);
            System.out.println("Message recu par le client:" + state);
            comp.getServeurClient().deconnecterDuServeur();

            comp.getPropertyChangeSupport().firePropertyChange("hardDrive",initHardDrive, comp.getHardDrive());
        }
    }
}
