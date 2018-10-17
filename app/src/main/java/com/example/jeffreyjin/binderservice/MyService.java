package com.example.jeffreyjin.binderservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

@SuppressLint("Registered")
public class MyService extends Service {


    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
//通过binder实现点用着
    private MyBinder binder =new MyBinder();

    private final Random generator=new Random();

    public void onCreate(){
        Log.i("J","123"+Thread.currentThread().getId());
        super.onCreate();
    }
    public int onStartCommand(Intent intent,int flage,int startId){
        Log.i( "J","123"+Thread.currentThread().getId() );
        return START_NOT_STICKY;

    }
    @Nullable


    @Override
    public IBinder onBind( Intent intent ) {
        Log.i( "J","123"+Thread.currentThread() );

        return binder;
    }
  public   boolean onUnbind(Intent intent){
        Log.i( "J","123"+Thread.currentThread() );
        return false;

  }
  public void onDestroy(){
        Log.i( "J","123"+Thread.currentThread().getId() );
        super.onDestroy();
  }
  public int getRandomNumber(){
        return generator.nextInt();
  }


}

