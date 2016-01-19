package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;

/**
 * Created by polinominom on 15.01.2016.
 */
public class OptionActivity extends AppCompatActivity {
    //settings must be pass through called activity because of player's changed attributes
    private Game game;
    private String newUserName;
    private String newUserRace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Intent calledActivity = getIntent();

        //set game
        getGameFromCalledActivity();

        addItemsToRaceSpinner();
        addListenerToRaceSpinner();


    }

    public void addItemsToRaceSpinner()
    {
        Spinner raceSpinner = (Spinner)findViewById(R.id.race_spinner_id);
        ArrayAdapter<CharSequence> raceSpinnerAdapter =
                ArrayAdapter.createFromResource(this,R.array.race_types,android.R.layout.simple_spinner_item);

        raceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        raceSpinner.setAdapter(raceSpinnerAdapter);

    }

    public void addListenerToRaceSpinner()
    {
        Spinner raceSpinner = (Spinner)findViewById(R.id.race_spinner_id);
        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               newUserRace = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getGameFromCalledActivity()
    {

        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inProfileIsGame: " + isGameCame);
        if(!isGameCame){
            Player p = new Player("Me","Assassin");
            game = new Game(p);
        }
    }

    public void onConfirmButtonClick(View view) {



        EditText newUserNameET = (EditText) findViewById(R.id.edit_profile_name_id);
        String newUserName = String.valueOf(newUserNameET.getText());

        Player p = game.getPlayer();
        p.setPlayerRace(newUserRace);
        p.setName(newUserName);

        Intent goingBack = new Intent(this,MainActivity.class);
        goingBack.putExtra("Game_object", game);
        startActivity(goingBack);
        finish();

    }

    public void onCancelButtonClick(View view) {
        Intent goingBack = new Intent(this,MainActivity.class);
        goingBack.putExtra("Game_object",game);
        startActivity(goingBack);
        finish();

    }
}
