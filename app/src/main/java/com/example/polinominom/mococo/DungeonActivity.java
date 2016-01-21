package com.example.polinominom.mococo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
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

    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        getGameFromCalledActivity();

        context = this;

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
        game.initializeFloorMonsters(currentFloor);
        updateListView();


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

    public void updateListView()
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

                //when monster picked start new acticity
                selectedMonster = createMonsterFromSelectedName(monsterPicked);

                Intent attack = new Intent(context, AttackActivity.class);
                attack.putExtra("Game_object",game);
                attack.putExtra("Monster_object",selectedMonster);
                attack.putExtra("Current_floor",""+currentFloor);
                attack.putExtra("Monster_position",""+pos);

                final int result = 1;

                startActivityForResult(attack,result);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) {

            //log message end return
            Log.v("DungeonActivity","onActivityResult data null!");
            return;
        }

        game = (Game)data.getSerializableExtra("Game_object");
        updateListView();




    }

    public void displayPlayerStatus()
    {
        //Player p = game.getPlayer();
        //TextView playerStatusTextView = (TextView) findViewById(R.id.player_status_dungeon_text_id);
        //playerStatusTextView.setText(p.dungeonStatus());
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


    public void onProfileButtonClick(View view)
    {
        Intent goingProfile = new Intent(this,ProfileActivity.class);
        goingProfile.putExtra("Game_object",game);

        startActivity(goingProfile);
        finish();

    }

    public void onDungeonButtonClick(View view)
    {
        //empty
    }

    public void onQuestButtonClick(View view)
    {
        Intent goingQuest = new Intent(this,QuestActivity.class);
        goingQuest.putExtra("Game_object",game);

        startActivity(goingQuest);
        finish();
    }

    public void onShopButtonClick(View view)
    {
        Intent goingShop = new Intent(this,ShopActivity.class);
        goingShop.putExtra("Game_object",game);

        startActivity(goingShop);
        finish();
    }


    public void onUpgradeButtonClick(View view)
    {
        Intent goingUpgrade = new Intent(this,UpgradeActivity.class);
        goingUpgrade.putExtra("Game_object",game);

        startActivity(goingUpgrade);
        finish();
    }
}
