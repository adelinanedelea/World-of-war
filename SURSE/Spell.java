package TemaPOO;

abstract class Spell implements Visitor {

    int cost_damage;
    int cost_mana;
    public Spell( int cost_damage, int cost_mana) {

        this.cost_damage = cost_damage;
        this.cost_mana = cost_mana;

    }

}
