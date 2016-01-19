package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */

public class OrcWarrior extends Monster{


    public OrcWarrior(String name, int floor) {
        super(name, floor);
        setMaxHealth(100);

        setPhysicalDamage(60-10);
        setPhysicalDefence(50-10);

        setMagicalDamage(10);
        setMagicalDefence(10);

        setName("Orc Warrior");

        setGold(21);
        setExp(12);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
