package com.example.testknowthecaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testknowthecaller.andreilisun.SwipeDismissDialog;

public class TransparentActivity extends Activity {
    Button testButton; TextView testTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Lets the activity pop up on top of every application
        //WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        //lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        //this.getWindow().setAttributes(lp);

        // Pass touch events to the background activity
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        );
        if (Build.VERSION.SDK_INT >= 27) {
            setShowWhenLocked(true);
        }
        else {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
            );
        }
        setContentView(R.layout.activity_transparent);
        testButton = findViewById(R.id.testButton);
        testTV = findViewById(R.id.testTV);
        testTV.setText("Mama me test");
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Add dialog to this transparent activity
        View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null);
        final SwipeDismissDialog swipeDismissDialog = new SwipeDismissDialog.Builder(this)
                .setView(dialog)
                .build()
                .show();
        final EditText usernameEditText = dialog.findViewById(R.id.et_username);
        Button addButton = dialog.findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeDismissDialog.dismiss();
                finish();
            }
        });
        //My modification
        usernameEditText.setText("Mamammamamaaaaaa");
        usernameEditText.setFocusable(false);
    }


    /*@Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }*/
}
