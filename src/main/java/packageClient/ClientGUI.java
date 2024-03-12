package packageClient;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientGUI extends Application {
    private TextField inputField;
    private Button sendButton;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Client GUI");

        inputField = new TextField();
        sendButton = new Button("Send");
        outputArea = new TextArea();

        sendButton.setOnAction(event -> {
            try {
                Socket clientSocket = new Socket("localhost", 12345);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                String message = inputField.getText();
                outToServer.writeUTF(message);

                // Attendre la réponse du serveur
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String serverResponse = inFromServer.readLine();

                // Afficher la réponse dans la zone de texte
                outputArea.appendText("Server: " + serverResponse + "\n");

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(inputField, sendButton, outputArea);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
