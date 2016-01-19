package com.example.polinominom.mococo;

import java.io.Serializable;

/**
 * Created by polinominom on 18.01.2016.
 */
public class Armor implements Serializable {

    private int healthBonus;
    private int defenceBonus;
    private int price;
    private String name;

    public Armor(String name) {
        this.name = name;

        initBasicArmorAttributes();
        updateAttributesByType();
    }


    private void initBasicArmorAttributes(){

        //set light attributes
        if(name.contains("Helmet")){
            setPrice(35);
            setHealthBonus(20);
            setDefenceBonus(5);

        }
        else if(name.contains("Shoulder"))
        {
            setPrice(25);
            setHealthBonus(10);
            setDefenceBonus(5);

        }
        else if(name.contains("Gloves")){
            setPrice(20);
            setHealthBonus(15);
            setDefenceBonus(5);
        }
        else if(name.contains("Leggings")){
            setPrice(30);
            setHealthBonus(25);
            setDefenceBonus(5);
        }
        else if(name.contains("Chest")){
            setPrice(40);
            setHealthBonus(30);
            setDefenceBonus(5);

        }

    }

    private void updateAttributesByType(){
        int oldHealth = getHealthBonus();
        int oldDefence = getDefenceBonus();
        int oldPrice = getPrice();

        //look what type the armor, and set the attributes
        if(name.contains("Medium")) {
            setPrice(oldPrice+500);
            setHealthBonus(oldHealth+20);
            setDefenceBonus(oldDefence+5);
        }
        else if(name.contains("Heavy")) {
            setPrice(oldPrice+2500);
            setHealthBonus(oldHealth+60);
            setDefenceBonus(oldDefence+15);
        }
        else if(name.contains("Legendary")) {
            setPrice(oldPrice+10000);
            setHealthBonus(oldHealth+100);
            setDefenceBonus(oldDefence+25);
        }
    }


    public String shopStatus()
    {
        String s = "";

        s += "\n\nName: "+getName();
        s += "\nHealth: "+getHealthBonus();
        s += "\nDefence: "+getDefenceBonus();
        s += "\nGold: "+getPrice();

        return s;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefenceBonus(int defenceBonus) {
        this.defenceBonus = defenceBonus;
    }

    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public int getDefenceBonus() {
        return defenceBonus;
    }
}
