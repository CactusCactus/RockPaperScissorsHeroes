package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.LogicClasses.Monster;
import sample.LogicClasses.MonsterGenerator;
import sample.LogicClasses.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by jakub on 17.01.2017.
 */
public class GameController implements Initializable{
    @FXML Label playerStoneVal;
    @FXML Label playerPaperVal;
    @FXML Label playerScissorVal;

    @FXML Label heroName;

    @FXML Label playerHpVal;
    @FXML Label playerMaxHpVal;

    @FXML Label monsterStoneVal;
    @FXML Label monsterPaperVal;
    @FXML Label monsterScissorsVal;

    @FXML Label monsterName;

    @FXML Label monsterHpVal;
    @FXML Label monsterMaxHpVal;

    @FXML Button fightButton;

    @FXML Button stoneButton;
    @FXML Button paperButton;
    @FXML Button scissorsButton;

    @FXML ImageView playerAtack;
    @FXML ImageView monsterAtack;

    @FXML Label killsLabel;

    @FXML Label stateLabel;

    private MonsterGenerator monsterGenerator;
    private int playerAtackType;
    private int monsterAtackType;

    private Monster monster;

    private int killed = 0;

    private Random random;

    @FXML private void onFightButton() {
        showMonster();
        fightButton.setDisable(true);
        disableAllButtons(false);
    }
    @FXML private void onStoneButton() {
        disableAllButtons(true);
        playerAtackType = Constants.STONE_CODE;
        fight();
    }
    @FXML private void onPaperButton() {
        disableAllButtons(true);
        playerAtackType = Constants.PAPER_CODE;
        fight();
    }
    @FXML private void onScissorsButton() {
        disableAllButtons(true);
        playerAtackType = Constants.SCISSORS_CODE;
        fight();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerStoneVal.setText(String.valueOf(Player.getInstance().getStoneVal()));
        playerPaperVal.setText(String.valueOf(Player.getInstance().getPaperVal()));
        playerScissorVal.setText(String.valueOf(Player.getInstance().getScissorsVal()));

        heroName.setText(Player.getInstance().getName());

        playerHpVal.setText(String.valueOf(Player.getInstance().getHealth()));
        playerMaxHpVal.setText(String.valueOf(Player.getInstance().getMaxHealth()));

        monsterGenerator = new MonsterGenerator();
        random = new Random();

    }
    private void disableAllButtons(boolean disable) {
        stoneButton.setDisable(disable);
        paperButton.setDisable(disable);
        scissorsButton.setDisable(disable);
    }
    private void showMonster() {
        monster = monsterGenerator.generate();
        monsterName.setText(monster.getName());
        monsterStoneVal.setText(String.valueOf(monster.getStoneVal()));
        monsterPaperVal.setText(String.valueOf(monster.getPaperVal()));
        monsterScissorsVal.setText(String.valueOf(monster.getScissorsVal()));
        monsterHpVal.setText(String.valueOf(monster.getHealth()));
        monsterMaxHpVal.setText(String.valueOf(monster.getMaxHealth()));
    }
    private void setAtackPicture() {
        File file = new File("src/sample/res/drawables/" + choosePicture(playerAtackType));
        Image image = new Image(file.toURI().toString());
        playerAtack.setImage(image);
        monsterAtackType = random.nextInt(3);
        file = new File("src/sample/res/drawables/" + choosePicture(monsterAtackType));
        image = new Image(file.toURI().toString());
        monsterAtack.setImage(image);
    }
    private String choosePicture(int code) {
        String picName = "";
        switch (code) {
            case Constants.STONE_CODE:
                picName = "rock_icon.png";
                break;
            case Constants.PAPER_CODE:
                picName = "paper_icon.png";
                break;
            case Constants.SCISSORS_CODE:
                picName = "scisors_icon.png";
                break;
        }
        return picName;
    }
    private void fight() {
        stateLabel.setText("Walka");
        setAtackPicture();
        int damage;
        double multiplier;
        double monsterMultiplier;
        if(didPlayerWin() == Constants.WON_CODE) {
            multiplier = 1.7;
            monsterMultiplier = 0.3;
        } else if (didPlayerWin() == Constants.DRAW_CODE) {
            multiplier = 0.8;
            monsterMultiplier = 0.8;
        } else {
            multiplier = 0.3;
            monsterMultiplier = 1.7;
        }
        damage = getDamage(playerAtackType, multiplier);
        monster.setHealth(monster.getHealth() - damage);
        int monsterDamage = getMonsterDamage(monsterAtackType, monsterMultiplier);
        System.out.println(monsterDamage);
        int hp = Player.getInstance().getHealth() - monsterDamage;
        Player.getInstance().setHealth(hp);
        System.out.println(Player.getInstance().getHealth());
        checkAndRefreshHp();
    }
    private void checkAndRefreshHp() {
        playerHpVal.setText(String.valueOf(Player.getInstance().getHealth()));
        monsterHpVal.setText(String.valueOf(monster.getHealth()));
        if(Player.getInstance().getHealth() <= 0){
            disableAllButtons(true);
            fightButton.setDisable(false);
            Constants.FIRST_PLAY = false;
            Constants.SCORE = killed;
            Stage mainStage = (Stage) fightButton.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                mainStage.setScene(new Scene(root, 1200, 600));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (monster.getHealth() <= 0) {
            disableAllButtons(true);
            fightButton.setDisable(false);
            killed ++;
            killsLabel.setText(String.valueOf(killed));
            stateLabel.setText("Wygrana");

        } else {
            disableAllButtons(false);
        }
    }
    private int getDamage(int atackType, double multiplier) {
        int damage = 0;
        switch (atackType) {
            case Constants.STONE_CODE:
                damage = (int)(Player.getInstance().getStoneVal() * multiplier);
                break;
            case Constants.PAPER_CODE:
                damage = (int)(Player.getInstance().getPaperVal() * multiplier);
                break;
            case Constants.SCISSORS_CODE:
                damage = (int)(Player.getInstance().getScissorsVal() * multiplier);
                break;
        }
        return damage;
    }
    private int getMonsterDamage(int atackType, double multiplier) {
        int damage = 0;
        switch (atackType) {
            case Constants.STONE_CODE:
                damage = (int)(monster.getStoneVal() * multiplier);
                break;
            case Constants.PAPER_CODE:
                damage = (int)(monster.getPaperVal() * multiplier);
                break;
            case Constants.SCISSORS_CODE:
                damage = (int)(monster.getScissorsVal() * multiplier);
                break;
        }
        return damage;
    }
    private int didPlayerWin() {
        switch (playerAtackType) {
            case Constants.STONE_CODE:
                switch (monsterAtackType) {
                    case Constants.STONE_CODE:
                        return Constants.DRAW_CODE;
                    case Constants.PAPER_CODE:
                        return Constants.LOST_CODE;
                    case Constants.SCISSORS_CODE:
                        return Constants.WON_CODE;
                }
            case Constants.PAPER_CODE:
                switch (monsterAtackType) {
                    case Constants.STONE_CODE:
                        return Constants.WON_CODE;
                    case Constants.PAPER_CODE:
                        return Constants.DRAW_CODE;
                    case Constants.SCISSORS_CODE:
                        return Constants.LOST_CODE;
                }
            case Constants.SCISSORS_CODE:
                switch (monsterAtackType) {
                    case Constants.STONE_CODE:
                        return Constants.LOST_CODE;
                    case Constants.PAPER_CODE:
                        return Constants.WON_CODE;
                    case Constants.SCISSORS_CODE:
                        return Constants.DRAW_CODE;
                }
        }
        return Constants.DRAW_CODE;
    }

}
