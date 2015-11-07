package com.example.beragames.loadscreans;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import android.view.MotionEvent;

import com.example.beargames.MenuLayer;
import com.example.beargames.Campaign_1.Level_1_1;
import com.example.game.arena.elements.Personage_Element;
import com.example.game.arena.elements.Progress_Bar_element;

public class Main_Load_Screen extends CCLayer
{
 
  private CCProgressTimer progress_bar =null;
  private MenuLayer button=null;
  private float general_scale_factor=0;
  private String path=null;
  private CGSize screen=null;
  public Main_Load_Screen(String image_path,float _general_scale_factor)
  {
	  general_scale_factor =_general_scale_factor;
	  path= image_path;
	  this.setPosition(0,0);
	  this.setAnchorPoint(0,0);
	  CGSize display_size= CCDirector.sharedDirector().displaySize();
	  this.setContentSize(display_size);
	  CCSprite back=bacground_init(image_path, display_size);
	  CCSprite front=front_init(image_path, display_size);
	  CCSprite bar= bar_init(image_path, display_size, front.getScaleX(), front.getPosition().x);
	  progress_bar_init(image_path, bar);
	  button_init(image_path, front.getScaleY(), bar);
	  this.addChild(back);
	  this.addChild(front);
	  this.addChild(bar);
	  this.addChild(button);
	  this.setIsTouchEnabled(true);
	  screen = display_size;
	  this.schedule("start_load");
	  //this.addChild( bacground_init(image_path));
  }
  private CCSprite bacground_init(String image_path, CGSize display_size )
  {
	 
	  CCSprite background_image = CCSprite.sprite(image_path+"level_1/back.png");
	  background_image.setAnchorPoint(0,0);
	  background_image.setPosition(0, 0);
	  float scale_x=display_size.width/background_image.getContentSize().width;
	  float scale_y=display_size.height/background_image.getContentSize().height;
	  background_image.setScaleX(scale_x);
	  background_image.setScaleY(scale_y);
	  background_image.setContentSize(display_size);
	  
	 
	  return background_image;
  }
  private CCSprite front_init(String image_path, CGSize display_size )
  {
	 
	  CCSprite background_image = CCSprite.sprite(image_path+"level_1/front.png");
	  background_image.setAnchorPoint(0,0);
	 
	  float scale_y=display_size.height/background_image.getContentSize().height;
	  background_image.setScaleX(scale_y);
	  background_image.setScaleY(scale_y);
	  background_image.setContentSize(background_image.getContentSize().width*scale_y,background_image.getContentSize().height*scale_y);
	  background_image.setPosition((display_size.width-background_image.getContentSize().width)/2, 0);
	 
	  return background_image;
  }
  private void button_init(String path, float scale_factor, CCSprite bar)
  {
	  
	  button = new MenuLayer(ccColor4B.ccc4(255,255, 255,255),path,path+"bar/button_n.png",1, CGSize.make(82, 82), scale_factor);
	  float poz= bar.getPosition().x+bar.getContentSize().width-55f*scale_factor-button.getContentSize().width;
	  button.setPosition(poz, 33*scale_factor);
	  button.setAnchorPoint(0, 0);
	  button.setIsTouchEnabled(true);
	  button.setOpacity(0);
	  button.add_item(path+"bar/button_a.png", 1, CGPoint.make(0, 0), CGSize.make(82, 82));
	  button.get_item(1).setisTouchEnabled(false);
	  button.get_item(1).setVisible(false);
	  
  }
  public void setPercentageBarLife(float real_value)
  {
	   float value = real_value;
	   System.out.println("BARA "+value);
	   progress_bar.setPercentage(value);
  }
  public float getPercentageBarLife()
  {
	  return progress_bar.getPercentage();
  }
 public MenuLayer get_Menu()
 {
	 return button;
 }
  
  private void progress_bar_init(String path, CCSprite main)
  {
	     progress_bar = CCProgressTimer.progress(path+"bar/bar_full.png");
		 progress_bar.setAnchorPoint(CGPoint.make(0, 0));
		 progress_bar.setPosition(0, 0);
		 progress_bar.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);
		 progress_bar.setPercentage(50f);
		 main.addChild(progress_bar);
		 
  }
  
  private CCSprite bar_init(String path, CGSize display_size, float scale_x, float poz_x)
  {
	  CCSprite background_image = CCSprite.sprite(path+"bar/bar_emty.png");
	  background_image.setAnchorPoint(0,0);
	  background_image.setPosition(poz_x, 0);
	  background_image.setScaleX(scale_x);
	  background_image.setScaleY(scale_x);
	  background_image.setContentSize(background_image.getContentSize().width*scale_x, background_image.getContentSize().height*scale_x);
	  
	 
	  return background_image;
  }
  public boolean ccTouchesEnded(MotionEvent event) 
  {
	  CGPoint location  = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
	  
	  if(button.ccTouchesEnded(location))
	  {
		  
		if(button.get_item(1).get_isTouchEnabel())
		{
		  button.get_item(1).button_press(path+"bar/button_a.png",path+"bar/button_p.png" , 0.2f);
		  CCDirector.sharedDirector().replaceScene(scene2);
		}
	  }
	  
	  return true;
  }
  CCScene scene2 =null;
  public void start_load(float dt)
  {
	    this.unschedule("start_load");
	    Level_1_1 level_1 = new Level_1_1(1,1, general_scale_factor,screen, this);
		this.setPercentageBarLife(100f);
		this.get_Menu().get_item(1).setVisible(true);
		this.get_Menu().get_item(1).setisTouchEnabled(true);
		 scene2 = level_1.get_logic_game().scene();
		
  }
  public  CCScene scene()
  {
  	CCScene scene = CCScene.node();
  	scene.addChild(this);
  	
  	return scene;
  	
  }
}
