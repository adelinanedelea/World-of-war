package TemaPOO;

public class Ice extends Spell{

    public Ice(int cost_damage, int cost_mana) {

        super(cost_damage, cost_mana);

    }
    @Override
    public void visit(Warrior w) {

        if ( w.ice ==  false ) {
            w.receiveDamage(cost_damage);
        }

    }

    @Override
    public void visit(Enemy e) {

        if ( e.ice == false) {
            e.receiveDamage(cost_damage);
        }

    }

    @Override
    public void visit(Rogue r) {
        if ( r.ice == false) {
            r.receiveDamage(cost_damage);
        }
    }

    @Override
    public void visit(Mage m) {
        if ( m.ice == false) {
            m.receiveDamage(cost_damage);
        }
    }
}
