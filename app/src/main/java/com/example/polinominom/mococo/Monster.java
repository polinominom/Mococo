package com.example.polinominom.mococo;

import java.io.Serializable;

/**
 * Created by polinominom on 17.01.2016.
 */
public class Monster implements Serializable {
    private String name;

    private int gold;
    private int exp;
    //private String type;
    //normal, magical, physical
    //normals are take directly hit
    //magicals are strong against magical attack but weak against physical
    //physicals are strong against physical attack but weak against magical


    private int maxHealth;
    private int currentHealth;

    private int physicalDamage;
    private int physicalDefence;

    private int magicalDamage;
    private int magicalDefence;

    private int floorLevel;

    public Monster(String name,int floor) {
        this.name = name;
        this.floorLevel = floor;

        maxHealth = 100;
        currentHealth = maxHealth;

        physicalDamage = 10;
        physicalDefence = 10;

        magicalDamage = 10;
        magicalDefence = 10;

        upgradeAttributes(floor);

    }

    private void upgradeAttributes(int floor)
    {
        maxHealth *= floor;
        currentHealth = maxHealth;

        physicalDamage *= floor;
        physicalDefence *= floor;

        magicalDamage *= floor;
        magicalDefence *= floor;
    }

    public void incomingDamage(int phsDamage, int magicDamage)
    {
        int totalPhysicalDamage = phsDamage-(this.physicalDefence);
        int totalMagicalDamage = magicDamage-(this.magicalDefence);

        if(totalPhysicalDamage <0) totalPhysicalDamage = 0;
        if(totalMagicalDamage <0) totalMagicalDamage = 0;

        int totalDamage = totalMagicalDamage + totalPhysicalDamage;


        currentHealth -= totalDamage;

    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
        upgradeAttributes(floorLevel);
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }

    public int getPhysicalDefence() {
        return physicalDefence;
    }

    public int getMagicalDamage() {
        return magicalDamage;
    }

    public int getMagicalDefence() {
        return magicalDefence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth*floorLevel;
        currentHealth = getMaxHealth();
    }

    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage*floorLevel;
    }

    public void setPhysicalDefence(int physicalDefence) {
        this.physicalDefence = physicalDefence*floorLevel;
    }

    public void setMagicalDamage(int magicalDamage) {
        this.magicalDamage = magicalDamage*floorLevel;
    }

    public void setMagicalDefence(int magicalDefence) {
        this.magicalDefence = magicalDefence*floorLevel;
    }

    public boolean isDead(){
        return (currentHealth<=0);
    }

    public int getGold() {
        return gold*floorLevel;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp*floorLevel;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getDungeonStatus() {
        return null;
    }

    public static String getDungeonStatus(Monster m)
    {
        String s = "Monster Status";

        s+="\n\nName: "     +m.getName();
        s+="\nFloor Level: "+m.getFloorLevel();
        s+="\nHealth: "     +m.getCurrentHealth()+"/"+m.getMaxHealth();
        s+="\nExp: "        +m.getExp();
        s+="\nGold: "       +m.getGold();

        return s;
    }




}
