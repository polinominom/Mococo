package com.example.polinominom.mococo;

import java.io.Serializable;

/**
 * Created by polinominom on 17.01.2016.
 */

public class Race implements Serializable {
    private String name;

    private short critChanceBonus;
    private int critDamageBonus;

    private int physicalDamageBonus;
    private int physicalDefenceBonus;

    private int magicalDamageBonus;
    private int magicalDefenceBonus;

    private int maxHealthBonus;

    private short strBonus;
    private short intBonus;
    private short luckBonus;

    public Race(String name) {
        this.name = name;
        setBonuses();
    }

    private void setBonuses()
    {
        if(this.name == null) this.name ="warrior";

        if(name.equals("Warrior")){
            critChanceBonus = 5;
            critDamageBonus = 0;

            physicalDamageBonus=20;
            physicalDefenceBonus=20;

            magicalDamageBonus = 0;
            magicalDefenceBonus = 0;

            maxHealthBonus = 50;

            strBonus = 2;
            intBonus = 0;
            luckBonus = 0;

        }
        else if(name.equals("Assassin")){

            critChanceBonus = 15;
            critDamageBonus = 10;

            physicalDamageBonus=10;
            physicalDefenceBonus=10;

            magicalDamageBonus = 10;
            magicalDefenceBonus = 10;

            maxHealthBonus = 25;

            strBonus = 1;
            intBonus = 1;
            luckBonus = 1;

        }
        else if(name.equals("Mage")){
            critChanceBonus = 5;
            critDamageBonus = 30;

            physicalDamageBonus=0;
            physicalDefenceBonus=0;

            magicalDamageBonus = 20;
            magicalDefenceBonus = 20;

            maxHealthBonus = 0;

            strBonus = 0;
            intBonus = 2;
            luckBonus = 0;
        }

    }


    public String getName() {
        return name;
    }

    public short getCritChanceBonus() {
        return critChanceBonus;
    }

    public int getCritDamageBonus() {
        return critDamageBonus;
    }

    public int getPhysicalDamageBonus() {
        return physicalDamageBonus;
    }

    public int getPhysicalDefenceBonus() {
        return physicalDefenceBonus;
    }

    public int getMagicalDamageBonus() {
        return magicalDamageBonus;
    }

    public int getMagicalDefenceBonus() {
        return magicalDefenceBonus;
    }

    public int getMaxHealthBonus() {
        return maxHealthBonus;
    }

    public short getStrBonus() {
        return strBonus;
    }

    public short getIntBonus() {
        return intBonus;
    }

    public short getLuckBonus() {
        return luckBonus;
    }
}
