package com.example.polinominom.mococo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by polinominom on 19.01.2016.
 */
public class QuestDoneDialog extends DialogFragment {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

            theDialog.setTitle("Quest Finished");
            theDialog.setMessage("You've completed a quest!");

            theDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {


                }

            });

            return theDialog.create();
        }


}
