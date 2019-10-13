package com.example.testknowthecaller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.testknowthecaller.andreilisun.*;

public class customMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button addUserButton = (Button) findViewById(R.id.btn_add_user);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });

        Button showAlertButton = (Button) findViewById(R.id.btn_show_alert);
        showAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAddUserDialog() {
        View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);
        final SwipeDismissDialog swipeDismissDialog = new SwipeDismissDialog.Builder(this)
                .setView(dialog)
                .build()
                .show();
        final EditText usernameEditText = (EditText) dialog.findViewById(R.id.et_username);
        Button addButton = (Button) dialog.findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserSuccess(usernameEditText.getText().toString());
                swipeDismissDialog.dismiss();
            }
        });
        //My modification
        usernameEditText.setText("Mamammamamaaaaaa");
    }

    private void addUserSuccess(String username) {
        Toast.makeText(this, username + " has been added", Toast.LENGTH_SHORT).show();
    }

    private void showAlertDialog() {
        new SwipeDismissDialog.Builder(this)
                .setLayoutResId(R.layout.dialog_success_booking)
                .build()
                .show();
    }
}
