package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML private Label titleLabel;
    @FXML private Label loseLabel;

    Stage mainStage;

    @FXML private void onStartButtonClick() {
        mainStage = (Stage) titleLabel.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
            mainStage.setScene(new Scene(root, 1000, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!Constants.FIRST_PLAY) {
            loseLabel.setText("Przegrana po zabiciu " + Constants.SCORE + " wrogów. Spróbuj ponownie");
        }
    }
}
