package com.example.beargames;





import java.util.HashMap;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.RelativeLayout;



public class MainActivity extends Activity {

	
	private HardwareRenderer        _renderer = null; // < Instance of OpenGL ES
    // renderer
private GLSurfaceView           _surface  = null; // < Instance of OpenGL ES
    // surface view
    
    private Context activity = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity = MainActivity.this;
        
        //---> required for OPEN GL Hardware Rendering
        
        WindowManager winmgr = getWindowManager();
        Display display = winmgr.getDefaultDisplay(); 
        int width;
        int height;
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
        {
        	Point s = new Point();
        	display.getSize(s);
        	width = s.x;
        	height = s.y;
        }
        else
        {
        	DisplayMetrics d = new DisplayMetrics();
        	display.getMetrics(d);
        	width = d.widthPixels;
        	height = d.heightPixels;
        }
        _surface = new GLSurfaceView(this);
        _renderer = new HardwareRenderer(this, width, height);
        _surface.setRenderer(_renderer);
        
        
        
        
        setContentView(R.layout.startup);
        RelativeLayout surface_container = (RelativeLayout) findViewById(R.id.startup_layout_surface_container);
        
        surface_container.addView(_surface);
        
        //<--- required for OPEN GL Hardware Rendering
        
        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            	//finish();
                //Intent mi = new Intent(MainActivity.this, MenuActivity.class);
                //startActivity(mi);
                
                Intent si = new Intent(MainActivity.this, com.example.beargames.GameInit.class);
				startActivity(si);


            }
        }, 2000);
        */
        
        
        
        
    }
    public Context get_activity()
    {
    	return activity;
    }
    

    @Override
	public void onResume() 
    {
		super.onResume();
		_renderer.enableHardwareProfiling();
	}

	void continueLoad(final HashMap<String, String> mInfo){
	
		System.out.println("Done Hardware Rendering ... Continue with Game");
		
		this.runOnUiThread(new Runnable() {
			  public void run() {
				  
				  Handler handler = new Handler();
			        handler.postDelayed(new Runnable() {
			            public void run() {
			            	finish();
			                //Intent mi = new Intent(MainActivity.this, MenuActivity.class);
			                //startActivity(mi);
			                
			                Intent si = new Intent(MainActivity.this, com.example.beargames.GameInit.class);
							si.putExtra("maxtext", mInfo.get("gl_max_texture_size"));
			                startActivity(si);
			            }
			        }, 2000);
			    
			  }
			});
	
	
	}
	
}	


   

