package com.example.universityapplication.model;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class CustomAlertDialog {
    public void alertOneButton() {

      /*  new AlertDialog.Builder(ActivityCompat)
                .setTitle("One Button")
                .setMessage("The Code of a Ninja is your new favorite website.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Thank you!");
                        dialog.cancel();
                    }
                }).show();*/
    }
  /*  Context
    public void createSingleListDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder();
        builder.setTitle(title)
                .setIcon(R.drawable.ic_attach_money_black_24dp)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, items[which], Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }*/
}
