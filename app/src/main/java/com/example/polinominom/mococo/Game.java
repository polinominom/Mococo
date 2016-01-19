package com.example.polinominom.mococo;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by polinominom on 17.01.2016.
 */
public class Game implements Serializable {
    private Player player;
    private ArrayList<Monster> floorMonsters;
    private ArrayList<Quest> quests;
    //private ArrayList<Quest> mediumQuests;
   // private ArrayList<Quest> hardQuests;
    //private ArrayList<Quest> exceptionalQuests;
    //private ArrayList<Quest> legendaryQuests;



    public Game(Player player) {
        this.player = player;
        floorMonsters = new ArrayList<Monster>();
        quests = new ArrayList<Quest>();

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;

    }

    public ArrayList<Monster> getFloorMonsters() {
        return floorMonsters;
    }

    public void initializeFloorMonsters(int floor)
    {
        //first clear
        deleteFloorMonsters();

        int monsterLimit = 20;
        for(int i = 0; i<monsterLimit; i++)
        {
            floorMonsters.add(createRandomMonster(floor));
        }

    }

    public void deleteFloorMonsters()
    {
        floorMonsters.clear();
    }


    public void deleteQuests()
    {
        quests.clear();
    }

    public void createQuests(String difficulty)
    {
        //first clear quests
        deleteQuests();

        //then create quests
        Quest m;

        m = new Quest("Orc Warrior",difficulty);
        quests.add(m);

        m = new Quest("Orc Archer",difficulty);
        quests.add(m);

        m = new Quest("Orc Shaman",difficulty);
        quests.add(m);

        m = new Quest("Orc Mage",difficulty);
        quests.add(m);

        m = new Quest("Goblin Soldier",difficulty);
        quests.add(m);

        m = new Quest("Goblin Mage",difficulty);
        quests.add(m);

        m = new Quest("Goblin Warrior",difficulty);
        quests.add(m);

        m = new Quest("Goblin Archer",difficulty);
        quests.add(m);
    }

    private Monster createRandomMonster(int floor)
    {
        Monster m;
        double randomDoubleValue = Math.random()*1000;
        int randomNumberSelected = ((int)randomDoubleValue)%8;

        if(randomNumberSelected == 0){
            m = new OrcWarrior("OrcWarrior",floor);
        }
        else if(randomNumberSelected == 1){
            m = new OrcArcher("OrcArcher",floor);
        }
        else if(randomNumberSelected == 2){
            m = new OrcShaman("OrcShaman",floor);
        }
        else if(randomNumberSelected == 3){
            m = new OrcMage("OrcMage",floor);
        }
        else if(randomNumberSelected == 4){
            m = new GoblinSoldier("GoblinSoldier",floor);
        }
        else if(randomNumberSelected == 5){
            m = new GoblinMage("GoblinMage",floor);
        }
        else if(randomNumberSelected == 6){
            m = new GoblinWarrior("GoblinWarrior",floor);
        }
        else /*if(randomNumberSelected == 7)*/
            m = new GoblinArcher("GoblinArcher",floor);


        return m;

    }

    public short attack(Monster s){
        Player p = this.getPlayer();

        int playersPhysicalAttack = p.getPhysicalDamage();
        int playersMagicalAttack = p.getMagicalDamage();
        if(p.isCritHit()){
            playersPhysicalAttack += (playersPhysicalAttack*p.getCritDamageBonus())/100;
            playersMagicalAttack += (playersMagicalAttack*p.getCritDamageBonus())/100;
        }

        Log.v("Attack","player_physical_dmg:"+playersPhysicalAttack);
        Log.v("Attack", "player_magical_dmg:" + playersMagicalAttack);

        s.incomingDamage(playersPhysicalAttack,playersMagicalAttack);
        if(!s.isDead()){
            int monstersPhysicalAttack = s.getPhysicalDamage();
            int monstersMagicalAttack = s.getMagicalDamage();

            Log.v("Attack","monst_physical_dmg:"+monstersPhysicalAttack);
            Log.v("Attack","monst_magical_dmg:"+monstersMagicalAttack);

            p.incomingDamage(monstersPhysicalAttack,monstersMagicalAttack);

            if(p.isDead()) return -1;
            else return 0;

        }
        else{

            //delete monster from list
            return 1;
        }

    }


    public ArrayList<Quest> getQuests() {
        return quests;
    }

}
