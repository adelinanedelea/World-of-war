package TemaPOO;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potions;
    int max_weigth;
    int money;
    public Inventory () {
        this.potions = new  ArrayList<Potion>();
        this.max_weigth = 0;
        this.money = 0;
    }
    public void addPotion(Potion potion) {

        potions.add(potion);
    }

    public void deletePotion(Potion potion) {

        potions.remove(potion);
    }

    public int weight_remain() {

        int w = 0;
        for (Potion p : potions) {
            w = w + p.valueOfweight();
        }
        return max_weigth - w;
    }
}

