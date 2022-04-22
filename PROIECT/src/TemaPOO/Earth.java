package TemaPOO;

public class Earth extends Spell {

    public Earth(int cost_damage, int cost_mana) {
        super(cost_damage, cost_mana);
    }
    @Override
    public void visit(Warrior w) {
        if ( w.earth == false) {
            w.receiveDamage(cost_damage);
        }
    }
    @Override
    public void visit(Enemy e) {
        if ( e.earth == false) {
            e.receiveDamage(cost_damage);
        }
    }
    @Override
    public void visit(Rogue r) {
        if ( r.earth == false) {
            r.receiveDamage(cost_damage);
        }
    }
    @Override
    public void visit(Mage m) {
        if ( m.earth == false)
        m.receiveDamage(cost_damage);
    }
}
