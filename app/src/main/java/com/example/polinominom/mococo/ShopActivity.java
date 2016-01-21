package com.example.polinominom.mococo;

import android.app.Activity;
import android.content.Context;
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
    private Spinner shopTypeSpinner;
    private ListAdapter shopListAdapter = null;
    private ListView shopListView = null;
    private String selectedItemNameFromList;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getGameFromCalledActivity();
        displayPlayerGold();

        context = this;

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

                //go to new activity
                selectedItemNameFromList = String.valueOf(parent.getItemAtPosition(position));


                Intent buy = new Intent(context,BuyingActivity.class);
                buy.putExtra("Game_object",game);
                buy.putExtra("Is_weapon",isWeapon);
                buy.putExtra("Item_name",selectedItemNameFromList);

                final int result = 1;
                startActivityForResult(buy,result);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null) {
            Log.v("ShopActivity", "result data null");
            return;
        }


        game = (Game)data.getSerializableExtra("Game_object");
        displayPlayerGold();


    }

    public void onProfileButtonClick(View view) {
        Intent goingProfile = new Intent(this,ProfileActivity.class);
        goingProfile.putExtra("Game_object",game);
        startActivity(goingProfile);
        finish();
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
        //empty
    }

    public void onUpgradeButtonClick(View view) {
        Intent goingUpgrade = new Intent(this,UpgradeActivity.class);
        goingUpgrade.putExtra("Game_object",game);
        startActivity(goingUpgrade);
        finish();
    }
}
