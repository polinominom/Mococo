package com.example.polinominom.mococo;

import android.preference.PreferenceActivity;

import java.io.Serializable;

/**
 * Created by polinominom on 17.01.2016.
 */
public class Player implements Serializable {
    private String name = "player";
    private int maxHealth;
    private int currentHealth;
    private int level;
    private int exp;
    private int maxExp;
    private int gold;
    private Quest quest;
    private int upgradePoint;
    private int floorLimit;

    //weapon and armors
    private Weapon weapon;
    private Armor helmet;
    private Armor shoulder;
    private Armor gloves;
    private Armor leggings;
    private Armor chest;

    //default race
    private Race playerRace= new Race("Warrior");

    //player attributes

    //phsical
    private int physicalDamage;
    private int physicalDefence;

    //magical
    private int magicalDamage;
    private int magicalDefence;

    //critical damage and critical chance
    //these attributes are calculates as percents when attack triggers.
    private short critChance;
    private int critDamageBonus;

    private int strength;
    private int intelligent;
    private int luck;


    public Player(String name,String race) {
        super();
        this.name = name;
        this.playerRace = new Race(race);
        this.quest = null;

        maxHealth = 100;
        currentHealth = getMaxHealth();
        level = 1;
        setMaxExp(1000);

        exp = 0;
        gold = 0;
        upgradePoint = 0;

        //attributes
        physicalDamage=10;
        physicalDefence=10;

        magicalDamage = 10;
        magicalDefence=10;

        critChance = 10;
        critDamageBonus = 50;

        strength = 0;
        intelligent = 0;
        luck = 0;

        floorLimit = 1;

    }
    public void incFloorLimit()
    {
        this.floorLimit++;
    }

    public int getFloorLimit()
    {
        return this.floorLimit;
    }

    public String profileStatus()
    {
        String playerStatus = "";

        playerStatus += "\nName: "+getName();
        playerStatus += "\nRace: "+getPlayerRace().getName();
        playerStatus += "\nHealth: "+getCurrentHealth()+"/"+getMaxHealth();
        playerStatus += "\nLevel: "+getLevel();
        playerStatus += "\nExp: "+getExp();
        playerStatus += "\nGold: "+getGold();

        playerStatus +="\n\n" +
                "Physical Damage: "+getPhysicalDamage()+"(weapon included)";
        playerStatus +="\n" +
                "Physical Defence: "+getPhysicalDefence()+"(armors included)";

        playerStatus +="\n\n" +
                "Magical Damage : "+getMagicalDamage()+"(weapon included)";
        playerStatus +="\n" +
                "Magical Defence: "+getMagicalDefence()+"(armors included)";

        playerStatus += "\n\nCritical Hit Chance: %"+getCritChance();
        playerStatus += "\nCritical Hit Damage bonus %"+getCritDamageBonus();

        //str, int, luck
        playerStatus += "\n\nStrength: "+getStrength();
        playerStatus +="\nIntelligent: "+getIntelligent();
        playerStatus +="\nLuck: "+getLuck();

        return playerStatus;
    }

    public String dungeonStatus()
    {
        String s = "Player Status";

        s +="\n\nName: " + getName();
        s +="\nLevel: "+getLevel();
        s +="\nHealth: "+getCurrentHealth()+"/" + getMaxHealth();
        s +="\nExp: " +getExp();
        s +="\nGold: "+getGold();

        s +="\n\nPhysical Damage: "+getPhysicalDamage();
        s +="\nPhysical Defence :"+getPhysicalDefence();
        s +="\nMagical Damage: "+getMagicalDamage();
        s +="\nMagical Defence "+getMagicalDamage();

        s +="\n\nCurrent Quest "+Quest.info(getQuest());

        return  s;
    }

    public String armorShopStatus(String type)
    {
        String s = "";
        Armor armor = getArmorByType(type);

        if(armor != null){
            s += "\nType: Armor:";
            s += armor.shopStatus();
        }
        else
        {
            s += "\nDon't have this armor type yet.";
            s += "\n\nName: -";
            s += "\nHealth: -";
            s += "\nDefence: -";
            s += "\nGold: -";
        }

        return s;
    }

    public String weaponShopStatus()
    {
        String s = "";

        if(weapon != null){
            s += "\nType: Weapon";
            s += weapon.shopStatus();
        }
        else
        {
            s += "\nYou don't have weapon yet";
            s += "\n\nName: "+"-";
            s += "\nType: "+"-";
            s += "\nDamage: "+"-";
        }

        return s;
    }

    public void setArmor(Armor armor)
    {
        String s = armor.getName();

        if(s.contains("Helmet"))        setHelmet(armor);
        else if(s.contains("Shoulder")) setShoulder(armor);
        else if(s.contains("Gloves"))   setGloves(armor);
        else if(s.contains("Leggings")) setLeggings(armor);
        else if(s.contains("Chest"))    setChest(armor);

    }

