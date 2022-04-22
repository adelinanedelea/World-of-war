package TemaPOO;

import java.util.Random;

public class Mage extends Character {
    Random rand;
    public Mage ( int level, int experience, String name, int ox, int oy ){
        super(level, experience, name, ox, oy);
        rand = new Random();
        inventory.max_weigth = 20;
        fire = false;
        ice = true;
        earth = false;
        strength = level % 100 + 10;
        charisma = level % 100 + 20;
        dexterity = level % 100 + 5;
        current_life = 100;
        current_mana = 100;
        Spell s1 = new Ice(rand.nextInt(21),  rand.nextInt(11));
        Spell s2 = new Fire(rand.nextInt(21),  rand.nextInt(11));
        Spell s3 = new Earth(rand.nextInt(21), rand.nextInt(11));
        spells.add(s1);
        spells.add(s2);
        spells.add(s3);
    }
    public void receiveDamage ( int damage) {
       if ( (dexterity + strength) % 3 == 0 ) {
           damage = damage/2;
       }
       current_life = current_life - damage;
    }
    public int getDamage() {
        int damage = 0;
        damage = charisma/ 4 + dexterity/6 + strength/6;
        if ( charisma % 5 == 0) {
            damage = damage *2;
        }
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
         visitor.visit(this);
    }
}
