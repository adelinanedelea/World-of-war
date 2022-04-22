package TemaPOO;

abstract public class Character extends Entity {

   String name;
   int Ox, Oy;
   Inventory inventory;
   int experience;
   int level;
   int strength, charisma, dexterity;
   public Character ( int level, int experience, String name, int ox, int oy) {

      this.inventory = new Inventory();
      this.level = level;
      this.experience = experience;
      this.name = name;
      this.Ox = ox;
      this.Oy = oy;

   }
   public String toString(){

      return name + " exp  " + experience + " lvl  " +  level + " ";
   }
   //cumpara potiunea p
   public String buy_potion(Potion p) {

      if (inventory.weight_remain() >= p.valueOfweight() && inventory.money >=  p.valueOfprice()) {
         inventory.money = inventory.money - p.valueOfprice();
         inventory.addPotion(p);
         return "Ai cumparat";
      }
      else {
         if (inventory.weight_remain() <= p.valueOfweight()) {
            return " Spatiu insuficient in inventar :(";
         }
         else {
            return "Nu ai suficiente monede :(";
         }
      }

   }
   //utilizeaza potiunea p
   public void  use_potion( Potion p) {

      if ( p instanceof ManaPotion) {
         regeneration_mana(p.valueOfregeneration());
         inventory.deletePotion(p);
      }
      else {
         regeneration_life(p.valueOfregeneration());
         inventory.deletePotion(p);
      }

   }
}
