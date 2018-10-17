package com.example.jeffreyjin.binderservice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ServiceConfigurationError;

@SuppressLint("Registered")
public class ActivityB extends Activity implements Button.OnClickListener {
    private MyService service = null;
    private boolean isBind = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected( ComponentName componentName, IBinder iBinder ) {
            isBind = true;
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            service =myBinder.getService();
            Log.i( "J","123"+Thread.currentThread().getId() );

            int num=service.getRandomNumber();
            Log.i( "J","123"+num);
        }
        @Override
        public void onServiceDisconnected( ComponentName componentName ) {

            isBind=false;
            Log.i( "j","ActivityA --onServiceDisconnected" );
        }
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_b );

        findViewById( R.id.btnBindService ).setOnClickListener( this );
        findViewById( R.id.btnUnbindService ).setOnClickListener( this );
        findViewById( R.id.btnFinish ).setOnClickListener( this );
    }

    @Override
    public void onClick( View v ) {
        if(v.getId()==R.id.btnBindService) {
            Intent intent = new Intent( this, MyService.class );
            intent.putExtra( "from", "ActivityB" );
            Log.i( "J", "--------------------------------" );
            Log.i( "J", "ActivityB 执行 bindservice" );
            bindService( intent, conn, BIND_AUTO_CREATE );
        }else if (v.getId()==R.id.btnUnbindService){
            if(isBind) {
                Log.i( "J", "----------------" );
                Log.i( "J", "ActivityB执行btnUnbindServices" );
                unbindService( conn );
            }

            }else if (v.getId()==R.id.btnFinish){
            Log.i( "J","--------------------------" );
            Log.i( "J","ActivityB执行finsh" );
            this.finish();
        }

    }
    public void onDestroy(){
        super.onDestroy();
        Log.i( "J","ActivityB-onDestroy" );
    }
}
