package TemaPOO;

public class Fire extends Spell{

    public Fire(int cost_damage, int cost_mana) {

        super(cost_damage, cost_mana);
    }

    @Override
    public void visit(Warrior w) {

        w.receiveDamage(cost_damage);
    }

    @Override
    public void visit(Enemy e) {

        e.receiveDamage(cost_damage);
    }

    @Override
    public void visit(Rogue r) {

        r.receiveDamage(cost_damage);
    }

    @Override
    public void visit(Mage m) {

        m.receiveDamage(cost_damage);
    }
}
