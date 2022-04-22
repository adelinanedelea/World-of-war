package TemaPOO;

public class HealthPotion implements Potion {
    private int price;
    private int weight;
    private int regeneration;

    public void setPrice ( int price) {
        this.price = price;
    }

    public void  setWeight (int weight) {
        this.weight = weight;
    }
    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

    public int regenerare() {
        return 0;
    }

    @Override
    public int valueOfprice() {
        return  this.price;
    }

    @Override
    public int valueOfregeneration() {
        return this.regeneration ;
    }

    @Override
    public int valueOfweight() {
        return this.weight;
    }
}
