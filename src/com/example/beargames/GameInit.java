package com.example.beargames;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CGSize;

import com.example.beargames.Campaign_1.Level_1_1;
import com.example.beargames.Campaign_1.Level_1_2;
import com.example.beargames.Campaign_1.Level_1_3;
import com.example.beargames.Campaign_1.Level_1_4;
import com.example.beragames.loadscreans.Main_Load_Screen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameInit extends Activity
{
	
	protected CCGLSurfaceView _glSurfaceView;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		_glSurfaceView = new CCGLSurfaceView(this);
		setContentView(_glSurfaceView);
		
		CCDirector director = CCDirector.sharedDirector();
		 director.attachInView(_glSurfaceView);

		 director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft); // set orientation
		 CCDirector.sharedDirector().setDisplayFPS(true);  //display fps
	     Activity s= GameInit.this;
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60.0f);  //set frame rate
	   
		//---> set texture max to CCDirector for later use
		/*
				String maxTexture = s.getIntent().getStringExtra("maxtext");
				System.out.println("maxTexture="+maxTexture);
				CCDirector.setMaxTexture(Integer.valueOf(maxTexture));
		*/
		SharedPreferences settings = getSharedPreferences("APP_PREF", 0);
		String maxTexture = settings.getString("gl_max_texture_size", "1024");
		System.out.println("maxTexture="+maxTexture);
		CCDirector.setMaxTexture(Integer.valueOf(maxTexture));
		CGSize screen_size = CCDirector.sharedDirector().displaySize();
		float general_scale_factor = screen_size.height/1600f;
		//<--- set texture max to CCDirector for later use
		Main_Load_Screen load_screen = new Main_Load_Screen("campaign_1/Load_Screens/", general_scale_factor);
		CCScene scene = load_screen.scene();
		CCDirector.sharedDirector().runWithScene(scene);
		
		//Level_1_2 level_1 = new Level_1_2("campaign_1/","level_2", general_scale_factor,screen_size);
		//CCScene scene = level_1.get_Level().scene();
		//Level_1_3 level_3 = new Level_1_3("campaign_1/","level_3", general_scale_factor,screen_size);
		//CCScene scene = level_3.get_Level().scene();
		//Level_1_4 level_4 = new Level_1_4("campaign_1/","level_4", general_scale_factor,screen_size);
		//CCScene scene = level_4.get_Level().scene();
		
		
		

	}

	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		CCDirector.sharedDirector().pause();		
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		CCDirector.sharedDirector().resume();
	}
	
	@Override
	public void onStop()
	{
		super.onStop();		
		CCDirector.sharedDirector().end();
	}
	
}
