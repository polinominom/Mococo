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
public class ProfileActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set game
        getGameFromCalledActivity();
        setInformationToTextView();
    }

    public void getGameFromCalledActivity()
    {

        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inProfileActivity: " + isGameCame);
    }

    public void setInformationToTextView()
    {
        TextView profileTextView = (TextView)findViewById(R.id.profile_text_view_id);
        Player p = game.getPlayer();

        String playerStatus = p.profileStatus();
        profileTextView.setText(playerStatus);

    }

    public void onHealClick(View view) {
        Player p = game.getPlayer();
        if(p.getGold()>=1)
        {
            if(p.getCurrentHealth() < p.getMaxHealth())
            {
                p.setCurrentHealth(p.getMaxHealth());
                p.setGold(p.getGold() - 1);
                setInformationToTextView();
                //save the player after heal
                Player.savePlayerToJSON(p,this);

            }
            else
            {
                //wtf are you doing
                Toast.makeText(ProfileActivity.this,"Your health is already full",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //not enough money
            Toast.makeText(ProfileActivity.this,"You don't have enough money to heal yourself!",Toast.LENGTH_SHORT).show();
        }


    }

    public void onProfileButtonClick(View view) {
        //empty

    }


    public void onDungeonButtonClick(View view) {
        Intent goingDungeon = new Intent(this,DungeonActivity.class);
        goingDungeon.putExtra("Game_object",game);
        startActivity(goingDungeon);
        finish();
    }

    public void onQuestButtonClick(View view) {

        Intent goingQuest = new Intent(this,QuestActivity.class);
        goingQuest.putExtra("Game_object",game);
        startActivity(goingQuest);
        finish();
    }

    public void onShopButtonClick(View view) {

        Intent goingShop = new Intent(this,ShopActivity.class);
        goingShop.putExtra("Game_object",game);
        startActivity(goingShop);
        finish();

    }

    public void onUpgradeButtonClick(View view) {

        Intent goingUpgrade = new Intent(this,UpgradeActivity.class);
        goingUpgrade.putExtra("Game_object",game);
        startActivity(goingUpgrade);
        finish();
    }
}
