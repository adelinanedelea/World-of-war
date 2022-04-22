package TemaPOO;

public interface Visitor {

    void visit ( Warrior w);
    void visit ( Enemy e);
    void visit ( Rogue r);
    void visit ( Mage m);

}
