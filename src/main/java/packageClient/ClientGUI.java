package packageClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class ClientGUI implements PropertyChangeListener {
    private JFrame frame;
    private JComboBox<String> parametreComboBox;
    private JLabel valeurLabel;
    private Timer timer;

    private ComputerClient computerClient;

    public ClientGUI(ComputerClient computerClient) {

        this.computerClient=computerClient;


        frame = new JFrame("Interface Utilisateur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        String[] parametres = {"Température mesurée", "Oxy mesurée", "Ph mesuré","Température de consigne","Température commandée"};
        parametreComboBox = new JComboBox<>(parametres);
        valeurLabel = new JLabel();

        panel.add(parametreComboBox);
        panel.add(valeurLabel);

        frame.getContentPane().add(panel);

        //deux boutons radio pour sélectionner le mode de visualisation
        JRadioButton toutVisualiserButton = new JRadioButton("Tout visualiser");
        JRadioButton toutesLes30SecondesButton = new JRadioButton("Visualiser toutes les 30 secondes");

        ButtonGroup group = new ButtonGroup();
        group.add(toutVisualiserButton);
        group.add(toutesLes30SecondesButton);

        JPanel modePanel = new JPanel();
        modePanel.add(toutVisualiserButton);
        modePanel.add(toutesLes30SecondesButton);

        frame.getContentPane().add(modePanel, BorderLayout.SOUTH);

        // écouteur d'événements à la ComboBox
        parametreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parametreSelectionne = (String) parametreComboBox.getSelectedItem();
                updateParameterValue(parametreSelectionne);
            }
        });
        //pour le mode "Visualiser toutes les 30 secondes"
        toutesLes30SecondesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toutesLes30SecondesButton.isSelected()) {
                    computerClient.setStrategyRequest("RequestTemporize");

                } else {
                    computerClient.setStrategyRequest("RequestComplete");
                }
            }
        });

        // pattern observateur
        computerClient.getPropertyChangeSupport().addPropertyChangeListener(this);

        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(ClientGUI::new);
//    }

    // Méthode pour mettre à jour la valeur du paramètre sélectionné
    private void updateParameterValue(String parameter) {
        // Demander à ComputerClient de mettre à jour la valeur
        String value = getValueForParameter(parameter);
        // Mettre à jour l'interface utilisateur avec la nouvelle valeur
        valeurLabel.setText(parameter + " : " + value);
    }

    private String getValueForParameter(String parameter) {
        /**
         * Gets the values of the bioreactor from the computerClient harddrive
         */
        String donnees = computerClient.getHardDrive().getLast();
        String[] parameters=donnees.split(",");

        switch (parameter) {
            case "Température mesurée":
                return (parameters.length > 0) ? parameters[1] : "N/A";
            case "Oxy mesurée":
                return (parameters.length > 1) ? parameters[2] : "N/A";
            case "Ph mesuré":
                return (parameters.length > 2) ? parameters[3] : "N/A";
            case "Température de consigne":
                return (parameters.length > 3) ? parameters[4] : "N/A";
            case "Température commandée":
                return (parameters.length > 4) ? parameters[5] : "N/A";
            default:
                return "N/A";
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("hardDrive")){
            updateParameterValue((String) parametreComboBox.getSelectedItem());
        }
    }
}
