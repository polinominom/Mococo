package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by polinominom on 21.01.2016.
 */
public class QuestInfoActivity extends Activity {
    private Game game;

    private ArrayList<Quest> quests;

    private Quest selectedQuest;
    private Quest currentQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_info);

        getObjectsFromCalledActivity();

        displayPlayerQuestInfo();
        displaySelectedQuestInfo();
    }

    public void getObjectsFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        selectedQuest = (Quest)getIntent().getSerializableExtra("Quest_selected");
        currentQuest = game.getPlayer().getQuest();

        boolean isGameCame = (game != null);
        boolean isQuestCame = (selectedQuest != null);

        //set messages to log
        Log.v("QuestActivity", "isGameCame:" + isGameCame);
        Log.v("QuestActivity", "isQuestCame: " + isQuestCame);
    }


    public void displayPlayerQuestInfo()
    {
        TextView info = (TextView)findViewById(R.id.player_quest_info);
        String message = "Current Quest";
        message += Quest.info(currentQuest);

        info.setText(message);

    }


    public void displaySelectedQuestInfo()
    {
        TextView info = (TextView)findViewById(R.id.selected_quest_info);
        String message = "Selected Quest";
        message += Quest.info(selectedQuest);

        info.setText(message);
    }


    public void onTakeButtonClick(View view) {

        if(selectedQuest == null)
        {
            Toast.makeText(QuestInfoActivity.this, "You have to choose a quest first.", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Player p = game.getPlayer();
            p.setQuest(selectedQuest);

            //display quest info then save the player
            displayPlayerQuestInfo();
            Player.savePlayerToJSON(p,this);

            Intent backToQuests = new Intent();
            backToQuests.putExtra("Game_object",game);

            setResult(RESULT_OK, backToQuests);
            finish();

        }
    }


    public void onLeaveButtonClick(View view) {

        Intent backToQuests = new Intent();
        backToQuests.putExtra("Game_object",game);

        setResult(RESULT_OK,backToQuests);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent goingBackToQuest = new Intent();
        goingBackToQuest.putExtra("Game_object",game);
        setResult(RESULT_OK, goingBackToQuest);
        finish();
    }

}
