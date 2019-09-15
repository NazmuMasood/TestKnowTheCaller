package com.example.testknowthecaller;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.os.Handler;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

//import dootam.dspl.com.lawyercasecall.R;

public class CallReceiver extends PhonecallReceiver
{
    Context context;

    @Override
    protected void onIncomingCallStarted(final Context ctx, final String number, Date start)
    {
        Toast.makeText(ctx,"Nazmu Incoming Call"+ number,Toast.LENGTH_LONG).show();

        context =   ctx;

        final Intent intent = new Intent(context, MyCustomDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phone_no",number);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    System.out.println("Starting MyCustomDialog");
                    context.startActivity(intent);

                    /*----------G A P----------*/
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View dialogView = inflater.inflate(R.layout.activity_my_custom_dialog, null);
                    Button button = dialogView.findViewById(R.id.dialog_ok);
                    builder.setView(dialogView);
                    final AlertDialog alert = builder.create();
                    alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    alert.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
                    alert.setCanceledOnTouchOutside(false);
                    alert.setMessage("Incoming call from "+number);
                    alert.show();
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    Window window = alert.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    window.setGravity(Gravity.CENTER);
                    lp.copyFrom(window.getAttributes());
                    //This makes the dialog take up the full width
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //close the service and remove the from from the window
                            alert.dismiss();
                        }
                    });*/

                    /*----------G A P----------*/

                   /* LayoutInflater layoutInflater =
                            (LayoutInflater)context.getApplicationContext()
                                    .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = layoutInflater.inflate(R.layout.activity_my_custom_dialog, MyCustomDialog.class);
                    final PopupWindow popupWindow = new PopupWindow(
                            popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                    popupView.setOnTouchListener(new View.OnTouchListener() {
                        int orgX, orgY;
                        int offsetX, offsetY;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    orgX = (int) event.getX();
                                    orgY = (int) event.getY();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    offsetX = (int)event.getRawX() - orgX;
                                    offsetY = (int)event.getRawY() - orgY;
                                    popupWindow.update(offsetX, offsetY, -1, -1, true);
                                    break;
                            }
                            return true;
                        }});

                    Button button = popupView.findViewById(R.id.dialog_ok);
                    LinearLayout linearLayout = popupView.findViewById(R.id.linear_layout);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            popupWindow.dismiss();
                        }
                    });

                    System.out.println("Creating pop up");
                    popupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);*/

                }//End of the try block
                catch (Exception e){e.printStackTrace();}
            }
        },1500);

//        MyCus/*tomDialog dialog   =   new MyCustomDialog(context);
//        dialog.*/show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {
        Toast.makeText(ctx,"Bye Bye"+ number,Toast.LENGTH_LONG).show();
    }
}
