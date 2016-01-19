package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by polinominom on 16.01.2016.
 */
public class QuestActivity extends Activity {

    private Game game;

    private Spinner questDifficultySpinner;
    ListView questListView;

    private ArrayList<Quest> quests;
    private Quest selectedQuest;
    private Quest currentQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        getGameFromCalledActivity();

        displayCurrentQuest();
        addItemsToDifficultySpinner();
        addListenerToDifficultySpinner();
    }

    public void getGameFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inQuestActivity: " + isGameCame);

    }

    private void addItemsToDifficultySpinner() {
        questDifficultySpinner = (Spinner) findViewById(R.id.quest_difficulty_type_id);
        ArrayAdapter<CharSequence> questDifficultyAdapter =
                ArrayAdapter.createFromResource(this,R.array.quest_type,android.R.layout.simple_spinner_item);

        questDifficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        questDifficultySpinner.setAdapter(questDifficultyAdapter);

    }

    private void addListenerToDifficultySpinner() {

        questDifficultySpinner = (Spinner) findViewById(R.id.quest_difficulty_type_id);
        questDifficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDifficulty = parent.getItemAtPosition(position).toString();
                createQuestList(selectedDifficulty);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void createQuestList(String difficulty) {
        //empty the selected quest section
        emptySelectedQuest();

        //create quests by difficulty
        String[] questNames;
        game.createQuests(difficulty);
        quests = game.getQuests();

        //put quest names into the listview
        switch (difficulty) {
            case "Easy":
                questNames = getResources().getStringArray(R.array.easy_quests);
                break;

            case "Medium":
                questNames = getResources().getStringArray(R.array.medium_quests);
                break;

            case "Hard":
                questNames = getResources().getStringArray(R.array.hard_quests);
                break;

            case "Exceptional":
                questNames = getResources().getStringArray(R.array.exceptional_quests);
                break;

            case "Legendary":
                questNames = getResources().getStringArray(R.array.legendary_quests);
                break;

            default:
                questNames = null;
                break;
        }

        ListAdapter questListAdapter = new QuestListAdapter(this, questNames, difficulty);

        questListView = (ListView) findViewById(R.id.quest_list_id);

        questListView.setAdapter(questListAdapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //When a quest selected put all information about it into the textView
                String select = String.valueOf(parent.getItemAtPosition(position));

                //Easy Quest 1, first quest
                //but actually it is easy quest 0 so decreasing must be done after parse.
                String[] tokens = select.split("[ ]+");
                int questNum = Integer.parseInt(tokens[2]) - 1;


                displayCurrentQuest();
                displaySelectedQuest(questNum);

            }
        });


    }

    public void displaySelectedQuest(int questNum)
    {
        TextView selectedQuestTextView = (TextView) findViewById(R.id.selected_quest_text_view_id);

        //get selected and current quests
        selectedQuest = game.getQuests().get(questNum);

        String selectedQuestMessage = "Selected Quest";
        selectedQuestMessage += Quest.info(selectedQuest);

        selectedQuestTextView.setText(selectedQuestMessage);
    }

    public void displayCurrentQuest()
    {
        TextView currentQuestTextView = (TextView) findViewById(R.id.current_quest_text_view_id);
        currentQuest = game.getPlayer().getQuest();

        String currentQuestMessage = "Current Quest";
        currentQuestMessage += Quest.info(currentQuest);

        currentQuestTextView.setText(currentQuestMessage);
    }

    public void emptySelectedQuest()
    {
        TextView t = (TextView)findViewById(R.id.selected_quest_text_view_id);
        t.setText("");
        selectedQuest = null;
    }
    public void onBackToMapFromQuestClick(View view) {

        Intent goingMap = new Intent(this, PlayActivity.class);
        goingMap.putExtra("Game_object",game);
        startActivity(goingMap);
        finish();

    }


    public void onBackToDungeonFromQuestClick(View view) {
        Intent goingDungeon = new Intent(this, DungeonActivity.class);
        goingDungeon.putExtra("Game_object",game);
        startActivity(goingDungeon);
        finish();
    }

    public void onBackToTownFromQuestClick(View view) {
        Intent goingTown = new Intent(this, TownActivity.class);
        goingTown.putExtra("Game_object",game);
        startActivity(goingTown);
        finish();
    }

    public void onApplyQuestClick(View view) {

        if(selectedQuest == null)
        {
            Toast.makeText(QuestActivity.this,"You have to choose a quest first.",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Player p = game.getPlayer();
            p.setQuest(selectedQuest);
            displayCurrentQuest();
        }
    }
}
