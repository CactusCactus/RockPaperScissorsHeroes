package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.LogicClasses.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jakub on 16.01.2017.
 */
public class CharCreationController implements Initializable{
    private Stage mainStage;

    @FXML RadioButton stoneRadio;
    @FXML RadioButton scissorRadio;
    @FXML RadioButton paperRadio;
    @FXML ToggleGroup classToggleGroup;

    @FXML Label stoneVal;
    @FXML Label paperVal;
    @FXML Label scissorsVal;
    @FXML Label healthVal;

    @FXML Button stonePlusButton;
    @FXML Button paperPlusButton;
    @FXML Button scissorsPlusButton;
    @FXML Button healthPlusButton;

    @FXML Button createButton;

    @FXML Label unusedSkillPointsLabel;
    @FXML TextField nameTextField;

    @FXML private void onCreateButton() {
        String name = nameTextField.getText();
        if(name.length() > 0) {
            Player.getInstance().setName(name);
            mainStage = (Stage) createButton.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
                mainStage.setScene(new Scene(root, 1200, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML private void onStoneRadio() {
        setStoneVal(Constants.HIGH_STAT_VAL);
        setPaperVal(Constants.LOW_STAT_VAL);
        setScissorsVal(Constants.LOW_STAT_VAL);
        setHealthVal(Constants.STARTING_HP);

        Player.getInstance().setSkillPoints(Constants.STARTING_SKILL_POINTS);
        unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
    }
    @FXML private void onPaperRadio() {
        setStoneVal(Constants.LOW_STAT_VAL);
        setPaperVal(Constants.HIGH_STAT_VAL);
        setScissorsVal(Constants.LOW_STAT_VAL);
        setHealthVal(Constants.STARTING_HP);

        Player.getInstance().setSkillPoints(Constants.STARTING_SKILL_POINTS);
        unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
    }
    @FXML private void onScissorRadio() {
        setStoneVal(Constants.LOW_STAT_VAL);
        setPaperVal(Constants.LOW_STAT_VAL);
        setScissorsVal(Constants.HIGH_STAT_VAL);
        setHealthVal(Constants.STARTING_HP);

        Player.getInstance().setSkillPoints(Constants.STARTING_SKILL_POINTS);
        unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
    }
    @FXML private void onStonePlusButton() {
        increaseStone();
    }
    @FXML private void onPaperPlusButton() {
        increasePaper();
    }
    @FXML private void onScissorPlusButton() {
        increaseScissors();
    }
    @FXML private void onHealthPlusButton() {
        increaseHealth();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player.getInstance().setSkillPoints(Constants.STARTING_SKILL_POINTS);
        onStoneRadio();
    }
    private void setStoneVal(int val) {
        Player.getInstance().setStoneVal(val);
        stoneVal.setText(String.valueOf(Player.getInstance().getStoneVal()));
    }
    private void setPaperVal(int val) {
        Player.getInstance().setPaperVal(val);
        paperVal.setText(String.valueOf(Player.getInstance().getPaperVal()));
    }
    private void setScissorsVal(int val) {
        Player.getInstance().setScissorsVal(val);
        scissorsVal.setText(String.valueOf(Player.getInstance().getScissorsVal()));
    }
    private void setHealthVal(int val) {
        Player.getInstance().setMaxHealth(val);
        healthVal.setText(String.valueOf(Player.getInstance().getMaxHealth()));
        Player.getInstance().healToMaxHealth();
    }
    private void setPlusButtonsDisabled(boolean disabled) {
        stonePlusButton.setDisable(disabled);
        paperPlusButton.setDisable(disabled);
        scissorsPlusButton.setDisable(disabled);
    }
    private void increaseStone(){
        if(Player.getInstance().getSkillPoints() > 0) {
            Player.getInstance().decreaseSkillPoints();
            unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
            setStoneVal(Player.getInstance().getStoneVal() + 1);
        }
        if(Player.getInstance().getSkillPoints() < 1) {
            setPlusButtonsDisabled(true);
            createButton.setDisable(false);
        }
    }
    private void increasePaper(){
        if(Player.getInstance().getSkillPoints() > 0) {
            Player.getInstance().decreaseSkillPoints();
            unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
            setPaperVal(Player.getInstance().getPaperVal() + 1);
        }
        if(Player.getInstance().getSkillPoints() < 1) {
            setPlusButtonsDisabled(true);
            createButton.setDisable(false);
        }
    }
    private void increaseScissors(){
        if(Player.getInstance().getSkillPoints() > 0) {
            Player.getInstance().decreaseSkillPoints();
            unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
            setScissorsVal(Player.getInstance().getScissorsVal() + 1);
        }
        if(Player.getInstance().getSkillPoints() < 1) {
            setPlusButtonsDisabled(true);
            createButton.setDisable(false);
        }
    }
    private void increaseHealth(){
        if(Player.getInstance().getSkillPoints() > 0) {
            Player.getInstance().decreaseSkillPoints();
            unusedSkillPointsLabel.setText(String.valueOf(Player.getInstance().getSkillPoints()));
            setHealthVal(Player.getInstance().getMaxHealth() + 10);
        }
        if(Player.getInstance().getSkillPoints() < 1) {
            setPlusButtonsDisabled(true);
            createButton.setDisable(false);
        }
    }
}
