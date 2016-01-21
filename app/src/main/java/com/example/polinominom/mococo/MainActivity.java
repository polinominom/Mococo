package com.example.polinominom.mococo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set game
        getGameFromCalledActivity();


    }
    public void getGameFromCalledActivity()
    {

        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG","inProfileIsGame: "+isGameCame);
        if(!isGameCame){
            Player p = new Player("Me","Assassin");
            game = new Game(p);
        }else
        {
            Log.v("DEBUG","inProfile: Race: "+game.getPlayer().getPlayerRace().getName()+"\nname: "+
                            game.getPlayer().getName());
        }
    }

    public void onPlayButtonClick(View view) {

        Log.v("play",game.getPlayer().getName());
        Intent goingToPlayActivity = new Intent(this, ProfileActivity.class);

        goingToPlayActivity.putExtra("Game_object",game);

        startActivity(goingToPlayActivity);
    }

    public void onOptionButtonClick(View view) {
        Intent optionIntent = new Intent(this,OptionActivity.class);

        optionIntent.putExtra("Game_object", game);

        startActivity(optionIntent);


    }


    public void onHowToPlayButtonClick(View view) {
        Log.v("how to play","clicked");

        Intent learnHowToPlay = new Intent(this, HowToPlayActivity.class);
        learnHowToPlay.putExtra("MainActivity","HowToPlayActivity");

        startActivity(learnHowToPlay);
    }

    public void onAboutButtonClick(View view) {
        Log.v("about","clicked");
        Intent goingToAboutActivity = new Intent(this, AboutActivity.class);

        goingToAboutActivity.putExtra("MainActivity","AboutActivity");

        startActivity(goingToAboutActivity);

    }

    public void onTestClick(View view) {

        setContentView(R.layout.test_layout);

    }
}
