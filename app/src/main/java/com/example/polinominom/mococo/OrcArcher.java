package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class OrcArcher extends Monster {

    public OrcArcher(String name, int floor) {
        super(name, floor);
        setName("Orc Archer");

        setMaxHealth(100);

        setPhysicalDamage(50-10);
        setPhysicalDefence(30-10);

        setMagicalDamage(25-10);
        setMagicalDefence(15-10);

        setGold(11);
        setExp(6);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
