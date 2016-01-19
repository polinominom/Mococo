package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaCodecInfo;
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

import org.w3c.dom.Text;

import java.security.AccessControlContext;
import java.util.ArrayList;

/**
 * Created by polinominom on 16.01.2016.
 */
public class ShopActivity extends Activity {

    private Game game;
    private Weapon selectedWeapon;
    private Armor selectedArmor;
    private Spinner shopTypeSpinner;
    private ListAdapter shopListAdapter = null;
    private ListView shopListView = null;
    private String selectedItemNameFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getGameFromCalledActivity();

        displayPlayerGold();

        addItemsToShopTypeSpinner();
        addListenerToShopTypeSpinner();
    }

    public void displayPlayerGold()
    {
        TextView goldTextView = (TextView)findViewById(R.id.gold_text_view);
        goldTextView.setText(String.format("Your Gold: %d", game.getPlayer().getGold()));

    }

    public void getGameFromCalledActivity()
    {
        game = (Game)getIntent().getSerializableExtra("Game_object");
        boolean isGameCame = (game!=null);
        Log.v("DEBUG", "inShoActivity: " + isGameCame);

    }

    public void addItemsToShopTypeSpinner()
    {

        shopTypeSpinner = (Spinner) findViewById(R.id.shop_type_id);

        ArrayAdapter<CharSequence> shopTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.shop_type,android.R.layout.simple_spinner_item);

        shopTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        shopTypeSpinner.setAdapter(shopTypeSpinnerAdapter);



    }

    public void addListenerToShopTypeSpinner(){

        shopTypeSpinner = (Spinner)findViewById(R.id.shop_type_id);
        shopTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                createShopList(selectedItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void createShopList(String selectedSpinnerItem){

        final boolean isWeapon = selectedSpinnerItem.equals("Weapon");

        if(isWeapon){
            //test
            String[] weaponNames = getResources().getStringArray(R.array.weapon_shop);
            shopListAdapter = new ShopAdapter(this,weaponNames,"Weapon");
        }
        else /*if(selectedSpinnerItem.equals("Armor"))*/{
            String[] armorNames = getResources().getStringArray(R.array.armor_shop);
            shopListAdapter = new ShopAdapter(this,armorNames,"Armor");
        }
        shopListView = (ListView)findViewById(R.id.shop_list_id);
        shopListView.setAdapter(shopListAdapter);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String shopItemInformation = "";
                String playerItemInfo = "";
                selectedItemNameFromList = String.valueOf(parent.getItemAtPosition(position));

                //get text views from screen
                TextView selectedListItemTW = (TextView) findViewById(R.id.selected_list_item_id);
                TextView playersItemTW = (TextView) findViewById(R.id.players_item_id);

                //get messages
                shopItemInformation += getInformationFromSelectedItem(isWeapon, selectedItemNameFromList);
                playerItemInfo += getInformationFromPlayerItem(isWeapon,selectedItemNameFromList);

                //set messages to screen
                selectedListItemTW.setText(shopItemInformation);
                playersItemTW.setText(playerItemInfo);


            }
        });


    }

    public String getInformationFromSelectedItem(boolean isWeapon,String selectedItemName)
    {
        String s = "";

        if(isWeapon) {
            Weapon w = new Weapon(selectedItemName);

            selectedArmor = null;
            selectedWeapon = w;

            s += "Selected Weapon";
            s += w.shopStatus();

        }
        else {
            Armor a = new Armor(selectedItemName);

            selectedWeapon = null;
            selectedArmor = a;

            s += "Selected Armor";
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

    public void onBackToMapFromShopClick(View view) {

        Intent goingBackToProfile = new Intent(this, PlayActivity.class);
        goingBackToProfile.putExtra("Game_object",game);
        startActivity(goingBackToProfile);
        finish();
    }

    public void onBackToProfileFromShopClick(View view) {
        Intent goingBackToProfile = new Intent(this, ProfileActivity.class);
        goingBackToProfile.putExtra("Game_object",game);
        startActivity(goingBackToProfile);
        finish();
    }

    public void onBackToTownFromShopClick(View view) {


        Intent goingBackToTown = new Intent(this, TownActivity.class);
        goingBackToTown.putExtra("Game_object",game);
        startActivity(goingBackToTown);
        finish();

    }

    public void onShopBuyClick(View view) {
        Player p = game.getPlayer();
        int playerGold = p.getGold();
        int itemGold;

        if(selectedArmor == null && selectedWeapon == null)
        {
            Toast.makeText(ShopActivity.this,"You have to choose an item first.",Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            if(selectedWeapon != null)
            {
                //control player's gold

                itemGold = selectedWeapon.getGold();

                if(playerGold<itemGold)
                {
                    Toast.makeText(ShopActivity.this,"Sorry, you don't have enough gold.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    p.setGold(playerGold-itemGold);
                    p.setWeapon(selectedWeapon);
                    displayPlayerGold();
                }
            }
            else if(selectedArmor != null)
            {
                itemGold = selectedArmor.getPrice();

                if(playerGold<itemGold)
                {
                    Toast.makeText(ShopActivity.this,"Sorry, you don't have enough gold.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    p.setGold(playerGold-itemGold);
                    p.setArmor(selectedArmor);
                    displayPlayerGold();
                }
            }


            boolean isWeapon = (selectedWeapon != null);
            TextView playersItemTW = (TextView) findViewById(R.id.players_item_id);
            String playerItemInfo = getInformationFromPlayerItem(isWeapon,selectedItemNameFromList);


            playersItemTW.setText(playerItemInfo);
        }



    }
}
