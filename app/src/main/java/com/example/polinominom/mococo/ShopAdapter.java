package com.example.polinominom.mococo;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by polinominom on 17.01.2016.
 */


public class ShopAdapter extends ArrayAdapter<String> {

    private String type;

    public ShopAdapter(Context context, String[] values,String shopType) {
        super(context, R.layout.shop_weapn_detail_layout,values);
        type = shopType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView;
        TextView theTextView;
        ImageView theImageView;
        if(type.equals("Weapon")){
            theView= theInflater.inflate(R.layout.shop_weapn_detail_layout, parent, false);

            String weaponName = getItem(position);

            theTextView = (TextView) theView.findViewById(R.id.weapon_name);
            theTextView.setText(weaponName);

            theImageView = (ImageView) theView.findViewById(R.id.wapon_icon_id);
            theImageView.setImageResource(R.drawable.weapon_icon);
        }
        else if(type.equals("Armor")) {
            theView = theInflater.inflate(R.layout.shop_armor_detail, parent, false);

            String armorName = getItem(position);

            theTextView = (TextView) theView.findViewById(R.id.armor_name);
            theTextView.setText(armorName);

            theImageView = (ImageView) theView.findViewById(R.id.armor_icon_id);
            theImageView.setImageResource(R.drawable.shop_icon);
        }else
            return null;



        return theView;



    }
}
