package com.example.polinominom.mococo;

import android.graphics.LightingColorFilter;

import java.io.Serializable;

/**
 * Created by polinominom on 18.01.2016.
 */
public class Weapon implements Serializable {
    private int type;
    private int damage;
    private int gold;
    private String name;


    public Weapon(String name) {
        this.name = name;

        //this weapons must have their own classes but their is no time
        //so may be return TODO here
        initBasicAttributes();
        updateAttributesByName();

    }

    private void initBasicAttributes()
    {

        if(name.contains("Sword")){
            setDamage(10);
            setGold(30);
            setType(0);
        }
        else if(name.contains("Greatsword"))
        {
            setDamage(30);
            setGold(75);
            setType(0);
        }
        else if(name.contains("Bow"))
        {
            setDamage(20);
            setGold(60);
            setType(0);
        }
        else if(name.contains("Crossbow"))
        {
            setDamage(35);
            setGold(85);
            setType(0);
        }
        else if(name.contains("Double Dagger"))
        {
            setDamage(40);
            setGold(100);
            setType(0);
        }
        else if(name.contains("Magic Staff"))
        {
            setDamage(55);
            setGold(120);
            setType(1);
        }
        else if(name.contains("Spell"))
        {
            setDamage(75);
            setGold(175);
            setType(1);
        }

    }

    private void updateAttributesByName()
    {
        int oldDamage = getPureDamage();
        int oldPrice = getGold();

        //set damage
        if(name.contains("Medium")) {
            setDamage(oldDamage + 50);
            setGold(oldPrice + 500);
        }
        else if(name.contains("Heavy")) {
            setDamage(oldDamage + 100);
            setGold(oldPrice + 2500);
        }
        else if(name.contains("Legendary")) {
            setDamage(oldDamage + 700);
            setGold(oldPrice + 10000);
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
       this.gold = gold;
    }

    public String getType() {
        if(type == 0) return "Physical";
        else return "Magical";
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getPhysicalDamage()
    {
        if(type == 0) return damage;
        else return 0;
    }

    public int getMagicalDamage()
    {
        if(type == 1) return damage;
        else return 0;
    }


    public int getPureDamage()
    {
        return getPhysicalDamage()+getMagicalDamage();
    }


    public String shopStatus()
    {
        String s = "";

        s += "\n\nName: "+getName();
        s += "\nType: "+getType();
        s += "\nDamage: "+getPureDamage();
        s += "\n\nGold: "+getGold();

        return s;

    }

    public static int findPrice(String name)
    {
        return new Weapon(name).getGold();

    }




}
