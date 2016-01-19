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
 * Created by polinominom on 17.01.2016.
 */
public class QuestListAdapter extends ArrayAdapter<String> {
    private String difficulty;
    public QuestListAdapter(Context context, String[] values, String difficulty) {
        //test
        super(context,R.layout.shop_armor_detail,values);
        if(difficulty == null) return;
        this.difficulty = difficulty;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.shop_armor_detail, parent, false);

        String questName = getItem(position);

        TextView theTextView = (TextView) theView.findViewById(R.id.armor_name);
        theTextView.setText(questName);

        ImageView theImageView = (ImageView) theView.findViewById(R.id.armor_icon_id);
        theImageView.setImageResource(R.drawable.quest_legendary_icon);

        return theView;

    }
}
