package com.example.polinominom.mococo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.hardware.camera2.DngCreator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by polinominom on 16.01.2016.
 */
public class DungeonActivity extends Activity {

    private Game game;

    private ArrayList<Monster> floorMonsters;
    private int currentFloor;

    private Monster selectedMonster;
    private int pos;

    private Spinner floorSpinner;
    private ListAdapter floorListAdapter;
    private ListView monsterListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        getGameFromCalledActivity();
        displayPlayerStatus();

        addItemsToFloorSpinner();
        addListenerToFloorSpinner();




    }
    public void getGameFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inDungeonActivity: " + isGameCame);

    }

    public void addItemsToFloorSpinner()
    {
        floorSpinner = (Spinner)findViewById(R.id.floor_spinner_id);
        ArrayAdapter<CharSequence> floorSpinnerAdapter =
                ArrayAdapter.createFromResource(this,R.array.floors,android.R.layout.simple_spinner_item);

        floorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        floorSpinner.setAdapter(floorSpinnerAdapter);

    }

    public void addListenerToFloorSpinner()
    {
        floorSpinner = (Spinner)findViewById(R.id.floor_spinner_id);
        floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFloor = parent.getItemAtPosition(position).toString();
                String[] tokens = selectedFloor.split("[ ]+");


                currentFloor = Integer.parseInt(tokens[1]);
                Player p = game.getPlayer();
                if(currentFloor>p.getFloorLimit())
                {
                    currentFloor = p.getFloorLimit();
                    Toast.makeText(DungeonActivity.this,"You can't select floor(s) higher than " +
                            p.getFloorLimit()+" You must destroy all monsters in that floor",Toast.LENGTH_SHORT).show();
                }else{
                    Log.v("DEBUG", "SELECTED FLOOR: " + currentFloor);

                    createMonsterList();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void createMonsterList()
    {
        //set selected monster null
        //and clear info area
        displaySelectedMonsterStatus("", false);
        game.initializeFloorMonsters(currentFloor);
        createListView();


    }

    public void floorFinished()
    {
        Player p = game.getPlayer();

        p.setExp(p.getExp() + 200 * currentFloor);
        p.setGold(p.getGold() + 200 * currentFloor);

        if(p.getFloorLimit() == currentFloor)
            p.incFloorLimit();

        currentFloor++;
    }

    public void createListView()
    {
        //first clear all monsters
        floorMonsters = game.getFloorMonsters();

        int size = floorMonsters.size();

        if(size == 0){
            //floor finished reward the player
            floorFinished();

            //restart floor
            createMonsterList();

            return;
        }


        TextView monsterListTextView = (TextView)findViewById(R.id.monster_list_text_view);
        monsterListTextView.setText(String.format("Monster List F%d", currentFloor));

        String[] monstersNames = new String[size];

        for(int i = 0;i<size;i++)
        {
            monstersNames[i] = floorMonsters.get(i).getName();
        }

        ListAdapter theAdapter = new MonsterAdapter(this, monstersNames);
        monsterListView = (ListView) findViewById(R.id.monster_list_id);
        monsterListView.setAdapter(theAdapter);

        monsterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pos = position;
                String monsterPicked = String.valueOf(parent.getItemAtPosition(position));
                displaySelectedMonsterStatus(monsterPicked, true);
                displayPlayerStatus();

            }
        });
    }


    public void displayPlayerStatus()
    {
        Player p = game.getPlayer();
        TextView playerStatusTextView = (TextView) findViewById(R.id.player_status_dungeon_text_id);
        playerStatusTextView.setText(p.dungeonStatus());
    }

    public void displaySelectedMonsterStatus(String monsterPicked,boolean isDisplay)
    {
        TextView monsterStatusTextView;

        if(isDisplay)
        {

            monsterStatusTextView = (TextView) findViewById(R.id.monster_info_status_area);
            selectedMonster = createMonsterFromSelectedName(monsterPicked);
            String monsterInfo = Monster.getDungeonStatus(selectedMonster);
            monsterStatusTextView.setText(monsterInfo);
        }
        else
        {
            monsterStatusTextView = (TextView) findViewById(R.id.monster_info_status_area);
            monsterStatusTextView.setText("");
            selectedMonster = null;
        }
    }

    public void onBackToMapFromDungeonClick(View view) {


        Intent goingBackToMap = new Intent(this, PlayActivity.class);
        goingBackToMap.putExtra("Game_object",game);

        startActivity(goingBackToMap);
        finish();
    }

    public void onBackToTownFromDungeonClick(View view) {

        Intent goingTown = new Intent(this, TownActivity.class);
        goingTown.putExtra("Game_object",game);

        startActivity(goingTown);
        finish();
    }

    public void onBackToProfileFromDungeonClick(View view) {

        Intent goingProfile = new Intent(this, ProfileActivity.class);
        goingProfile.putExtra("Game_object",game);

        startActivity(goingProfile);
        finish();

    }

    public void onBackToQuestFromDungeonClick(View view) {
        Intent goingToQuest = new Intent(this,QuestActivity.class);
        goingToQuest.putExtra("Game_object",game);

        startActivity(goingToQuest);
        finish();
    }

    public Monster createMonsterFromSelectedName(String name)
    {

        switch (name) {
            case "Orc Warrior":
                return new OrcWarrior(name, currentFloor);
            case "Orc Mage":
                return new OrcMage(name, currentFloor);
            case "Orc Archer":
                return new OrcArcher(name, currentFloor);
            case "Orc Shaman":
                return new OrcShaman(name, currentFloor);
            case "Goblin Soldier":
                return new GoblinSoldier(name, currentFloor);
            case "Goblin Archer":
                return new GoblinArcher(name, currentFloor);
            case "Goblin Mage":
                return new GoblinMage(name, currentFloor);
            case "Goblin Warrior":
                return new GoblinWarrior(name, currentFloor);
            default:
                return null;
        }

    }

    public void onAttackButtonClick(View view) {

        if(selectedMonster == null)
        {
            String message = "You have to choose a monster for attack.";
            Toast.makeText(DungeonActivity.this,message, Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //begin the attack;
            short attackStatus = game.attack(selectedMonster);

            if(attackStatus == -1){

                //game over create new game reset player and send through main activity
                Player p = game.getPlayer();
                Player newPlayer = new Player(p.getName(),p.getPlayerRace().getName());
                Game g = new Game(newPlayer);

                DialogFragment myFragment = new DeadDialog(g,this);
                myFragment.show(getFragmentManager(), "theDialog");

            }
            else if(attackStatus == 1){

                //player reward
                Player p = game.getPlayer();
                p.setExp(p.getExp()+selectedMonster.getExp());
                p.setGold(p.getGold()+selectedMonster.getGold());

                //quest Update
                Quest q = p.getQuest();
                if(q != null)
                {

                    if(q.getType().equals(selectedMonster.getName()))
                    {
                        //monster killed same with quest type
                        //check if floor ok
                        if(currentFloor >= q.getKillFloorLimit()){
                            //decrease kill count
                            q.decreaseKillCount();

                            //check if quest ended
                            if(q.getKillCount() == 0)
                            {
                                //pass rewards into player
                                p.setGold(p.getGold() + q.getGoldReward());
                                p.setExp(p.getExp() + q.getExpReward());
                                p.setQuest(null);

                                Toast.makeText(DungeonActivity.this,"You've just completed a quest",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                displayPlayerStatus();
                displaySelectedMonsterStatus("", false);


                //delete Monster from list
                game.getFloorMonsters().remove(pos);
                createListView();
            }
            else if(attackStatus == 0) {

                //no one died, continue.
                displayPlayerStatus();
                displayMonsterStatus(selectedMonster);
            }

        }

    }

    public void displayMonsterStatus(Monster m)
    {
        TextView monsterStatusTextView = (TextView) findViewById(R.id.monster_info_status_area);
        monsterStatusTextView.setText(Monster.getDungeonStatus(m));
    }
}
