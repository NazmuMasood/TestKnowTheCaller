package com.example.testknowthecaller;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

//import android.os.Bundle;

public class MyCustomDialog extends Activity {

    TextView tv_client;
    String phone_no;
    Button dialog_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setFinishOnTouchOutside(false);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_custom_dialog);
            initializeContent();

            System.out.println("MyCustomDialog started");
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            if (Build.VERSION.SDK_INT >= 27) {
                setShowWhenLocked(true);
                setTurnScreenOn(true);
                ((KeyguardManager) getApplicationContext().getSystemService(KEYGUARD_SERVICE))
                        .requestDismissKeyguard(this, null);
            }
            else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        //| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                );
            }

            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setGravity(Gravity.CENTER);
            lp.copyFrom(window.getAttributes());
            //This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            //Not my comments
            /*WindowManager.LayoutParams params = getWindow().getAttributes();
            params.x = -100;
            params.height = 70;
            params.width = 1000;
            params.y = -50;

            this.getWindow().setAttributes(params);*/
            //Not my comments end

            phone_no    =   getIntent().getExtras().getString("phone_no");
            String info = ""+phone_no +" is calling you"
                            +"\nCaller type: Customer" +
                            "\nProducts List:\nLamp\nTable-Tennis Bat" +
                            "\nLa\nLa\nLa\nLa\nLa\nLa";
            tv_client.setText(info);

            dialog_ok.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    MyCustomDialog.this.finish();
//                    this.setFinishOnTouchOutside(false);
                    System.exit(0);
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Exception", e.toString());
            e.printStackTrace();
        }
    }

    private void initializeContent()
    {
        tv_client   = findViewById(R.id.tv_client);
        dialog_ok   = findViewById(R.id.dialog_ok);
    }
}
