package com.example.beargames;

import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventListenerProxy;
import java.util.List;
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

import com.example.beargames.R.raw;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.gesture.GesturePoint;
import android.graphics.Color;
import android.hardware.Camera.Size;

import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.Settings.System;
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
    private static int[] buffer_ability_items={0,0,0};
    private static int[] buffer_ability_team = {4,6,10,16};
	private CCMenu gen = null;
	 static CCProgressTimer item1;
	private CCBitmapFontAtlas statusLabel;
	private int istouch =0;
	private  int  last_touch_ability=0;
	private  MenuLayer bar_menu, bnm;
	private CCSprite player;
	private ArrayList<Integer>  bear_init= null;
	private static int thetime = 0 ;
	private static int team_selected=0;
	protected static Activity mMyApp;
	private  MenuLayer choose= null;
	private ArrayList<MenuLayer> menus_game= null;
    public GameLayer(Activity m)
    {
    	screenSize = CCDirector.sharedDirector().winSize();
        mMyApp = m; 
          java.lang.System.out.println("Antonio"+screenSize);
        bear_init= new ArrayList<Integer>();
    	this.setIsTouchEnabled(true);
        menus_game = new ArrayList<MenuLayer>();
        buffer_team =  new int[4];
    	CGSize percents = CGSize.make(800/2560f, 1082/1600f);
        CGSize scale_factors = CGSize.make(1, 1);
        
        //size_menus.set(304, 486);
        this.bears_menu_init(screenSize, percents);
        this.main_menu_init( 0.16f);
        this.ability_menu_init();
        //size_menus.set(800,1082);
       
        //size_menus.set(300, 250);
        //this.setting_menu_init(size_menus, scale_factors);
        //size_menus.set(1024, 75);
        this.top_menu_init();
        //this.ablity_items_init();
        for(int i=1;i<=5; i++)
        	bear_init.add(i*2);
       statusLabel = CCBitmapFontAtlas.bitmapFontAtlas (Float.toString(20), "bionic.fnt");
        //statusLabel.setScale( generalscalefactor_h); //scaled
        statusLabel.setAnchorPoint(CGPoint.ccp(0,1));
        statusLabel.setPosition( CGPoint.ccp( screenSize.getWidth()/2,screenSize.getHeight()/2));
        addChild(statusLabel);
    	
    
    }
    
    
    private void bear_team_init(Boolean blank_game, int menu_index)
    {
    	if(blank_game)
    	{
    		
    		
    		for(int i=0;i<buffer_team.length;i++){
    	      buffer_team[i]=(i+1)*2;
    	      //java.lang.System.out.println("Aleoonaaa "+buffer_team[i]);
    	     button_item_bears_press(buffer_team[i],0,1);
    	     
    		}
    	    
    		String path= null;
    		int i=7;
    		while(i<=8)
    		{
    			
    			path = "menus/block/"+(i)+"b.png";
    			menus_game.get(menu_index).change_Image_item(path, i*2);
    			menus_game.get(menu_index).get_item(i*2).setisTouchEnabled(false);
    			menus_game.get(menu_index).get_item(i*2).set_touch_state(true);
    			i++;
    		}
    	 last_touch_ability = 5;	
    	}
    	else
    	{
    		
    		
    	}
    	//update_control_team();
    }
     public boolean ccTouchesEnded(MotionEvent event) 
     {
    	
    	//;
    	 CGPoint location  = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
    	 int menu_tag = -1;
    	java.lang.System.out.println("AIIII loc "+location);
    	 java.lang.System.out.println("AIIII "+menus_game.get(0).scaled_size_width+" "+menus_game.get(0).scaled_size_height);
    	 
    	 for(int i=0; i<menus_game.size(); i++)
    	 {
  
    		if( menus_game.get(i).ccTouchesEnded(location));
    		{
    		   java.lang.System.out.println("Lungu "+menu_tag);	
    	    	 menu_tag = menus_game.get(i).get_touch_menu_state();
    	    	 if(menu_tag!=-1) break;
    	    	
    		  }
    			
    	    } 
    		
    	   
    	 
         switch (menu_tag) 
         {
		   case 2: 
		   {   
		      
			   //System.out.println("Ibala"+menus_game.get(menu_tag-1).menuitems.size());
			   switch (menus_game.get(menu_tag-1).get_item_touch_tag()) {
		      	case 4: 
		      	{
		      		this.button_bears_choose(menu_tag-1,0,4);
		      		
		      		
		      		break;
		      	}
		      	case 20: 
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
		      	
		      	case 6:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[0]));
		      		 button_bear_team_item_select(menu_tag-1,0,6, buffer_team[0]/2, 1);
		      		break;
		      	}
		    	case 8:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[1]));
		      		button_bear_team_item_select(menu_tag-1,0,8, buffer_team[1]/2, 2);
		      		break;
		      	}
		    	case 10:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[2]));
		      		button_bear_team_item_select(menu_tag-1,0,10, buffer_team[2]/2, 3);
		      		break;
		      	}
		    	case 12:
		      	{
		      		
		      		statusLabel.setString(Integer.toString(buffer_team[3]));
		      		button_bear_team_item_select(menu_tag-1,0,12, buffer_team[3]/2, 6);
		      		break;
		      	}
		      	
			    default: statusLabel.setString(Float.toString(-1));
				  break;
			} 
			   
		
			 //menus_game.get(0).set_touch_menu_state(-1);
		     break;
		   }   
		   case 3: 
		   {
			 
			   
			   switch (menus_game.get(menu_tag-1).get_item_touch_tag())
			   {
			   case 2:
		      	{

		   		statusLabel.setString(Float.toString(buffer_ability_items[1]));
		      		break;
		      	}
			   case 3:
		      	{
		      		statusLabel.setString(Float.toString(buffer_ability_items[2]));
		      		break;
		      	}
			   case 5:
		      	{
		      		button_ability_item_press(menus_game.get(menu_tag-1).get_item_touch_tag(),menu_tag-1);
		      		
		      		break;
		      	}
			   case 9:
		      	{
		      		button_ability_item_press(9,2);
		      		
		      		break;
		      	}
			   case 13:
		      	{
		      		button_ability_item_press(13,2);
		      		
		      		break;
		      	}
			   case 17:
		      	{
		      		button_ability_item_press(17,2);
		      		
		      		break;
		      	}
			   
			   		default:statusLabel.setString(Float.toString(-1));
			   			break;
			   	} 
			   
			   menus_game.get(1).set_touch_menu_state(-1);
			   break;
		   } 
		   case 1: 
		   {
			   
			   //statusLabel.setString(Float.toString(2f));
			   int item_tag = menus_game.get(menu_tag-1).get_item_touch_tag();
			   switch (item_tag ) {
		   		case 2: 
		   	   {
		   		statusLabel.setString(Float.toString(31f));
		   		  button_item_bears_press(item_tag, menu_tag-1,1);
		   		   
		   		   break;
		   	   }
		   		case 4: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		
		   		case 6: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(23f));
		   			break;
		   		}
		   		
		   		case 8: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(24f));
		   			break;
		   		}
		   		case 10: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(25f));
		   			break;
		   		}
		   		case 12: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(26f));
		   			break;
		   		}
		   		case 14: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(27f));
		   			break;
		   		}
		   		case 16: 
		   		{
		   		 button_item_bears_press(item_tag, menu_tag-1,1);
		   			statusLabel.setString(Float.toString(28f));
		   			break;
		   		}
		   		
		   		default:statusLabel.setString(Float.toString(-1));
		   			break;
		   	} 
		   
		   //statusLabel.setString(Float.toString(2f));
		   menus_game.get(menu_tag-1).set_touch_menu_state(-1);
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

    private void  button_bear_team_item_select(int menu_index, int bear_menu_index ,int index, int item_tag, float time)
     {
    	 if(menus_game.get(menu_index).get_item(index).get_touch_state())
    	 {
    		 java.lang.System.out.println("Trala lala la");
    	 }
    	 else
    	 {
    		 menus_game.get(menu_index).change_Image_item("menus/block/"+item_tag+"b.png", index);
    		 menus_game.get(menu_index).get_item(index).setisTouchEnabled(false);
    		 menus_game.get(menu_index).get_item(index).set_touch_state(true);
    		 menus_game.get(menu_index).get_item(index).time_progress_bar_init("menus/choosed/"+item_tag+"a.png");
    		 menus_game.get(menu_index).get_item(index).set_path_progress_bar("menus/neutral/"+item_tag+"n.png");
    		 menus_game.get(menu_index).get_item(index).time_progress(time,menus_game.get(bear_menu_index),item_tag, team_selected, buffer_team);
    		 menus_game.get(bear_menu_index).get_item(item_tag*2).setisTouchEnabled(false);
    		 menus_game.get(bear_menu_index).change_Image_item("menus/press/"+item_tag+"p.png", item_tag*2);
    		 team_selected++;
    		
    		 java.lang.System.out.println("kkkk "+team_selected+" "+ menus_game.get(menu_index).get_item(index).team_count);
    		 if((team_selected-menus_game.get(menu_index).get_item(index).team_count)==4||(team_selected-menus_game.get(menu_index).get_item(index).team_count)==0)
    		 {
    			 for(int i=2;i<18;i+=2)
    			 {
    			   if(menus_game.get(bear_menu_index).get_item(i).get_isTouchEnabel()&& !menus_game.get(bear_menu_index).get_item(i).get_touch_state()){	 
    				  menus_game.get(bear_menu_index).change_Image_item("menus/neutral_press/"+(i/2)+"np.png", i);
    				 // menus_game.get(bear_menu_index).get_item(i).set_touch_state(true);
    				  menus_game.get(bear_menu_index).get_item(i).setisTouchEnabled(false);
    			   }
    			 }
    		 }
    		 if(team_selected==4) team_selected=0;
    	 }
     }
     private void button_ability_item_press(int index, int menu_index)
     {
    	 String path="menus/ability_icons/"+last_touch_ability+"ab_i/1a.png";
	     menus_game.get(menu_index).change_Image_item(path, last_touch_ability);
	     menus_game.get(menu_index).get_item(last_touch_ability).setisTouchEnabled(true);
    	 path="menus/ability_icons/"+index+"ab_i/1p.png";
    	 menus_game.get(menu_index).change_Image_item(path, index);
    	 for(int i=0;i<3;i++)
    	 {
    	 	buffer_ability_items[i]=index+i;
    	 	
    	 }
    	 menus_game.get(menu_index).change_Image_item("menus/ability_icons/main_button"+index+"a.png", 1);
    	 menus_game.get(menu_index).get_item(index).setisTouchEnabled(false);
    		 
    		 last_touch_ability=index;
    		 //System.out.println();
    	 
     } 
     private void main_menu_init(float percents)
     {
    	 float perc = screenSize.height/1600;
    	 MenuLayer main_menu =   new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menus/menu2.png",2, CGSize.make(3048f, 250f), perc);
    	 main_menu.setPosition(0,0);
    	 main_menu.setIsTouchEnabled(true);
    	 main_menu.add_item("menus/rim_stone.png", 1, CGPoint.make(348, 10), CGSize.make(200, 200));
         main_menu.add_item("menus/strategy_unpress.png",2, CGPoint.make(358, 20), CGSize.make(180, 180));
         main_menu.add_item("menus/rim_stone.png",3, CGPoint.make(69, 10), CGSize.make(200, 200));
         main_menu.add_item("menus/main_unpress.png",4, CGPoint.make(79, 20), CGSize.make(180, 180));
         float dist = 678;
         int count=1;
         for(int i=5;i<=12;i++)
         {
        	 main_menu.add_item("menus/rim.png", i, CGPoint.make(dist, 10), CGSize.make(200, 200));
        	 i++;
        	 count=i/2-2;
             main_menu.add_item("menus/neutral/"+count+"n.png", i, CGPoint.make(dist+10, 20), CGSize.make(180, 180));
             dist+=300;
         } 
         
         
         main_menu.get_item(4).setisTouchEnabled(true);
         main_menu.get_item(6).setisTouchEnabled(true);
         main_menu.get_item(8).setisTouchEnabled(true);
         main_menu.get_item(10).setisTouchEnabled(true);
         main_menu.get_item(12).setisTouchEnabled(true);
         /*main_menu.add_item("strategy_unpress.png",2, CGPoint.make(108,13), CGSize.make(50, 50));
         main_menu.add_item("ability_unpress.png",3, CGPoint.make(652,13), CGSize.make(50, 50));
         main_menu.add_item("neutral/1n.png", 4, CGPoint.make(227, 8), CGSize.make(60, 60));
         main_menu.get_item(4).setisTouchEnabled(true);
         main_menu.add_item("neutral/2n.png", 5, CGPoint.make(327, 8), CGSize.make(60, 60));
         main_menu.add_item("neutral/3n.png", 6, CGPoint.make(427, 8), CGSize.make(60, 60));
         main_menu.add_item("neutral/4n.png", 7, CGPoint.make(527, 8), CGSize.make(60, 60));
         main_menu.get_item(3).setisTouchEnabled(true); */
    	 addChild(main_menu);
    	 menus_game.add(1, main_menu);
    	 update_control_team(1);
    	 
     }
     private void ability_menu_init()
     {
    	 float perc = screenSize.height/1600f;
    	 MenuLayer ability_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menus/menu3.png",3, CGSize.make(762f, 1180f), perc);
    	
    	
    	 
    	
    	 ability_menu.setPosition(screenSize.width-ability_menu.scaled_size_width,0f);//-(ability_menu.scaled_size_height-menus_game.get(1).scaled_size_height));
    	
    	 ability_menu.setIsTouchEnabled(true);
    	/* ability_menu.add_item("ability_icons/1b.png", 1, CGPoint.make(20, 930), CGSize.make(225, 225));
    	
    	 ability_menu.add_item("ability_icons/1n.png", 3, CGPoint.make(90, 715), CGSize.make(140, 140));
    	 ability_menu.add_item("ability_icons/2n.png", 4, CGPoint.make(320, 715), CGSize.make(140, 140));
    	 ability_menu.add_item("ability_icons/3n.png", 5, CGPoint.make(570, 715), CGSize.make(140, 140));
    	 
    	 ability_menu.add_item("ability_icons/4n.png", 6, CGPoint.make(285, 705), CGSize.make(521, 160));
    	 ability_menu.add_item("menus/rim.png", 7, CGPoint.make(80,465), CGSize.make(160, 160));
    	 ability_menu.add_item("ability_icons/1n.png", 8, CGPoint.make(90, 475), CGSize.make(140, 140));
    	 ability_menu.add_item("ability_icons/2n.png", 9, CGPoint.make(320, 475), CGSize.make(140, 140));
    	 ability_menu.add_item("ability_icons/3n.png", 10, CGPoint.make(570, 475), CGSize.make(140, 140));
    	 
    	 ability_menu.add_item("ability_icons/4n.png", 11, CGPoint.make(285, 465), CGSize.make(521, 160));
    	 //ability_menu.add_item("ability_icons/2n.png", 7, CGPoint.make(30, 950), CGSize.make(200, 200));*/
    	 addChild(ability_menu);
    	 menus_game.add(2, ability_menu);
    	 ablity_items_init(ability_menu,0);
    	
     }
     
     private void ablity_items_init(MenuLayer ability_menu , int bear_menu_index)
     {
    	float coord_x=80, coord_y=705;
    	int j=0, count=1, count_bear_main=0;
    	String path=null;
    	ability_menu.add_item("menus/ability_icons/main_button/1a.png", 1, CGPoint.make(20, 930), CGSize.make(225, 225));
    	ability_menu.add_item("menus/ability_icons/5ab_i/2a.png", 2, CGPoint.make(320, 970), CGSize.make(150, 150));
    	ability_menu.add_item("menus/ability_icons/5ab_i/3a.png", 3, CGPoint.make(570, 970), CGSize.make(150, 150));
    	 for(int i=4;i<20;i+=4)
    	{
    		 
    		 j=i;
    	 if(menus_game.get(bear_menu_index).get_item(buffer_ability_team[count_bear_main]).get_isTouchEnabel()){	 
    		 ability_menu.add_item("menus/rim.png", i, CGPoint.make(coord_x,coord_y), CGSize.make(160, 160));
    		 j++;
    		 coord_x+=10;
    		 coord_y+=10;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", j, CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		 ability_menu.get_item(j).setisTouchEnabled(true);
    		 count++;
    		 coord_x+=230;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", (j+1), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count++;
    		 coord_x+=250;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", (j+2), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count=1;
    		 coord_x=80;
    		 coord_y-=250;
    	 }
    	 else
    	 {
    		 ability_menu.add_item("menus/rim.png", i, CGPoint.make(coord_x,coord_y), CGSize.make(160, 160));
    		 j++;
    		 coord_x+=10;
    		 coord_y+=10;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", j, CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		 ability_menu.get_item(j).setisTouchEnabled(false);
    		 count++;
    		 coord_x+=230;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", (j+1), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count++;
    		 coord_x+=250;
    		 path="menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", (j+2), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count=1;
    		 coord_x=80;
    		 coord_y-=250;
    	 }
    		 count_bear_main++;
    	}
    	 coord_y=705;
    	 for(int i=20;i<25;i++)
    	 {
    		 ability_menu.add_item("menus/ability_icons/glass.png", i, CGPoint.make(285, coord_y), CGSize.make(521, 160));
    		 coord_y-=240;
    	 }
    	 
    	 button_ability_item_press(last_touch_ability, 2);
     }
     private void bears_menu_init( CGSize screen_size, CGSize percents)
     {
    	
    	// CGSize size_menu=CGSize.make( screen_size.width*percents.width, screen_size.height*percents.height);
    	// CGSize scale_factors = CGSize.make(1, 1); 
    	 float s = screen_size.height/1600f;
    	 MenuLayer bears_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menus/menu1.png",1, CGSize.make(800f, 1082f), s);    	
    	 bears_menu.setPosition(-bears_menu.scaled_size_width,250f*s );                                                                                                                                                                                                 
    	 bears_menu.setIsTouchEnabled(true);
    	float sizex = bears_menu.get_size().width/800f;
    	 float sizey = bears_menu.get_size().height/1082f;
    	 java.lang.System.out.println("Olealea"+sizex+" "+sizey);
    	 int count=1;
    	 float coordx=165f;
    	 float coordy = 815f;
    	 for(int i=1;i<17;i++)
    	 {
    		 
    		 bears_menu.add_item("menus/rim.png", i, CGPoint.make(coordx, coordy), CGSize.make(160f, 160f));
    		 i++;
    		 bears_menu.add_item("menus/neutral/"+(i/2)+"n.png", i, CGPoint.make(coordx+10, coordy+10), CGSize.make(140f, 140f));
    		 bears_menu.get_item(i).setisTouchEnabled(true);
    		 java.lang.System.out.println("menus/neutral/"+i+"n.png");
    		 count++; 
    		 coordx+=370;
    		 if(count==3)
    		 {
    			 count=1;
    			 coordx-=370*2;
    			 coordy-=250;
    		 }
    		 
    	 }
    	
    			 
    	 
    	
    	 addChild(bears_menu);
    	 menus_game.add(0, bears_menu);
    	 bear_team_init(true,0);
    	 
    	 
     }
    
     private void top_menu_init ()
     {
    	 float perc = screenSize.height/1600f;
    	 MenuLayer top_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),"menus/menu4.png",3, CGSize.make(3840f, 188f), perc);
    	 top_menu.setOpacity(0);
    	 top_menu.setPosition(screenSize.width-top_menu.scaled_size_width,screenSize.height-top_menu.scaled_size_height);
    	 /*top_menu.add_item("zoomout_unpress.png", 1, CGPoint.make(4,15 ), CGSize.make(55, 56));
    	 top_menu.get_item(1).setisTouchEnabled(true);
    	 top_menu.add_item("zoomin_unpress.png", 2, CGPoint.make(965,15 ), CGSize.make(55, 56));
    	 top_menu.get_item(2).setisTouchEnabled(true);*/
    	 top_menu.setIsTouchEnabled(true);
    	 //top_menu.setVertexZ(-1);
    	 addChild(top_menu);
    	// menus_game.add(4, top_menu);
     }
     
    /* private void setting_menu_init (CGSize size_menu, CGSize scale_factors)
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
     }*/
     
     private void button_item_bears_press(int item_index, int menu_tag, int menu_main_index)
     {
    	 int item_team=-1, place_free=-1;
    	 int delete_item=0;
    	 String path = new String();
    	 //java.lang.System.out.println("Zaibali "+menu_tag+" "+item_index);
    	 if(menus_game.get(menu_tag).get_item(item_index).get_touch_state())
    	 {
    		path = "menus/neutral/"+(item_index/2)+"n.png";
    		 menus_game.get(menu_tag).get_item(item_index).set_touch_state(false);
    		 menus_game.get(menu_tag).change_Image_item(path, item_index);
    		  item_team=search_bear_team(menu_tag, item_index);
    		  delete_item=buffer_team[item_team];
    		 if(item_team!=-1)
    		 {
    			   
    			 set_place_to_free(item_team);
       
    		 }
    	
    	 }
    	 else 
    	 {
    		 path = "menus/choosed/"+(item_index/2)+"a.png";
    		 menus_game.get(menu_tag).get_item(item_index).set_touch_state(true);
    		 menus_game.get(menu_tag).change_Image_item(path, item_index);
    		 item_team= search_bear_team(menu_tag, item_index);
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
    				
    			  if(search_not_progress_place(menu_main_index)!=-1){	 
    				int tag= buffer_team[(search_not_progress_place(menu_main_index)-6)/2];
    				 buffer_team[(search_not_progress_place(menu_main_index)-6)/2]=item_index;
    				 path = "menus/neutral/"+(tag/2)+"n.png";
    				 menus_game.get(menu_tag).get_item(tag).set_touch_state(false);
    	    		 menus_game.get(menu_tag).change_Image_item(path, tag);
    				 //System.out.println("sUKA "+tag);
    			  }
    			 }
    		 }
    		
    	 }
    	 
       if(menus_game.size()>=2)	 
    	update_control_team(1);
     }
     private int  search_not_progress_place(int menu_index)
     {
    	 int item_index=-1;
    	 for(int i=6;i<13; i+=2)
            if(!menus_game.get(menu_index).get_item(i).timer_is_working()) return item_index=i;  
    	 return item_index;
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
     
      private void update_control_team(int menu_index)
     {
    	   String path=new String();
    	  // sort_bear_buffer(buffer_team);
    	  
    	 for(int i=0;i<buffer_team.length;i++)
    	 {
    		 if(buffer_team[i]!=0 && !menus_game.get(menu_index).get_item(i*2+6).timer_is_working())
    		 {
    			 // 
    			path="menus/neutral/"+Integer.toString(buffer_team[i]/2)+"n.png";
    			menus_game.get(menu_index).change_Image_item(path, i*2+6);
    			path="menus/choosed/"+Integer.toString(buffer_team[i]/2)+"a.png";
    			menus_game.get(menu_index).get_item(i*2+6).time_progress_bar_init(path);
    			menus_game.get(menu_index).get_item(i*2+6).setOpacity(255);
    			menus_game.get(menu_index).get_item(i*2+6).setisTouchEnabled(true);
    			
    			
    		 }
    		 else
    		 {
    			 if(!menus_game.get(menu_index).get_item(i*2+6).timer_is_working()){
    			 menus_game.get(menu_index).get_item(i*2+6).setOpacity(0);
    			 menus_game.get(menu_index).get_item(i*2+6).setisTouchEnabled(false);
    			 }
    			
    			// menus_game.get(0).change_Image_item(path,4);
    			 // 
    			 //menus_game.get(0).change_Image_item("block/"+buffer_team[i]+"b.png", i+4);
    			
    		 }
    		
    	 }
     }
     private int search_bear_team(int menu_tag, int index)
     {
    	 int item_tag = -1;
    	 for (int i=0; i<buffer_team.length; i++ )
    		 if(menus_game.get(menu_tag).get_item(index).getTag()== buffer_team[i])
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
  
     private void button_bears_choose( int menu_index, int menu_move, int item_index)
     {
    	 
    	 CGPoint move_menu =CGPoint.make(menus_game.get(menu_move).getPosition().x, menus_game.get(menu_move).getPosition().y); 
    	 if(menus_game.get(menu_index).get_item(item_index).get_touch_state()){ 
    		
    		 menus_game.get(menu_index).get_item(item_index).set_touch_state(false);
    		   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), raw.bear_growl_002);
    		  menus_game.get(menu_index).change_Image_item("menus/main_unpress.png", item_index);
    		  
    		   menus_game.get(menu_move).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(-menus_game.get(menu_move).scaled_size_width, move_menu.y)), CCCallFunc.action(this, "Menu_Move")));
    		   statusLabel.setString(Float.toString(11f));
              
    		}
    		else
    		{
    			   menus_game.get(menu_index).get_item(item_index).set_touch_state(true);
    			   SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), raw.bear_growl_001);
    			   menus_game.get(menu_index).change_Image_item("menus/main_press.png", item_index);
	      		   menus_game.get(menu_move).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(0, move_menu.y)), CCCallFunc.action(this, "Menu_Move")));
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
