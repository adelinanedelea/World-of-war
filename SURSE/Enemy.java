package TemaPOO;

import java.util.Random;

public class Enemy extends Entity implements CellElement{
    public Enemy () {
        super();
        Boolean ice, fire, earth;
        Random rand = new Random();
        current_life = rand.nextInt(101) + 1;
        current_mana = rand.nextInt(101) + 1;
        if ( rand.nextInt(2) == 1) {
            ice = true;
        }
        else {
            ice = false;
        }
        if ( rand.nextInt(2) == 1) {
            fire = true;
        }
        else {
            fire = false;
        }
        if ( rand.nextInt(2) == 1) {
            earth = true;
        }
        else {
            earth = false;
        }
        Spell s1 = new Ice(rand.nextInt(21),  rand.nextInt(11));
        Spell s2 = new Fire(rand.nextInt(21),  rand.nextInt(11));
        Spell s3 = new Earth(rand.nextInt(21), rand.nextInt(11));
        spells.add(s1);
        spells.add(s2);
        spells.add(s3);
    }
     public void receiveDamage(int damage) {
        current_life = current_life - damage;
     }
    @Override
    public int getDamage() {
        Random rand = new Random();
        return rand.nextInt(25);
    }
    @Override
    public char toCharacter() {
        return 'E';
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
