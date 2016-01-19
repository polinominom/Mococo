package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class GoblinSoldier extends Monster {
    public GoblinSoldier(String name, int floor) {
        super(name, floor);
        setName("Goblin Soldier");

        setMaxHealth(200);

        setPhysicalDamage(35);
        setPhysicalDefence(25);

        setMagicalDamage(5);
        setMagicalDefence(5);

        setGold(10);
        setExp(5);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
