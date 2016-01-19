package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class GoblinWarrior extends Monster {
    public GoblinWarrior(String name, int floor) {
        super(name, floor);
        setName("Goblin Warrior");

        setMaxHealth(300);

        setPhysicalDamage(45);
        setPhysicalDefence(40);

        setMagicalDamage(5);
        setMagicalDefence(10);

        setGold(15);
        setExp(10);
    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
