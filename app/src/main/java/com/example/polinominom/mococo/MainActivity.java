package com.example.polinominom.mococo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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
        Log.v("DEBUG", "inProfileIsGame: " + isGameCame);
        if(!isGameCame){
            Player p = getPlayerFromJSON();
            game = new Game(p);
        }else
        {
            Log.v("DEBUG","inProfile: Race: "
                    +game.getPlayer().getPlayerRace().getName()
                    +"\nname: "
                    +game.getPlayer().getName());
        }
    }

    public Player getPlayerFromJSON() {

        Player p = null;

        p = Player.loadPlayerFromJson(loadFileFromInternalStorage());


        return p;

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

    public String loadJSONFromAsset()
    {

        String json;
        try {

            InputStream is = getAssets().open("player_stats.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

            Log.i("DEBUG",json);


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String loadJSONFromAssetToInternalStorage()
    {
        String json = loadJSONFromAsset();
        FileOutputStream file;

        try {
            //copy asset into internal storage
            file = openFileOutput("player_stats.json", Context.MODE_PRIVATE);
            file.write(json.getBytes());
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;


    }

    public String loadFileFromInternalStorage()
    {
        String json = null;
        File f = new File(getFilesDir(),"player_stats.json");

        try {

            InputStream is  = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            Log.v("JSON_DEBUG",json);

        } catch (FileNotFoundException e) {

            json = loadJSONFromAssetToInternalStorage();

        } catch (IOException e) {

            e.printStackTrace();

        }


        return json;

    }


}
