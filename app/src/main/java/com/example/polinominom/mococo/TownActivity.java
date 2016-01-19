package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by polinominom on 16.01.2016.
 */
public class TownActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        //set game
        getGameFromCalledActivity();

    }

    public void getGameFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inTownActivity: " + isGameCame);
    }

    public void onBackToMapFromProfileClick(View view) {

        Intent goingBackToMap = new Intent(this,PlayActivity.class);
        goingBackToMap.putExtra("Game_object", game);
        startActivity(goingBackToMap);
        finish();
    }


    public void onQuestButtonClick(View view) {
        Intent goingToQuestList = new Intent(this, QuestActivity.class);
        goingToQuestList.putExtra("Game_object",game);
        startActivity(goingToQuestList);
        finish();

    }

    public void onUpgradeButtonClick(View view) {

        Intent goingUpgradeActivity = new Intent(this, UpgradeActivity.class);
        goingUpgradeActivity.putExtra("Game_object",game);
        startActivity(goingUpgradeActivity);
        finish();

    }

    public void onShopButtonClick(View view) {
        Intent goShopping = new Intent(this, ShopActivity.class);
        goShopping.putExtra("Game_object",game);
        startActivity(goShopping);
        finish();
    }
}
