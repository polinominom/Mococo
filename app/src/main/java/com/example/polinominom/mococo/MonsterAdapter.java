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

        View theView = theInflater.inflate(R.layout.row_monster_detail_layout, parent, false);

        String monster = getItem(position);

        TextView information = (TextView) theView.findViewById(R.id.weapon_name);

        information.setText(monster);

        ImageView theImageView = (ImageView) theView.findViewById(R.id.wapon_icon_id);

        theImageView.setImageResource(R.drawable.monster_icon);

        return theView;

    }
}


