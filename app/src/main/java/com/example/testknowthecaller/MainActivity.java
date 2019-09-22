package com.example.testknowthecaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testknowthecaller.andreilisun.SwipeDismissDialog;

public class MainActivity extends AppCompatActivity {

    Button seekPermission; TextView permissionStatusTV; TextView appTitle;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checks for permission to show dialog box i.e. permit draw over other apps
        checkPermission();

        //App title as there's no action bar
        appTitle = findViewById(R.id.appTitle);
        String stringAppTitle = "TestKnowTheCaller App"; appTitle.setText(stringAppTitle);

        //Button for asking permission to monitor phone state
        seekPermission = findViewById(R.id.seekPermissionButton);
        seekPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekPermission();
            }
        });

        //Permission status which shows if permission is granted or not
        permissionStatusTV = findViewById(R.id.permissionStatusTV);

        Button openDialog = findViewById(R.id.openDialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestMyCustomDialog.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //intent.putExtra("phone_no", "Your friend");
                //intent.putExtra("turnScreenOff", "no");
                startActivity(intent);

                //showMyDialog();
            }
        });
    }

    private void showMyDialog(){
        View dialog = LayoutInflater.from(this).inflate(R.layout.temp_dialog, null);
        final SwipeDismissDialog swipeDismissDialog = new SwipeDismissDialog.Builder(this)
                .setView(dialog)
                .build()
                .show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void seekPermission(){
        //Checks for permission to show dialog box
        if(!Settings.canDrawOverlays(this)) {
            checkPermission();
        }
        //Checks for permission to monitor phone state
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }
        //Checks for permission to monitor call log
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
        //Checks for permission to monitor outgoing calls
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},1);
        }
        //Checks for permission to acquire wake locks
        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WAKE_LOCK},1);
        }*/

        updatePermissionStatus();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void updatePermissionStatus(){
        String s;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS)
                        == PackageManager.PERMISSION_GRANTED&& Settings.canDrawOverlays(this)){

            s = "[Permission granted]";
            permissionStatusTV.setText(s);

        }
        else{
            s = "[No permission yet]";
            permissionStatusTV.setText(s);

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // You don't have permission
                checkPermission();
            } else {
                // Do as per your logic
            }

        }

    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }
}
