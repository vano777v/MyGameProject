package com.example.beargames;

import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventListenerProxy;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.opengl.CCBitmapFontAtlas;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import org.cocos2d.utils.CCFormatter;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.gesture.GesturePoint;
import android.graphics.Color;
import android.hardware.Camera.Size;

import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.view.MotionEventCompat;

import android.view.MotionEvent;
import android.view.animation.BounceInterpolator;
import android.widget.ProgressBar;

public class GameLayer extends CCLayer
{
	private static CGSize screenSize; 
	private float generalscalefactor_h;
	private float generalscalefactor_w;
	private float poi=0;
	private static final int STATUS_LABEL_TAG = 20;
	private static final int TIMER_LABEL_TAG =20;
	private static final int MOVES_LABEL_TAG =20; 
    private static int[] buffer_team;
	private CCMenu gen = null;
	 static CCProgressTimer item1;
	private CCBitmapFontAtlas statusLabel;
	private int istouch =0;
	private  MenuLayer bar_menu, bnm;
	private CCSprite player;
	private static int thetime = 0 ;
	protected static Activity mMyApp;
	private  MenuLayer choose= null;
	private ArrayList<MenuLayer> menus_game= null;
    public GameLayer(Activity m)
    {
    	screenSize = CCDirector.sharedDirector().winSize();
        mMyApp = m; 
          
        
    	this.setIsTouchEnabled(true);
        menus_game = new ArrayList<MenuLayer>();
        buffer_team =  new int[4];
    	CGSize size_menus = CGSize.make(720, 100);
        CGSize scale_factors = CGSize.make(1, 1);
        this.main_menu_init(size_menus, scale_factors);
        size_menus.set(304, 480);
        this.ability_menu_init(size_menus, scale_factors);
        size_menus.set(350,490);
        this.bears_menu_init(size_menus, scale_factors);
        size_menus.set(300, 250);
        this.setting_menu_init(size_menus, scale_factors);
        size_menus.set(1024, 75);
        this.top_menu_init(size_menus, scale_factors);
        
       statusLabel = CCBitmapFontAtlas.bitmapFontAtlas (Float.toString(20), "bionic.fnt");
        //statusLabel.setScale( generalscalefactor_h); //scaled
        statusLabel.setAnchorPoint(CGPoint.ccp(0,1));
        statusLabel.setPosition( CGPoint.ccp( screenSize.getWidth()/2,screenSize.getHeight()/2));
        addChild(statusLabel);
    	
    
    }
    
