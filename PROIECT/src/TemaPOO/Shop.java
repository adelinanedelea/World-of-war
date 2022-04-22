package TemaPOO;

import java.util.ArrayList;
import java.util.Random;

public class Shop implements CellElement {

    ArrayList<Potion> potions;
    Random r = new Random();
    public Shop () {

        this.potions = new ArrayList<Potion>();
        Potion p1 = new HealthPotion();
        Potion p2 = new ManaPotion();
        ((HealthPotion)p1).setPrice(r.nextInt(5));
        ((HealthPotion)p1).setWeight(r.nextInt(20));
        ((HealthPotion)p1).setRegeneration(20 + r.nextInt(80));
        ((ManaPotion)p2).setPrice(r.nextInt(5));
        ((ManaPotion)p2).setWeight(r.nextInt(20));
        ((ManaPotion)p2).setRegeneration(20 + r.nextInt(80));
        potions.add(p1);
        potions.add(p2);
    }
    public Potion selectPotion( int index) {

        Potion potion = potions.get(index);
        potions.remove(index);
        return potion;

    }
    @Override
    public char toCharacter() {

        return 'S';

    }

}
