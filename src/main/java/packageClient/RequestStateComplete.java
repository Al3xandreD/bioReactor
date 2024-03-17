package packageClient;

import org.openjfx.bioreactor.IComputer;
import packageClient.IRequestState;

public class RequestStateComplete implements IRequestState {
    @Override
    public void requestS(IComputer computer) {
        int i;
        ComputerClient comp=(ComputerClient) computer;
        for(i=0;i<5812;i++){
            comp.getServeurClient().connecterAuServeur();
            String state = comp.getServeurClient().send("state_bio_reactor"); //request of state
            comp.saveData(state);
            System.out.println("Message recu par le client:" + state);
            comp.getServeurClient().deconnecterDuServeur();
        }
    }
}