    private void bear_team_init(Boolean blank_game)
    {
    	if(blank_game)
    	{
    		for(int i=0;i<buffer_team.length;i++){
    	      buffer_team[i]=i+1;
    	      button_item_bears_press(i+1);
    		}
    	
    	}
    	//else
    	//{
    		//request database users
    	//}
    }
     public boolean ccTouchesEnded(MotionEvent event) 
     {
    	
    	//;
    	 CGPoint location  = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
    	 int menu_tag = -1;
    	 for(int i=0; i<menus_game.size(); i++)
    	 {
    		if( menus_game.get(i).ccTouchesEnded(location));
    		{
    			
    	    	 menu_tag = menus_game.get(i).get_touch_menu_state();
    	    	 if(menu_tag!=-1) break;
    	    	
    		  }
    			
    	    } 
    		
    	   
    	 
         switch (menu_tag) 
         {
		   case 1: 
		   {   
		      
			  // System.out.println("Ibala"+menus_game.get(0).menuitems.size());
			   switch (menus_game.get(0).get_item_touch_tag()) {
		      	case 1: 
		      	{
		      		this.button_bears_choose();
		      		
		      		
		      		break;
		      	}
		      	case 2: 
		      	{
		      		menus_game.get(2).runAction(CCSequence.actions(CCMoveTo.action(1f, CGPoint.make(-350, 101)), CCCallFunc.action(this, "Menu_Move")));
		      		//menus_game.get(2).get_item(1).runAction(CCSequence.actions(CCMoveTo.action()), CCCallFunc.action(menus_game.get(2), "Menu_Move")));
		      		//menus_game.get(2).runAction(CCSequence.action;
		      		SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
		      		statusLabel.setString(Float.toString(12f));
		      		break;
		      	}
		      	
		      	case 3:
		      	{
		      		this.button_ability();
		      		
		      		break;
		      	}
		      	
		      	case 4:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[0]));
		      		break;
		      	}
		    	case 5:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[1]));
		      		break;
		      	}
		    	case 6:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[2]));
		      		break;
		      	}
		    	case 7:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[3]));
		      		break;
		      	}
		      	
			    default: statusLabel.setString(Float.toString(-1));
				  break;
			} 
			   
		
			 //menus_game.get(0).set_touch_menu_state(-1);
		     break;
		   }   
		   case 2: 
		   {
			 menus_game.get(1).get_item(1).setOpacity(60);
			 System.out.println("Ibala"+menus_game.get(1).get_item(1).getPosition());
			   
			   switch (menus_game.get(1).get_item_touch_tag()) {
			   		case 1: 
			   	   {
			   		   statusLabel.setString(Float.toString(21f));
			   		   break;
			   	   }
			   		case 2: 
			   		{
			   			statusLabel.setString(Float.toString(22f));
			   			break;
			   		}
	      	
			   		default:statusLabel.setString(Float.toString(-1));
			   			break;
			   	} 
			   
			   //statusLabel.setString(Float.toString(2f));
			   //menus_game.get(1).set_touch_menu_state(-1);
			   break;
		   } 
		   case 3: 
		   {
			   
			   //statusLabel.setString(Float.toString(2f));
			   
			   switch (menus_game.get(2).get_item_touch_tag()) {
		   		case 1: 
		   	   {
		   		   button_item_bears_press(1);
		   		   statusLabel.setString(Float.toString(31f));
		   		   break;
		   	   }
		   		case 2: 
		   		{
		   		 button_item_bears_press(2);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		
		   		case 3: 
		   		{
		   		 button_item_bears_press(3);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		
		   		case 4: 
		   		{
		   		 button_item_bears_press(4);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 5: 
		   		{
		   		 button_item_bears_press(5);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 6: 
		   		{
		   		 button_item_bears_press(6);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 7: 
		   		{
		   		 button_item_bears_press(7);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 8: 
		   		{
		   		 button_item_bears_press(8);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		
		   		default:statusLabel.setString(Float.toString(-1));
		   			break;
		   	} 
		   
		   //statusLabel.setString(Float.toString(2f));
		   menus_game.get(1).set_touch_menu_state(-1);
		   break;
		   }  
		   case 4: 
		   {
			   switch (menus_game.get(3).get_item_touch_tag()) {
		   		case 1: 
		   	   {  
		   		   
		   		   button_sound_press();
		   		   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
		   		   //statusLabel.setString(Float.toString(31f));
		   		   break;
		   	   }
		   		case 2: 
		   		{
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 3: 
		   		{
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 4: 
		   		{
		   			this.button_pause_press();
		   			
		   			menus_game.get(3).set_touch_menu_state(-1);
		   			break;
		   		}
		   		case 5: 
		   		{
		   			button_stop_press();
		   			SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
		   			statusLabel.setString(Float.toString(45f));
		   			break;
		   		}
    	
		   		default:statusLabel.setString(Float.toString(-1));
		   			break;
		   	} 
			   menus_game.get(3).set_touch_menu_state(-1);
			   break;
		   }  
		   case 5: 
		   {
			   //System.out.println("Aleoonnaa "+menus_game.get(4).get_item_touch_tag());
			   switch (menus_game.get(4).get_item_touch_tag()) 
			   {
		   		case 1: 
		   	   {
		   		   this.button_zoom_out();
		   		   menus_game.get(4).set_touch_menu_state(-1);
		   		   statusLabel.setString(Float.toString(51f));
		   		   break;
		   	   }
		   		case 2: 
		   		{
		   			this.button_zoom_in();
		   			statusLabel.setString(Float.toString(22f));
		   			menus_game.get(4).set_touch_menu_state(-1);
		   			break;
		   		}
			   
			   
			   }
			   break;
		   }  
		   

		default: statusLabel.setString(Float.toString(0));
			break;
		}
         return true;
    	
}
     
     public void Menu_Move ()
     {
    	//MenuLayer menu = (MenuLayer)sender;
    	 //this.removeChild(menu, true);
    	// menus_game.get(2).Destroy();
    	// SoundEngine.sharedEngine().playSound(mMyApp.getApplicationContext(), R.raw.button_press, false);
    	 statusLabel.setString("Reusit");
     }
     
     private void main_menu_init(CGSize size_menu, CGSize scale_factors)
     {
    	 
    	 MenuLayer main_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menu1.png",1, size_menu, scale_factors);
    	 main_menu.setPosition(0,0);
    	 main_menu.setIsTouchEnabled(true);
         main_menu.add_item("choose_unpress.png", 1, CGPoint.make(15, 15), CGSize.make(50, 50));
         main_menu.get_item(1).setisTouchEnabled(true);
         main_menu.add_item("strategy_unpress.png",2, CGPoint.make(108,13), CGSize.make(50, 50));
         main_menu.add_item("ability_unpress.png",3, CGPoint.make(652,13), CGSize.make(50, 50));
         main_menu.add_item("neutral/1n.png", 4, CGPoint.make(227, 8), CGSize.make(60, 60));
         main_menu.get_item(4).setisTouchEnabled(true);
         
         main_menu.add_item("neutral/2n.png", 5, CGPoint.make(327, 8), CGSize.make(60, 60));
         main_menu.add_item("neutral/3n.png", 6, CGPoint.make(427, 8), CGSize.make(60, 60));
         main_menu.add_item("neutral/4n.png", 7, CGPoint.make(527, 8), CGSize.make(60, 60));
         main_menu.get_item(3).setisTouchEnabled(true);
    	 addChild(main_menu);
    	 menus_game.add(0, main_menu);
    	 
     }
     private void ability_menu_init(CGSize size_menu, CGSize scale_factors)
     {
    	 MenuLayer ability_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menu2.png",2, size_menu, scale_factors);
    	 ability_menu.setPosition(720,-380);
    	 ability_menu.setIsTouchEnabled(true);
    	 ability_menu.add_item("beat.png", 1, CGPoint.make(27, 390), CGSize.make(60, 60));
    	 ability_menu.add_item("beat.png", 2, CGPoint.make(27, 292), CGSize.make(60, 60));
    	 ability_menu.add_item("mag.png", 3, CGPoint.make(27, 19), CGSize.make(60, 60));
    	 addChild(ability_menu);
    	 menus_game.add(1, ability_menu);
     }
     
     private void bears_menu_init(CGSize size_menu, CGSize scale_factors)
     {
    	 MenuLayer bears_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menu3.png",3, size_menu, scale_factors);
    	 bears_menu.setPosition(-350,101);
    	 bears_menu.setIsTouchEnabled(true);
    	 bears_menu.add_item("neutral/1n.png", 1, CGPoint.make(80, 392), CGSize.make(60, 60));
    	 bears_menu.get_item(1).setisTouchEnabled(true);
    	 bears_menu.add_item("neutral/2n.png", 2, CGPoint.make(235, 392), CGSize.make(60, 60));
    	 bears_menu.get_item(2).setisTouchEnabled(true);
    	 bears_menu.add_item("neutral/3n.png", 3, CGPoint.make(80, 272), CGSize.make(60, 60));
    	 bears_menu.get_item(3).setisTouchEnabled(true);
    	 bears_menu.add_item("neutral/4n.png", 4, CGPoint.make(235, 272), CGSize.make(60, 60));
    	 bears_menu.get_item(4).setisTouchEnabled(true);
    	 bears_menu.add_item("neutral/5n.png", 5, CGPoint.make(80, 150), CGSize.make(60, 60));
    	 bears_menu.get_item(5).setisTouchEnabled(true);
    	 bears_menu.add_item("block/6b.png", 6, CGPoint.make(235, 150), CGSize.make(60, 60));
    	 bears_menu.add_item("block/7b.png", 7, CGPoint.make(80, 30), CGSize.make(60, 60));
    	 bears_menu.add_item("block/8b.png", 8, CGPoint.make(235, 30), CGSize.make(60, 60));
    	 addChild(bears_menu);
    	 menus_game.add(2, bears_menu);
    	 bear_team_init(true);
    	 
     }
    
     private void top_menu_init (CGSize size_menu, CGSize scale_factors)
     {
    	 MenuLayer top_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menu5.png",5, size_menu, scale_factors);
    	 top_menu.setOpacity(0);
    	 top_menu.setPosition(0,645);
    	 top_menu.add_item("zoomout_unpress.png", 1, CGPoint.make(4,15 ), CGSize.make(55, 56));
    	 top_menu.get_item(1).setisTouchEnabled(true);
    	 top_menu.add_item("zoomin_unpress.png", 2, CGPoint.make(965,15 ), CGSize.make(55, 56));
    	 top_menu.get_item(2).setisTouchEnabled(true);
    	 top_menu.setIsTouchEnabled(true);
    	 //top_menu.setVertexZ(-1);
    	 addChild(top_menu);
    	 menus_game.add(4, top_menu);
     }
     
     private void setting_menu_init (CGSize size_menu, CGSize scale_factors)
     {
    	 MenuLayer setting_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menu4.png",4, size_menu, scale_factors);
    	 setting_menu.setOpacity(0);
    	 setting_menu.setPosition(655,645);
    	 setting_menu.add_item("sound_on.png", 1, CGPoint.make(13,102 ), CGSize.make(55, 57));
    	 setting_menu.get_item(1).setisTouchEnabled(true);
    	 setting_menu.add_item("restart.png", 2, CGPoint.make(122,102 ), CGSize.make(55, 57));
    	 setting_menu.get_item(2).setisTouchEnabled(true);
    	 setting_menu.add_item("sound_off.png", 3, CGPoint.make(232,102 ), CGSize.make(55, 57));
    	 setting_menu.get_item(3).setisTouchEnabled(true);
    	 setting_menu.add_item("pause.png", 4, CGPoint.make(232,15 ), CGSize.make(55, 57));
    	 setting_menu.get_item(4).setisTouchEnabled(true);
    	 setting_menu.add_item("stop.png", 5, CGPoint.make(232,188 ), CGSize.make(55, 57));
    	 setting_menu.get_item(5).setisTouchEnabled(true);
    	 
    	 setting_menu.setIsTouchEnabled(true);
    	 //setting_menu.setVertexZ(0);
    	 addChild(setting_menu);
    	 menus_game.add(3, setting_menu);
     }
     
     private void button_item_bears_press(int item_index)
     {
    	 int item_team=-1, place_free=-1;
    	 int delete_item=0;
    	 String path = new String();
    	 if(menus_game.get(2).get_item(item_index).get_touch_state())
    	 {
    		path = "neutral/"+item_index+"n.png";
    		 menus_game.get(2).get_item(item_index).set_touch_state(false);
    		 menus_game.get(2).change_Image_item(path, item_index);
    		 item_team=search_bear_team(item_index);
    		  delete_item=buffer_team[item_team]; 
    		 if(item_team!=-1)
    		 {
    			 
    			 set_place_to_free(item_team);
       
    		 }
    	
    	 }
    	 else 
    	 {
    		 path = "choosed/"+item_index+"a.png";
    		 menus_game.get(2).get_item(item_index).set_touch_state(true);
    		 menus_game.get(2).change_Image_item(path, item_index);
    		 item_team= search_bear_team(item_index);
    		 if(item_team!=-1)
    		 {
    			 buffer_team[item_team]=item_index;
    			 
    			
    		 }
    		 else
    		 {
    			 place_free = get_free_place_team();
    			 
    			 if(place_free!=-1)
    			 {
    				 buffer_team[place_free]=item_index;
    				
    			 }
    			 else
    			 {
    				
    				 int tag= buffer_team[buffer_team.length-1];
    				 buffer_team[buffer_team.length-1]=item_index;
    				 path = "neutral/"+tag+"n.png";
    				 menus_game.get(2).get_item(tag).set_touch_state(false);
    	    		 menus_game.get(2).change_Image_item(path, tag);
    				 //System.out.println("sUKA "+tag);
    				
    			 }
    		 }
    		
    	 }
    	update_control_team();
     }
     private void sort_bear_buffer(int[] bufer)
     {
    	 
    	 for(int i=0;i< bufer.length-1;i++ )
    		 if(bufer[i]==0)
    		 {
    		   bufer[i]=bufer[i+1];
    		   bufer[i+1]=0;
    		 } 
     }
     
      private void update_control_team()
     {
    	   String path=new String();
    	   sort_bear_buffer(buffer_team);
    	 for(int i=0;i<buffer_team.length;i++)
    	 {
    		 if(buffer_team[i]!=0)
    		 {
    			 //
    			 path="neutral/"+Integer.toString(buffer_team[i])+"n.png";
    			menus_game.get(0).change_Image_item(path, i+4);
    			menus_game.get(0).get_item(i+4).setOpacity(255);
    			menus_game.get(0).get_item(i+4).setisTouchEnabled(true);
    			
    			
    		 }
    		 else
    		 {
    			 menus_game.get(0).get_item(i+4).setOpacity(0);
    			 menus_game.get(0).get_item(i+4).setisTouchEnabled(false);
    			// menus_game.get(0).change_Image_item(path,4);
    			 // 
    			 //menus_game.get(0).change_Image_item("block/"+buffer_team[i]+"b.png", i+4);
    			
    		 }
    		
    	 }
     }
     private int search_bear_team(int index)
     {
    	 int item_tag = -1;
    	 for (int i=0; i<buffer_team.length; i++ )
    		 if(menus_game.get(2).get_item(index).getTag()== buffer_team[i])
    		 {
    			 item_tag = i;
    		 }
    	 	return item_tag;
     }
     
     private int get_free_place_team ()
     {
    	 int item_free = -1;
    	 for(int i=0;i<buffer_team.length; i++)
    	 {
    		 if(buffer_team[i]==0)
    		 {
    			 return i;
    		 }
    	 }
    	 return item_free;
     }
     
     private void set_place_to_free(int index)
     {
    	 buffer_team[index]=0;
     }
  
     private void button_bears_choose()
     {
    	 if(menus_game.get(0).get_item(1).get_touch_state()){ 
    		   menus_game.get(0).get_item(1).set_touch_state(false);
    		   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.large_heavy_castle_door_closing);
    		  menus_game.get(0).change_Image_item("choose_unpress.png", 1);
    		  
    		   menus_game.get(2).runAction(CCSequence.actions(CCMoveTo.action(1f, CGPoint.make(-350, 101)), CCCallFunc.action(this, "Menu_Move")));
    		   statusLabel.setString(Float.toString(11f));
              
    		}
    		else
    		{
    			   menus_game.get(0).get_item(1).set_touch_state(true);
    			   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.door_open);
    			   menus_game.get(0).change_Image_item("choose_press.png", 1);
	      		   menus_game.get(2).runAction(CCSequence.actions(CCMoveTo.action(1f, CGPoint.make(0, 101)), CCCallFunc.action(this, "Menu_Move")));
	      		   statusLabel.setString(Float.toString(11f));
	      		   
    		}
    	// for(int i=0;i<buffer_team.length;i++)
    	 //{
    		// System.out.println("GGGGG "+buffer_team[i]);
    	// }
    	 
     }
     private void button_zoom_out ()
     {
    	 menus_game.get(4).change_Image_item("zoomout_press.png", 1);
    	 Timer time =new Timer();
    	 
    	time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				menus_game.get(4).change_Image_item("zoomout_unpress.png", 1);
			}
		}, 300);
    	 //

		
     }
     private void button_zoom_in ()
     {
    	 menus_game.get(4).change_Image_item("zoomin_press.png", 2);
    	 Timer time =new Timer();
    	 
    	 time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				menus_game.get(4).change_Image_item("zoomin_unpress.png", 2);
			}
		}, 300);
    	 //

		
     }
      private void button_ability()
     {
    	
    	 if(menus_game.get(0).get_item(3).get_touch_state()){ 
  		   menus_game.get(0).get_item(3).set_touch_state(false); 
  		   menus_game.get(0).change_Image_item("ability_unpress.png", 3);
  		   SoundEngine.sharedEngine().playEffect(mMyApp, R.raw.wooden_door_being_closed); 
  		   menus_game.get(1).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(720,-380)), CCCallFunc.action(this, "Menu_Move")));
  		   statusLabel.setString(Float.toString(31f));
  		}
  		else
  		{
  			
  			   menus_game.get(0).get_item(3).set_touch_state(true);
  			   menus_game.get(0).change_Image_item("ability_press.png", 3);
  			   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wooden_door_open); 
	      		   menus_game.get(1).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(720, 0)), CCCallFunc.action(this, "Menu_Move")));
	      		   statusLabel.setString(Float.toString(31f));
	      		 
  		}
   }
     
      private void button_stop_press()
      {
    	  menus_game.get(3).change_Image_item("stop_press.png", 5);
     	 Timer time =new Timer();
     	 
     	 time.schedule(new TimerTask() {
 			
 			@Override
 			public void run() {
 				
 				for(int i=menus_game.size()-1;i>=0;i--){
 					menus_game.get(i).Destroy();
 					menus_game.remove(i);
 					
 				}
 				//Context obj = beargames.getContext();
 			
 				mMyApp.finish();
 			}
 		}, 300);
      }
      
      private void button_sound_press ()
      {
    	  if(menus_game.get(3).get_item(1).get_touch_state()){ 
   		   
     		 menus_game.get(3).get_item(1).set_touch_state(false);
     		 menus_game.get(3).change_Image_item("sound_on.png", 1);
     		   statusLabel.setString(Float.toString(41f));
     		  
              
     		  
     		}
     		else
     		{
     			menus_game.get(3).get_item(1).set_touch_state(true);
       		    menus_game.get(3).change_Image_item("off.png", 1);
       		    statusLabel.setString(Float.toString(41f));
   	      		 
     		}
    	  
      }
     private void button_pause_press() 
     {
    	 if(menus_game.get(3).get_item(4).get_touch_state()){ 
    		   
    		 menus_game.get(3).get_item(4).set_touch_state(false);
    		 menus_game.get(3).change_Image_item("pause.png", 4);
    		 SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wood_internal_door_handle_movement_version_2);
    		menus_game.get(3).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(655,645)), CCCallFunc.action(this, "Menu_Move")));
    		   statusLabel.setString(Float.toString(41f));
    		  
             
    		  
    		}
    		else
    		{
    			menus_game.get(3).get_item(4).set_touch_state(true);
      		  menus_game.get(3).change_Image_item("play.png", 4);
      		SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wooden_door_being_closed);
      		   menus_game.get(3).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(655,470)), CCCallFunc.action(this, "Menu_Move")));
      		   statusLabel.setString(Float.toString(41f));
  	      		 
    		}
     }
    public static CCScene scene()
    {
    	CCScene scene = CCScene.node();
    	GameLayer layer = new GameLayer(mMyApp);
    	scene.addChild(layer);
    	return scene;
    	
    }
    
    public void updateTimeLabel(float dt) {
 
    	thetime += 1;
    	String string = CCFormatter.format("%02d:%02d", (int)(thetime /60) , (int)thetime % 60 );
    	CCBitmapFontAtlas timerLabel = (CCBitmapFontAtlas) getChildByTag(TIMER_LABEL_TAG) ;
    	timerLabel.setString(string);
    	}
   
      
}
