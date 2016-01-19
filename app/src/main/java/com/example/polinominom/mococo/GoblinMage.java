package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class GoblinMage extends Monster {
    public GoblinMage(String name, int floor) {
        super(name, floor);
        setName("Goblin Mage");

        setMaxHealth(85);

        setPhysicalDamage(10);
        setMagicalDamage(10);

        setMagicalDamage(100);
        setMagicalDefence(100);

        setGold(20);
        setExp(10);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
