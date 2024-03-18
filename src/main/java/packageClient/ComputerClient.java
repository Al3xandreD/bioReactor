package packageClient;

import org.openjfx.bioreactor.Computer;

import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * Simulates a computer to store the data in an hardDrive and send it to the ClientGUI
 */
public class ComputerClient extends Computer {

    private ServeurClient serveurClient;
    private IRequestState strategyRequest;  //pattern strategy
    private PropertyChangeSupport pcSupport;

    public ComputerClient(IRequestState strategyRequest) {
        super();
        this.serveurClient=new ServeurClient("localhost", port);
        this.strategyRequest=strategyRequest;

        pcSupport=new PropertyChangeSupport(this);
    }

    public synchronized void start(){
        /**
         * Starting the request of states
         */
        strategyRequest.requestS(this);
    }

    // pattern observer
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcSupport;
    }


    //pattern strategy
    public void setStrategyRequest(String typeRequest) {
        if(typeRequest.equals("RequestTemporize")){
            strategyRequest=new RequestStateTemporize();
        }
        else if(typeRequest.equals("RequestComplete")){
            strategyRequest=new RequestStateComplete();
        }
    }


    // getters and setters
    public ServeurClient getServeurClient() {
        return serveurClient;
    }

    public void setServeurClient(ServeurClient serveurClient) {
        this.serveurClient = serveurClient;
    }

}
