package packageClient;

import org.openjfx.bioreactor.IComputer;
import packageClient.IRequestState;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RequestStateTemporize implements IRequestState {
    @Override
    public void requestS(IComputer computer) {
        long delay = 5000;    // parameters for timertask
        long period = 5000;

        ComputerClient comp = (ComputerClient) computer;
        ArrayList<String> initHardDrive=comp.getHardDrive();    // pattern Observer

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                comp.getServeurClient().connecterAuServeur(); //connexion to tcp serveur
                String state = comp.getServeurClient().send("state_bio_reactor"); //request of state
                comp.saveData(state); // save the data
                System.out.println("Message recu par le client:" + state);
                comp.getServeurClient().deconnecterDuServeur();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, delay, period);

        comp.getPropertyChangeSupport().firePropertyChange("hardDrive", initHardDrive, comp.getHardDrive());

    }
}

