package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by polinominom on 16.01.2016.
 */
public class UpgradeActivity extends Activity{
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        getGameFromCalledActivity();
        displayPlayerSIL();

    }

    public void displayPlayerSIL()
    {
        Player p = game.getPlayer();
        TextView strTextView = (TextView)findViewById(R.id.upgrade_strength_text_view);
        TextView intTextView = (TextView)findViewById(R.id.upgrade_intelligent_text_view);
        TextView luckTextView = (TextView)findViewById(R.id.upgrade_luck_text_view);
        TextView upgradePointTextView = (TextView)findViewById(R.id.upgrade_player_isl);

        strTextView.setText(String.format("%s %d", getString(R.string.str_status_name), p.getStrength()));
        intTextView.setText(String.format("%s %d", getString(R.string.int_status_name),p.getIntelligent()));
        luckTextView.setText(String.format("%s %d",getString(R.string.luck_status_name),p.getLuck()));

        upgradePointTextView.setText(String.format("Upgrade Points: %d", p.getUpgradePoint()));


    }

    public void getGameFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inUpgradeActivity: " + isGameCame);
    }


    public void onBuyStrClick(View view){
        Player p = game.getPlayer();
        int uPoint = p.getUpgradePoint();

        if(uPoint>0){
            p.setUpgradePoint(uPoint-1);
            p.setStrength(p.getStrength() + 1);
            displayPlayerSIL();
        }
        else {
            Toast.makeText(UpgradeActivity.this,"You don't have upgrade points!",Toast.LENGTH_SHORT).show();
        }


    }
    public void onBuyIntClick(View view){
        Player p = game.getPlayer();
        int uPoint = p.getUpgradePoint();

        if(uPoint>0){
            p.setUpgradePoint(uPoint-1);
            p.setIntelligent(p.getIntelligent() + 1);
            displayPlayerSIL();
        }
        else {
            Toast.makeText(UpgradeActivity.this,"You don't have upgrade points!",Toast.LENGTH_SHORT).show();
        }

    }

    public void onBuyLuckClick(View view){
        Player p = game.getPlayer();
        int uPoint = p.getUpgradePoint();

        if(uPoint>0){
            p.setUpgradePoint(uPoint-1);
            p.setLuck(p.getLuck() + 1);
            displayPlayerSIL();
        }
        else {
            Toast.makeText(UpgradeActivity.this,"You don't have upgrade points!",Toast.LENGTH_SHORT).show();
        }

    }

    public void onProfileButtonClick(View view) {
        Intent goingProfile = new Intent(this, ProfileActivity.class);
        goingProfile.putExtra("Game_object",game);
        startActivity(goingProfile);
        finish();
    }


    public void onDungeonButtonClick(View view) {
        Intent goingDungeon = new Intent(this, DungeonActivity.class);
        goingDungeon.putExtra("Game_object",game);
        startActivity(goingDungeon);
        finish();
    }

    public void onQuestButtonClick(View view) {
        Intent goingQuest = new Intent(this, QuestActivity.class);
        goingQuest.putExtra("Game_object",game);
        startActivity(goingQuest);
        finish();
    }


    public void onShopButtonClick(View view) {
        Intent goingShop = new Intent(this, ShopActivity.class);
        goingShop.putExtra("Game_object",game);
        startActivity(goingShop);
        finish();
    }

    public void onUpgradeButtonClick(View view) {
        //empty
    }
}
