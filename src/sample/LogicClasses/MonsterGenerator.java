package sample.LogicClasses;

import java.util.Random;

/**
 * Created by jakub on 17.01.2017.
 */
public class MonsterGenerator {

    private final String[] monsterNames = {"Wilk", "Bandyta", "Szczur Wojownik", "Smok bagienny", "Pirat", "Wilkołak", "Wampir",
        "Czort", "Szatan", "Skacząca piłeczka", "Wielbłąd bojowy", "Panzerkampfwagen IV", "Ghoul", "Zombie", "Szkielet", "Szkielet Wojownik",
        "Szkielet Kusznik", "Szkielet tenisista", "Ludożerca", "Chmara pszczół"};

    public Monster generate() {
        Random random = new Random();
        String name = monsterNames[random.nextInt(monsterNames.length)];
        int stoneVal = random.nextInt(5) + 2;
        int paperVal = random.nextInt(5) + 2;
        int scissorsVal = random.nextInt(5) + 2;
        int healthVal = random.nextInt(21) + 10;
        return new Monster(name, stoneVal, paperVal, scissorsVal, healthVal);

    }
}
