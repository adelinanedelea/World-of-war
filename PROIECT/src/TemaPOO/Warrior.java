package TemaPOO;

import java.util.Random;

public class Warrior extends Character{

    Random rand;
    public Warrior ( int level, int experience, String name, int ox, int oy ){

        super(level, experience, name, ox, oy);
        inventory.max_weigth = 100;
        fire = true;
        ice = false;
        earth = false;
        strength = level % 100 + 15;
        charisma = level % 100 + 7;
        dexterity = level % 100 + 2;
        current_life = 100;
        current_mana = 100;
        rand  = new Random();
        Spell s1 = new Ice(rand.nextInt(21),  rand.nextInt(11));
        Spell s2 = new Fire(rand.nextInt(21),  rand.nextInt(11));
        Spell s3 = new Earth(rand.nextInt(21), rand.nextInt(11));
        spells.add(s1);
        spells.add(s2);
        spells.add(s3);

    }
    public void receiveDamage ( int damage) {

        if ( (dexterity + charisma) % 3 == 0 ) {
            damage = damage/2;
        }
        current_life = current_life - damage;

    }
    public int getDamage() {

        int damage = 0;
        damage = strength/ 4 + dexterity/6 + charisma/6;
        if ( strength % 5 == 0) {
            damage = damage *2;
        }
        return damage;

    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visit(this);

    }
}
