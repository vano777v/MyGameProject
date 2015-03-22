package com.example.beargames;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;



public class MainActivity extends Activity {

    
    private Context activity = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = MainActivity.this;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                //Intent mi = new Intent(MainActivity.this, MenuActivity.class);
                //startActivity(mi);
                
                Intent si = new Intent(MainActivity.this, com.example.beargames.GameInit.class);
				startActivity(si);
               // Intent si = new Intent(MainActivity.this, com.bearsvsvampires.gameplay.test_vano.cocos_game.class);
			     //startActivity(si);
            }
        }, 2000);
        
        
        
    }
    public Context get_activity()
    {
    	return activity;
    }
    }


   

