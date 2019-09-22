package com.example.testknowthecaller;

import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

//import android.os.Bundle;

public class MyCustomDialog extends Activity implements View.OnTouchListener{

    TextView tv_client;
    String phone_no;
    Button dialog_ok; LinearLayout rootLayout; LinearLayout rootsSuccessorLayout;
    private int mCurrentX, mCurrentY;
    private float mDx;
    private float mDy;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        makeToast("Helloouu");
        return true;
    }

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
            //This flag makes the background of the activity touchable
            //window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            //This flag takes outside touch event and pass to onTouchEvent
            //window.addFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

            if (Build.VERSION.SDK_INT >= 27) {
                setShowWhenLocked(true);
                //setTurnScreenOn(true);
                //window.addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);

                //The below line of code isn't seem to be necessary in Android 9 as..
                //..the keyguard is auto dismissed
                //KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
                //keyguardManager.requestDismissKeyguard(this, null);
                //((KeyguardManager) MyCustomDialog.this.getSystemService(KEYGUARD_SERVICE))
                     //   .requestDismissKeyguard(this, null);
            }
            else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        //| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        //| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        //| WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                );
            }

            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setGravity(Gravity.CENTER);
            lp.copyFrom(window.getAttributes());
            //This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //lp.format = PixelFormat.TRANSPARENT;
            window.setAttributes(lp);

            //Not my comments
            /*WindowManager.LayoutParams params = getWindow().getAttributes();
            params.x = -100;
            params.height = 70;
            params.width = 1000;
            params.y = -50;

            this.getWindow().setAttributes(params);*/
            //Not my comments end

            WindowManager.LayoutParams params = getWindow().getAttributes();
            mCurrentX = params.x;
            mCurrentY = params.y;

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

//        rootLayout = findViewById(R.id.root_linear_layout);
//        rootLayout.setOnTouchListener(this);
//        rootsSuccessorLayout = findViewById(R.id.roots_successor_linear_layout);
////        rootsSuccessorLayout.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                makeToast("Clicked [onClick]");
////            }
////        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        makeToast("Clicked [onTouch]");
        return true;
    }

    private void makeToast(String message){Toast.makeText(this, message,
            Toast.LENGTH_SHORT)
            .show();}

    private void initializeContent()
    {
        tv_client   = findViewById(R.id.tv_client);
        dialog_ok   = findViewById(R.id.dialog_ok);
    }

    /*@Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "Touch Detected",
                Toast.LENGTH_SHORT).show();
        System.out.println("Touch detected");
//        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
//            Toast.makeText(this, "Touch ActionDown"
//                            +event.getRawX()+" "+event.getRawY(),
//                    Toast.LENGTH_SHORT).show();
//            System.out.println("Touch ActionDown"
//                    +event.getRawX()+" "+event.getRawY());
//            mDx = mCurrentX - event.getRawX();
//            mDy = mCurrentY - event.getRawY();
//        }
//        if (action == MotionEvent.ACTION_MOVE) {
//            Toast.makeText(this, "Touch ActionMove"
//                            +event.getRawX()+" "+event.getRawY(),
//                    Toast.LENGTH_SHORT).show();
//            System.out.println("Touch ActionMove"
//                    +event.getRawX()+" "+event.getRawY());
//            mCurrentX = (int) (event.getRawX() + mDx);
//            mCurrentY = (int) (event.getRawY() + mDy);
//
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.x = mCurrentX; params.y = mCurrentY;
//            this.getWindow().setAttributes(params);
//        }
        return true;
    }*/

}
