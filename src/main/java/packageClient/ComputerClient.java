package packageClient;

import org.openjfx.bioreactor.Computer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simulates a computer to store the data in an hardDrive and send it to the ClientGUI
 */
public class ComputerClient extends Computer {

    private ServeurClient serveurClient;
    private IRequestState strategyRequest;

    public ComputerClient(IRequestState strategyRequest) {
        super();
        this.serveurClient=new ServeurClient("localhost", port);
        this.strategyRequest=strategyRequest;
    }

    public synchronized void start(){
        /**
         * Starting the request of states
         */
       strategyRequest.requestS(this);
    }

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
