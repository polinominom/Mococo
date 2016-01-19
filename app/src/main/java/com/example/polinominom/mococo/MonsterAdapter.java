package com.example.polinominom.mococo;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by polinominom on 16.01.2016.
 */
public class MonsterAdapter extends ArrayAdapter<String> {
    public MonsterAdapter(Context context, String[] values) {
        super(context, R.layout.shop_weapn_detail_layout,values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.shop_weapn_detail_layout, parent, false);

        String monster = getItem(position);

        TextView information = (TextView) theView.findViewById(R.id.weapon_name);

        information.setText(monster);

        ImageView theImageView = (ImageView) theView.findViewById(R.id.wapon_icon_id);

        theImageView.setImageResource(R.drawable.monster_icon);


        /*if(monster.equals("Orc Warrior")) {
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.orc_warrior);

            information.append("");
        }
        else if(monster.equals("Orc Archer")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.orc_archer);

        }
        else if(monster.equals("Goblin Soldier")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.goblin_soldier);

        }
        else if(monster.equals("Goblin Archer")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.goblin_archer);
        }
        else if(monster.equals("Goblin Warrior")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.orc_2);

        }*/
        /*
        else if(monster.equals("Archmage")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.archmage);

        }
        else if(monster.equals("Fallen Angel")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.fallen_angel);

        }
        else if(monster.equals("Dragon")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.dragon_black_big);

        }
        else if(monster.equals("Dark Knight")){
            ImageView theImageView = (ImageView) theView.findViewById(R.id.image1);

            theImageView.setImageResource(R.drawable.dark_knight);

        } */




        return theView;

    }
}


