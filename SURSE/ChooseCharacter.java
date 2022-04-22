package TemaPOO;

public class ChooseCharacter {
    public static Character factory (String c, String name, int level, int experience) {

        if (c.equals("Warrior"))
            return new Warrior(level, experience, name, -1, -1);
        if ( c.equals("Mage"))
            return new Mage(level, experience, name, -1, -1);
        if ( c.equals("Rogue"))
            return new Rogue(level, experience, name, -1, -1);
        return null;

    }
}
