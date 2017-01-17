package sample.LogicClasses;

/**
 * Created by jakub on 16.01.2017.
 */
public class Player {
    private String name;
    private int stoneVal;
    private int scissorsVal;
    private int paperVal;
    private int skillPoints;
    private int maxHealth;
    private int health;

    private static Player instance;

    public static Player getInstance(){
        if(instance != null) {
            return instance;
        } else {
            instance = new Player();
            return instance;
        }
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

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void decreaseSkillPoints() {
        this.skillPoints = skillPoints - 1;
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
        if(health >= maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }
    public void healToMaxHealth() {
        this.health = this.maxHealth;
    }
}
