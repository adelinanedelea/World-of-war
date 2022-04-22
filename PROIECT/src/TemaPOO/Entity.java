package TemaPOO;

import java.util.ArrayList;

public abstract class Entity implements Element {
    ArrayList <Spell> spells;
    int current_life;
    int max_life = 100;
    int current_mana;
    int max_mana = 100;
    boolean ice, fire, earth;
    public Entity () {
        this.spells = new ArrayList<Spell>();
    }
    public void regeneration_life ( int life) {
        if ( life > max_life) {
            this.current_life = max_life;
        }
        else {
            this.current_life = this.current_life  + life;
        }
    }
    public void regeneration_mana ( int mana) {

        if ( mana > max_mana) {
            this.current_mana = max_mana;
        }
        else {
            this.current_mana = this.current_mana + mana;
        }
    }
    public int use_abilities(Spell spell, Entity enemy) {

        if (current_mana > spell.cost_mana) {
            current_mana = current_mana - spell.cost_mana;
            enemy.accept(spell);
            return 1;
        }
        else {
                enemy.receiveDamage(this.getDamage());
                System.out.println(" Nu poate fi folosita abilitatea");
                return 0;
        }
    }
    abstract public  void receiveDamage(int damage);
    abstract public int getDamage();
}
