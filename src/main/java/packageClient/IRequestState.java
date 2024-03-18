package packageClient;

import org.openjfx.bioreactor.IComputer;

/**
 * Pattern Strategy for requesting the state of the bioreactor
 */
public interface IRequestState {
    public void requestS(IComputer computer);
}
