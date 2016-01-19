package com.example.polinominom.mococo;

import java.io.Serializable;

/**
 * Created by polinominom on 17.01.2016.
 */
public class Quest implements Serializable{

    private String monsterType;
    private String difficulty;
    private int killCount;
    private int killFloorLimit;
    private int expReward;
    private int goldReward;

    public Quest(String type, String difficulty) {
        this.monsterType = type;
        this.difficulty = difficulty;

        initializeQuestByDif();



    }

    public void initializeQuestByDif()
    {
        switch (difficulty) {
            case "Easy":
                setKillCount(50);
                setKillFloorLimit(1);
                setExpReward(100);
                setGoldReward(100);
                break;

            case "Medium":
                setKillCount(100);
                setKillFloorLimit(3);
                setExpReward(250);
                setGoldReward(250);
                break;

            case "Hard":
                setKillCount(200);
                setKillFloorLimit(7);
                setExpReward(500);
                setGoldReward(500);
                break;

            case "Exceptional":
                setKillCount(500);
                setKillFloorLimit(15);
                setExpReward(1000);
                setGoldReward(1000);
                break;

            case "Legendary":
                setKillCount(2000);
                setKillFloorLimit(20);
                setExpReward(5000);
                setGoldReward(5000);
                break;
        }
    }

    //decreaseKillCount
    public void decreaseKillCount()
    {
        if(killCount>0)
            killCount--;
    }

    public String getType()
    {
        return this.monsterType;
    }

    public int getKillCount()
    {
        return this.killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public int getKillFloorLimit() {
        return killFloorLimit;
    }

    public void setKillFloorLimit(int killFloor) {
        this.killFloorLimit = killFloor;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public static String info(Quest q)
    {
        String s = "";

        if(q != null)
        {
            s += "\n\nTarget: "+q.getType();
            s +="\nKill Count: "+q.getKillCount();
            s +="\nFloor Limit: "+q.getKillFloorLimit();
            s +="\nExp/Gold rewards: "+q.getExpReward()+"/"+q.getGoldReward();
            s +="\nDifficulty: "+q.getDifficulty();
        }
        else
        {
            s += "\n\nTarget: -";
            s +="\nKill Count: -";
            s +="\nFloor Limit: -";
            s +="\nExp/Gold rewards: "+"-/-";
            s +="\nDifficulty: -";
        }

        return s;
    }
}
