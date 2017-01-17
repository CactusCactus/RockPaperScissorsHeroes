package sample.LogicClasses;

/**
 * Created by jakub on 17.01.2017.
 */
public class Monster {
    private String name;
    private int stoneVal;
    private int paperVal;
    private int scissorsVal;
    private int maxHealth;
    private int health;

    public Monster(String name, int stoneVal, int paperVal, int scissorsVal, int health) {
        this.name = name;
        this.stoneVal = stoneVal;
        this.paperVal = paperVal;
        this.scissorsVal = scissorsVal;
        this.maxHealth = health;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoneVal() {
        return stoneVal;
    }

    public void setStoneVal(int stoneVal) {
        this.stoneVal = stoneVal;
    }

    public int getScissorsVal() {
        return scissorsVal;
    }

    public void setScissorsVal(int scissorsVal) {
        this.scissorsVal = scissorsVal;
    }

    public int getPaperVal() {
        return paperVal;
    }

    public void setPaperVal(int paperVal) {
        this.paperVal = paperVal;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