    public Armor getArmorByType(String type)
    {
        if(type.contains("Helmet")) return this.helmet;

        else if(type.contains("Shoulder")) return this.shoulder;

        else if(type.contains("Gloves")) return this.gloves;

        else if(type.contains("Leggings")) return this.leggings;

        else if(type.contains("Chest")) return this.chest;

        else return null;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getPlayerRace() {
        return playerRace;
    }

    public void setPlayerRace(String playerRace) {
        this.playerRace = new Race(playerRace);
    }

    public int getLuck() {
        return luck+playerRace.getLuckBonus();
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getStrength() {
        return strength+playerRace.getStrBonus();
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligent() {
        return intelligent+playerRace.getIntBonus();
    }

    public void setIntelligent(int intelligent) {
        this.intelligent = intelligent;
    }

    public int getPhysicalDamage() {
        if(weapon == null)
            return (physicalDamage + playerRace.getPhysicalDamageBonus())*(strength+1);
        else
            return (physicalDamage + playerRace.getPhysicalDamageBonus()+weapon.getPhysicalDamage())*(strength+1);


    }

    public int getPhysicalDefence() {
        int purePhsicalDefence = physicalDefence + playerRace.getPhysicalDefenceBonus();

        //check armors and calculate new defence
        if(helmet != null) purePhsicalDefence += helmet.getDefenceBonus();
        if(shoulder != null) purePhsicalDefence+= shoulder.getDefenceBonus();
        if(gloves != null) purePhsicalDefence+= gloves.getDefenceBonus();
        if(leggings != null)purePhsicalDefence+= leggings.getDefenceBonus();
        if(chest != null)purePhsicalDefence+= chest.getDefenceBonus();


        return (purePhsicalDefence)*(strength+1);
    }

    public int getMagicalDamage() {
        if(weapon == null)
            return (magicalDamage + playerRace.getMagicalDamageBonus())*(intelligent+1);
        else
            return (magicalDamage + playerRace.getMagicalDamageBonus()+weapon.getMagicalDamage())*(intelligent+1);
    }


    public int getMagicalDefence() {
        return (magicalDefence+ playerRace.getMagicalDefenceBonus())*(intelligent+1);
    }


    //outGoing crit chance
    //default critChance + racebonus +luck
    public short getCritChance() {
        return (short) (critChance + playerRace.getCritChanceBonus()+luck);
    }

    public void setCritChance(short critChance) {
        this.critChance = critChance;
    }


    //outGoing crit damage bonus:default critDamageBonus + racebonus +luck*10
    public int getCritDamageBonus() {
        return critDamageBonus + playerRace.getCritDamageBonus() +luck*10;
    }

    public void setCritDamageBonus(int critDamageBonus) {
        this.critDamageBonus = critDamageBonus;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        controlLevelUp();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHealth() {
        return maxHealth + playerRace.getMaxHealthBonus();
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public void setCurrentHealth(int currentHealth){this.currentHealth = currentHealth;}

    public int getCurrentHealth() {
        return currentHealth;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getHelmet() {
        return helmet;
    }

    public void setHelmet(Armor helmet) {

        int hpBonus = helmet.getHealthBonus();
        int defenceBonus = helmet.getDefenceBonus();

        maxHealth +=hpBonus;
        currentHealth += hpBonus;

        physicalDefence += defenceBonus;
        magicalDefence += defenceBonus;

        this.helmet = helmet;

    }

    public Armor getShoulder() {
        return shoulder;
    }

    public void setShoulder(Armor shoulder) {

        int defenceBonus = shoulder.getDefenceBonus();
        physicalDefence += defenceBonus;
        magicalDefence += defenceBonus;

        int hpBonus = shoulder.getHealthBonus();
        maxHealth +=hpBonus;
        currentHealth += hpBonus;

        this.shoulder = shoulder;
    }

    public Armor getGloves() {
        return gloves;
    }

    public void setGloves(Armor gloves) {
        int hpBonus = gloves.getHealthBonus();
        maxHealth +=hpBonus;
        currentHealth += hpBonus;


        int defenceBonus = gloves.getDefenceBonus();
        physicalDefence += defenceBonus;
        magicalDefence += defenceBonus;


        this.gloves = gloves;
    }

    public Armor getLeggings() {
        return leggings;
    }

    public void setLeggings(Armor leggings) {
        int hpBonus = leggings.getHealthBonus();
        maxHealth +=hpBonus;
        currentHealth += hpBonus;

        int defenceBonus = leggings.getDefenceBonus();
        physicalDefence += defenceBonus;
        magicalDefence += defenceBonus;

        this.leggings = leggings;
    }

    public Armor getChest() {

        return chest;
    }

    public int getUpgradePoint() {
        return upgradePoint;
    }

    public int getMaxExp() {
        return maxExp*level;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public void controlLevelUp()
    {
        if(exp >= getMaxExp()){
            //level up
            exp = exp - getMaxExp();

            this.level++;
            upgradePoint++;

        }
    }

    public void setUpgradePoint(int upgradePoint) {
        this.upgradePoint = upgradePoint;
    }

    public void setChest(Armor chest) {
        int hpBonus = chest.getHealthBonus();
        maxHealth +=hpBonus;
        currentHealth += hpBonus;

        int defenceBonus = chest.getDefenceBonus();
        physicalDefence += defenceBonus;
        magicalDefence += defenceBonus;

        this.chest = chest;
    }

    public void incomingDamage(int mPhysical, int mMagical)
    {
        int totalPhysicalDamage = mPhysical - getPhysicalDefence();
        int totalMagicDamage = mMagical - getMagicalDefence();

        if(totalMagicDamage<0) totalMagicDamage = 0;
        if(totalPhysicalDamage<0) totalPhysicalDamage = 0;


        int totalDamage = totalPhysicalDamage+totalMagicDamage;

        currentHealth = currentHealth - totalDamage;


    }

    public boolean isDead(){
        return (currentHealth<=0);
    }

    public String displaySIL()
    {
        String s = "";

        s+="\n"+getStrength();
        s+="\n"+getIntelligent();
        s+="\n"+getLuck();

        return s;
    }

    public boolean isCritHit()
    {
        int random = (int)(Math.random()*100);
        return random < getCritChance();

    }

}
