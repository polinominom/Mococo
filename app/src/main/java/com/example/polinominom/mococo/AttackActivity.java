package com.example.polinominom.mococo;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AccessControlContext;

/**
 * Created by polinominom on 20.01.2016.
 */
public class AttackActivity extends Activity {

    private Game game;
    private Monster attackedMonster;
    private TextView statusTextView;
    private int currentFloor;
    private int listPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);

        getObjectsFromCalledActivity();
        displayPlayerStatus();
        displayMonsterStatus();


    }

    public void getObjectsFromCalledActivity()
    {
        game = (Game) getIntent().getSerializableExtra("Game_object");

        attackedMonster = (Monster) getIntent().getSerializableExtra("Monster_object");

        String s = getIntent().getStringExtra("Current_floor");
        if(s != null)
            currentFloor = Integer.parseInt(s);

        s = getIntent().getStringExtra("Monster_position");
        if(s != null)
            listPosition = Integer.parseInt(s);


        boolean isGameCame = (game != null);
        boolean isMonsterCame = (attackedMonster != null);

        //set messages to log
        Log.v("AttackActivity", "isGameCame:" + isGameCame);
        Log.v("AttackActivity", "isMonsterCame: " + isMonsterCame);


    }

    public void displayPlayerStatus()
    {
        if(game == null) return;
        Player p = game.getPlayer();
        String dungeonStatus =p.dungeonStatus();

        statusTextView = (TextView) findViewById(R.id.player_status_attack_id);
        statusTextView.setText(String.format("PLAYER\n%s", dungeonStatus));

    }

    public void displayMonsterStatus()
    {
        if(attackedMonster == null) return;
        String dungeonStatus = Monster.getDungeonStatus(attackedMonster);

        statusTextView = (TextView) findViewById(R.id.monster_status_attack_id);
        statusTextView.setText(String.format("Monster\n%s", dungeonStatus));
    }

    public void onAttackButtonClick(View view) {
        if(attackedMonster == null)
        {
            String message = "You have to choose a monster for attack.";
            Toast.makeText(AttackActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //begin the attack;
            short attackStatus = game.attack(attackedMonster);

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
                p.setExp(p.getExp()+attackedMonster.getExp());
                p.setGold(p.getGold()+attackedMonster.getGold());

                //quest Update
                Quest q = p.getQuest();
                if(q != null)
                {

                    if(q.getType().equals(attackedMonster.getName()))
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

                                Toast.makeText(AttackActivity.this,"You've just completed a quest",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                displayPlayerStatus();

                //delete Monster from list
                game.getFloorMonsters().remove(listPosition);

                goBackToDungeon();

            }
            else if(attackStatus == 0) {

                //no one died, continue.
                displayPlayerStatus();
                displayMonsterStatus();
            }

        }
    }

    public void onFleeButtonClick(View view) {
        goBackToDungeon();
    }

    public void goBackToDungeon()
    {
        //and return to dungeon
        //result the activity
        Intent goingBackToDungeon = new Intent();
        goingBackToDungeon.putExtra("Game_object",game);
        setResult(RESULT_OK, goingBackToDungeon);
        finish();
    }
}
