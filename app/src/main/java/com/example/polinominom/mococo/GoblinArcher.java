package com.example.polinominom.mococo;

/**
 * Created by polinominom on 17.01.2016.
 */
public class GoblinArcher extends Monster {
    public GoblinArcher(String name, int floor) {
        super(name, floor);
        setName("Goblin Archer");
        setMaxHealth(100);

        setPhysicalDamage(30);
        setPhysicalDefence(30);

        setMagicalDamage(10);
        setMagicalDefence(15);

        setGold(20);
        setExp(6);

    }

    @Override
    public String getDungeonStatus() {
        return super.getDungeonStatus();
    }
}
