package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AccessControlContext;

/**
 * Created by polinominom on 21.01.2016.
 */
public class BuyingActivity extends Activity {

    private Game game;
    private String selectedItem;
    private TextView statusTextView;
    private boolean isWeapon;
    private Weapon selectedWeapon;
    private Armor selectedArmor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        getObjectsFromCalledActivity();

        displayItemStatus();
        displayPlayersItemStatus();

    }

    public void getObjectsFromCalledActivity()
    {
        game = (Game) getIntent().getSerializableExtra("Game_object");
        isWeapon = getIntent().getBooleanExtra("Is_weapon",false);
        selectedItem = getIntent().getStringExtra("Item_name");

        boolean isGameCame = (game != null);

        //set messages to log
        Log.v("BuyingActivity", "isGameCame:" + isGameCame);
        Log.v("BuyingActivity", "isWeaponCame: " + isWeapon);
        Log.v("BuyingActivity", "isArmorCame: " + !isWeapon);
    }

    public void displayItemStatus()
    {
        String shopItemInformation = getInformationFromSelectedItem(isWeapon, selectedItem);

        statusTextView = (TextView) findViewById(R.id.selected_item_info_id);
        statusTextView.setText(String.format("Selected Item\n %s", shopItemInformation));

    }

    public void displayPlayersItemStatus()
    {
        if(game == null) return;
        String playerItemInfo = getInformationFromPlayerItem(isWeapon, selectedItem);

        statusTextView = (TextView)findViewById(R.id.player_item_info_id);
        statusTextView.setText(String.format("Player's Item\n%s", playerItemInfo));

        statusTextView = (TextView)findViewById(R.id.player_gold_info);
        statusTextView.setText(String.format("Your current gold: %d", game.getPlayer().getGold()));

    }

    public String getInformationFromSelectedItem(boolean isWeapon, String selectedItemName)
    {
        String s = "";

        if(isWeapon) {
            Weapon w = new Weapon(selectedItemName);

            selectedArmor = null;
            selectedWeapon = w;

            s += "\nItem Type: Weapon";
            s += w.shopStatus();

        }
        else {
            Armor a = new Armor(selectedItemName);

            selectedWeapon = null;
            selectedArmor = a;

            s += "\nItem Type: Armor";
            s += a.shopStatus();
        }

        return s;
    }

    public String getInformationFromPlayerItem(boolean isWeapon,String selectedItemName)
    {
        String s = "";
        Player p = game.getPlayer();

        if(isWeapon)
        {
            s += p.weaponShopStatus();
        }
        else//armor
        {
            s += p.armorShopStatus(selectedItemName);
        }

        return s;

    }

    public void onBuyItemButtonClick(View view) {
        Player p = game.getPlayer();
        int playerGold = p.getGold();
        int itemGold;

        if(selectedArmor == null && selectedWeapon == null)
        {
            Toast.makeText(BuyingActivity.this, "You have to choose an item first.", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            if(selectedWeapon != null)
            {
                //control player's gold

                itemGold = selectedWeapon.getGold();

                if(playerGold<itemGold)
                {
                    Toast.makeText(BuyingActivity.this,"Sorry, you don't have enough gold.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    p.setGold(playerGold - itemGold);
                    p.setWeapon(selectedWeapon);
                }
            }
            else
            {
                itemGold = selectedArmor.getPrice();

                if(playerGold<itemGold)
                {
                    Toast.makeText(BuyingActivity.this,"Sorry, you don't have enough gold.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    p.setGold(playerGold - itemGold);
                    p.setArmor(selectedArmor);

                }
            }

            //display new item before leave
            displayPlayersItemStatus();

            //go back
            goBackToShop();

        }


    }

    public void onCancelButtonClick(View view) {
        goBackToShop();
    }

    public void goBackToShop()
    {
        Intent goBackToShop = new Intent();
        goBackToShop.putExtra("Game_object",game);

        setResult(RESULT_OK,goBackToShop);
        finish();

    }
}
