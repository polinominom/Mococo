package com.example.polinominom.mococo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by polinominom on 18.01.2016.
 */
public class DeadDialog extends DialogFragment {
    private Game g;
    private Context c;

    public DeadDialog(Game g, Context c) {
        super();
        this.g = g;
        this.c = c;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("You died");
        theDialog.setMessage("A monster killed you during attack." +
                "Game is restarting");

        theDialog.setCancelable(false);
        theDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


                Intent dead = new Intent(c,MainActivity.class);
                dead.putExtra("Game_object", g);
                getActivity().finish();
                startActivity(dead);

            }

        });

        return theDialog.create();

    }
}
