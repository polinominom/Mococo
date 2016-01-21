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
 *
 */


public class ShopAdapter extends ArrayAdapter<String> {

    private String type;
    private int price;

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

        theView = theInflater.inflate(R.layout.row_monster_detail_layout, parent, false);

        String weaponName = getItem(position);

        theTextView = (TextView) theView.findViewById(R.id.weapon_name);
        theTextView.setText(weaponName);

        theImageView = (ImageView) theView.findViewById(R.id.wapon_icon_id);




        switch (type) {

            case "Weapon":
                theImageView.setImageResource(R.drawable.weapon_icon);
                price = Weapon.findPrice(weaponName);
                break;

            case "Armor":
                theImageView.setImageResource(R.drawable.shop_icon);
                price = Armor.findPrice(weaponName);
                break;

            default:
                return null;

        }

        return theView;

    }
}
