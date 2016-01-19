package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class OrcMage extends Monster {
    public OrcMage(String name, int floor) {
        super(name, floor);
        setName("Orc Mage");

        setMaxHealth(100);

        setPhysicalDamage(25-10);
        setPhysicalDefence(20-10);

        setMagicalDamage(75-10);
        setMagicalDefence(60-10);

        setGold(20);
        setExp(11);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
