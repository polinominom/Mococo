package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by polinominom on 16.01.2016.
 */
public class PlayActivity extends Activity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getGameFromCalledActivity();



    }

    public void getGameFromCalledActivity()
    {

        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG","inMapActivity: "+isGameCame);
    }

    public void onProfileButtonClick(View view) {
        final int result = 1;
        Intent goingToProfile = new Intent(this, ProfileActivity.class);
        goingToProfile.putExtra("Game_object",game);
        startActivityForResult(goingToProfile, result);
        finish();

    }

    public void onTownButtonClick(View view) {
        Intent goToTown = new Intent(this, TownActivity.class);
        goToTown.putExtra("Game_object",game);

        startActivity(goToTown);
        finish();

    }

    public void onDungeonButtonClick(View view) {
        Intent goingToDungeon = new Intent(this, DungeonActivity.class);
        goingToDungeon.putExtra("Game_object",game);
        startActivity(goingToDungeon);
        finish();

    }


    public void onBackToMenuButtonClick(View view) {
        Intent goingBack = new Intent(this,MainActivity.class);
        goingBack.putExtra("Game_object", game);
        startActivity(goingBack);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v("DEBUG","RequestCode: "+requestCode);
        Log.v("DEBUG","resultCode: "+resultCode);
        Log.v("DEBUG","data: "+data.getStringExtra("Game_object"));

    }
}
