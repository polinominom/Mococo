package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class OrcShaman extends Monster {

    public OrcShaman(String name, int floor) {
        super(name, floor);
        setName("Orc Shaman");

        setMaxHealth(100);

        setPhysicalDamage(10-5);
        setMagicalDefence(15-10);

        setMagicalDamage(50-10);
        setMagicalDefence(30-10);

        setGold(12);
        setExp(7);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
