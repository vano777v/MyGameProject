package com.example.beargames;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCBitmapFontAtlas;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import org.cocos2d.utils.CCFormatter;

import android.app.Activity;
import android.view.MotionEvent;


import com.example.beargames.Inputs_Outputs.Constants_Game;
import com.example.engine.beargames.Logic_Engine;
//import com.example.beargames.R.raw;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;

public class GameLayer extends CCLayer
{
	private static CGSize screenSize;
	static CCScene mscene = null;
	
	//private float generalscalefactor_h;
	//private float generalscalefactor_w;
	//private float poi=0;
	//private static final int STATUS_LABEL_TAG = 20;
	private static final int TIMER_LABEL_TAG =20;
	
	//private static final int MOVES_LABEL_TAG =20; 
    private static int[] buffer_team;
    private static int[] buffer_ability_items={0,0,0};
    private static int[] buffer_ability_team = {4,6,10,16};
	//private CCMenu gen = null;
	 static CCProgressTimer item1;
	private CCBitmapFontAtlas statusLabel;
	//private int istouch =0;
	private  Game_Arena arena = null;
	private  int  last_touch_ability=0;
	//private  MenuLayer bar_menu, bnm;
	//private CCSprite player;
	//private ArrayList<Integer>  bear_init= null;
	private static int thetime = 0 ;
	private static int team_selected=0;
	///protected static Activity mMyApp;
	//private  MenuLayer choose= null;
	private static String campaign=null;
	private static int count_zoom=0;
	private static float scale_factor=0;
    private  Main_Base bear_base=null;
    private float pers_dim = 512;
    private float base_dimm = 1500;
   // private CCSprite m, n = null; 
    private  Main_Base vimpir_base=null;
    private Logic_Engine logic= null;
	private ArrayList<MenuLayer> menus_game= null;
	  float vp=0 ,dt=0,pz=0;
	 protected Constants_Game const_level= null;
    public GameLayer( String campaign, Game_Arena arena)
    {
    	screenSize = CCDirector.sharedDirector().winSize();
       /// mMyApp = m; 
          java.lang.System.out.println("Antonio"+screenSize);
      //  bear_init= new ArrayList<Integer>();
    	this.setIsTouchEnabled(true);
    	this.campaign = campaign;
        menus_game = new ArrayList<MenuLayer>();
        buffer_team =  new int[4];
    	CGSize percents = CGSize.make(800/2560f, 1082/1600f);
    	float pp = screenSize.height/1600f;
    	this.setContentSize(screenSize.width*3, 1600*pp);
    	scale_factor=pp;
        CGSize scale_factors = CGSize.make(1, 1);
        count_zoom=2;  
       
        this.logic =  logic;
        this.arena =  arena;
        addChild(arena);
        
       
        this.bears_menu_init(screenSize, percents);
        this.main_menu_init( 0.16f);
        this.ability_menu_init();
     
        this.setting_menu_init();
         this.top_menu_init();
         
        
        
        //this.ablity_items_init();
        //for(int i=1;i<=5; i++)
        	//bear_init.add(i*2);
        
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
    			
    			path = this.campaign+"menus/block/"+(i)+"b.png";
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
    private int trans=0;
     public boolean ccTouchesEnded(MotionEvent event) 
     {
    	
    	//;
    	 CGPoint location  = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
    	 int menu_tag = -1;
    	 
    	 java.lang.System.out.println("AIIII "+location);
    	 
    	 for(int i=0; i<menus_game.size(); i++)
    	 {
  
    		if( menus_game.get(i).ccTouchesEnded(location));
    		{
    		   java.lang.System.out.println("Lungu "+menu_tag);	
    	    	 menu_tag = menus_game.get(i).get_touch_menu_state();
    	    	 if(menu_tag!=-1) { java.lang.System.out.println("AIIII loc "+menu_tag);
    	    		 break;
    	    	}
    	    	
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
		      		//SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
		      		statusLabel.setString(Float.toString(12f));
		      		break;
		      	}
		      	
		      	case 3:
		      	{
		      		//this.button_ability();
		      		
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
			   case 1:
			   {
				   button_ability_press(2,1 );
				   break;
			   }
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
			   switch (menus_game.get(menu_tag-1).get_item_touch_tag()) {
		   		case 1: 
		   	   {  
		   		   
		   		   button_sound_press(menu_tag-1,1);
		   		 
		   		   //statusLabel.setString(Float.toString(31f));
		   		   break;
		   	   }
		   		case 2: 
		   		{
		   			button_restart_press(menu_tag-1, 2);
		   			trans -=20;
		   			//level.setPosition(trans,menus_game.get(1).scaled_size_height);
		   			
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 3: 
		   		{
		   			button_save_press(menu_tag-1, 3);
		   			statusLabel.setString(Float.toString(22f));
		   			break;
		   		}
		   		case 4: 
		   		{
		   			this.button_pause_press(menu_tag-1,4);
		   			
		   			menus_game.get(3).set_touch_menu_state(-1);
		   			break;
		   		}
		   		case 5: 
		   		{
		   			button_stop_press(menu_tag-1,5);
		   			
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
		   		case 4: 
		   	   {
		   		   this.button_zoom_out(menu_tag-1, 4);
		   		   menus_game.get(4).set_touch_menu_state(-1);
		   		   statusLabel.setString(Float.toString(51f));
		   		   break;
		   	   }
		   		case 3: 
		   		{
		   			this.button_zoom_in(menu_tag-1,3);
		   			statusLabel.setString(Float.toString(22f));
		   			menus_game.get(4).set_touch_menu_state(-1);
		   			break;
		   		}
			   
			   
			   }
			   break;
		   }  
		   
		  
		
		default: 
		{arena.touch_detect_arena(menus_game.get(4), menus_game.get(1), screenSize,location );
			statusLabel.setString("0");
			break;
		}
		}
         return true;
    	
}
     
     public boolean ccTouchesBegan (MotionEvent event) 
     {
    	 CGPoint location =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY()));
    	 if(arena.touch_detect_arena(menus_game.get(4), menus_game.get(1), screenSize,location ) )
    	 {
    		 arena.ccTouchesBegan(location);
    	 }
    	 return true;
     }
     public boolean ccTouchesMoved(MotionEvent event)
     {
    	 CGPoint location =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY()));
    	 if(arena.touch_detect_arena(menus_game.get(4), menus_game.get(1), screenSize,location ) )
    	 {
    		 arena.ccTouchesMoved(location);  
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
    	Main_Personage pers= null;
    	Main_Personage pers2= null;
    	if(menus_game.get(menu_index).get_item(index).get_touch_state())
    	 {
    		 java.lang.System.out.println("Trala lala la");
    	 }
    	 else
    	 {
    		 if(arena.count_personages("b")<arena.unit_limit_bears) 
     		{
     		   pers= new Main_Personage( const_level.bear_team_fight.get(item_tag-1));
    		 menus_game.get(menu_index).change_Image_item(this.campaign+"menus/block/"+item_tag+"b.png", index);
    		 menus_game.get(menu_index).get_item(index).setisTouchEnabled(false);
    		 menus_game.get(menu_index).get_item(index).set_touch_state(true);
    		 menus_game.get(menu_index).get_item(index).time_progress_bar_init(this.campaign+"menus/choosed/"+item_tag+"a.png");
    		 menus_game.get(menu_index).get_item(index).set_path_progress_bar(this.campaign+"menus/neutral/"+item_tag+"n.png");
    		  
    		 //pers=arena.add_personage("campaign_1/","b_box", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
    		 //pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
    		 //const_level.init_animation_bear_box_engine("default/", pers);
             //pers.set_coordanate(CGSize.make(168, 168), CGSize.make(168, 168));
    		 arena.add_personage(pers); 
    		 menus_game.get(menu_index).get_item(index).time_progress(pers,arena,menus_game.get(bear_menu_index),item_tag, team_selected, buffer_team);
    		 menus_game.get(bear_menu_index).get_item(item_tag*2).setisTouchEnabled(false);
    		 menus_game.get(bear_menu_index).change_Image_item(this.campaign+"menus/press/"+item_tag+"p.png", item_tag*2);
    		 team_selected++;
    		
    		 java.lang.System.out.println("kkkk "+item_tag);
    		 if((team_selected-menus_game.get(menu_index).get_item(index).team_count)==4||(team_selected-menus_game.get(menu_index).get_item(index).team_count)==0)
    		 {
    			 for(int i=2;i<18;i+=2)
    			 {
    			   if(menus_game.get(bear_menu_index).get_item(i).get_isTouchEnabel()&& !menus_game.get(bear_menu_index).get_item(i).get_touch_state()){	 
    				  menus_game.get(bear_menu_index).change_Image_item(this.campaign+"menus/neutral_press/"+(i/2)+"np.png", i);
    				 // menus_game.get(bear_menu_index).get_item(i).set_touch_state(true);
    				  menus_game.get(bear_menu_index).get_item(i).setisTouchEnabled(false);
    			   }
    			 }
    		 }
    		 if(team_selected==4) team_selected=0;
    	 }
    	 }
     }
     private void button_ability_item_press(int index, int menu_index)
     {
    	 String path=this.campaign+"menus/ability_icons/"+last_touch_ability+"ab_i/1a.png";
	     menus_game.get(menu_index).change_Image_item(path, last_touch_ability);
	     menus_game.get(menu_index).get_item(last_touch_ability).setisTouchEnabled(true);
    	 path=this.campaign+"menus/ability_icons/"+index+"ab_i/1p.png";
    	 menus_game.get(menu_index).change_Image_item(path, index);
    
    	 for(int i=0;i<3;i++)
    	 {
    	 	buffer_ability_items[i]=index+i;
    	 	if(i==0)
    	 	{
    	 		 menus_game.get(menu_index).change_Image_item(this.campaign+"menus/ability_icons/"+index+"ab_i/"+index+"_mb_a.png", i+1);
    	    	
    	 	}else
    	 	{
    	 		 menus_game.get(menu_index).change_Image_item(this.campaign+"menus/ability_icons/"+index+"ab_i/"+(i+1)+"a.png", i+1);
    	 	}
    	 	
    	 	
    	 }
    	
    	 menus_game.get(menu_index).get_item(index).setisTouchEnabled(false);
    		 last_touch_ability=index;
    		if(menus_game.get(menu_index).getPosition().y==0){
    			//SoundEngine.sharedEngine().playEffect(mMyApp, R.raw.wooden_door_being_closed); 
    			float coord_y= menus_game.get(menu_index).scaled_size_height- menus_game.get(1).scaled_size_height;
    			 menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(menus_game.get(menu_index).getPosition().x,0f-coord_y)), CCCallFunc.action(this, "Menu_Move")));
    			 menus_game.get(menu_index).get_item(1).set_touch_state(false);
    		}
    		 
    		  
    		 //
    	 
     } 
     private void main_menu_init(float percents)
     {
    	 float perc = screenSize.height/1600;
    	 MenuLayer main_menu =   new MenuLayer(ccColor4B.ccc4(255,255, 255,255),this.campaign,this.campaign+"menus/menu2.png",2, CGSize.make(3048f, 250f), perc);
    	 main_menu.setPosition(0,0);
    	 main_menu.setIsTouchEnabled(true);
    	 main_menu.add_item(this.campaign+"menus/rim_stone.png", 1, CGPoint.make(360, 10), CGSize.make(200, 200));
         main_menu.add_item(this.campaign+"menus/strategy_unpress.png",2, CGPoint.make(370, 20), CGSize.make(180, 180));
         main_menu.add_item(this.campaign+"menus/rim_stone.png",3, CGPoint.make(20, 10), CGSize.make(200, 200));
         main_menu.add_item(this.campaign+"menus/main_unpress.png",4, CGPoint.make(30, 20), CGSize.make(180, 180));
         float button_dist =((screenSize.width-762f*perc-590f*perc-800f*perc)/5f)/perc;
         float dist = button_dist+590f;
         java.lang.System.out.println("dist "+dist);
         int count=1;
         for(int i=5;i<=12;i++)
         {
        	 main_menu.add_item(this.campaign+"menus/rim.png", i, CGPoint.make(dist, 10), CGSize.make(200, 200));
        	 i++;
        	 count=i/2-2;
             main_menu.add_item(this.campaign+"menus/neutral/"+count+"n.png", i, CGPoint.make(dist+10, 20), CGSize.make(180, 180));
             dist+=(200+button_dist);
         } 
         
         
         main_menu.get_item(4).setisTouchEnabled(true);
         main_menu.get_item(6).setisTouchEnabled(true);
         main_menu.get_item(8).setisTouchEnabled(true);
         main_menu.get_item(10).setisTouchEnabled(true);
         main_menu.get_item(12).setisTouchEnabled(true);
    	 addChild(main_menu);
    	 menus_game.add(1, main_menu);
    	 update_control_team(1);
    	 
     }
     private void ability_menu_init()
     {
    	 float perc = screenSize.height/1600f;
    	 MenuLayer ability_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),this.campaign,this.campaign+"menus/menu3.png",3, CGSize.make(762f, 1227f), perc);
    	
    	
    	 
    	
    	 ability_menu.setPosition(screenSize.width-ability_menu.scaled_size_width, 0f-(ability_menu.scaled_size_height-menus_game.get(1).scaled_size_height));
    	
    	 ability_menu.setIsTouchEnabled(true);
    	
    	 addChild(ability_menu);
    	 ablity_items_init(ability_menu,0);
    	 menus_game.add(2, ability_menu);
    	 
    	 button_ability_item_press(last_touch_ability, 2);
     }
     
     private void ablity_items_init(MenuLayer ability_menu , int bear_menu_index)
     {
    	float coord_x=80, coord_y=752;
    	int j=0, count=1, count_bear_main=0;
    	String path=null;
    	ability_menu.add_item(this.campaign+"menus/ability_icons/5ab_i/5_mb_a.png", 1, CGPoint.make(20, 977), CGSize.make(225, 225));
    	ability_menu.get_item(1).setisTouchEnabled(true);
    	ability_menu.add_item(this.campaign+"menus/ability_icons/5ab_i/2a.png", 2, CGPoint.make(320, 1017), CGSize.make(150, 150));
    	ability_menu.get_item(2).setisTouchEnabled(true);
    	ability_menu.add_item(this.campaign+"menus/ability_icons/5ab_i/3a.png", 3, CGPoint.make(570, 1017), CGSize.make(150, 150));
    	ability_menu.get_item(3).setisTouchEnabled(true);
    	 for(int i=4;i<20;i+=4)
    	{
    		 
    		 j=i;
    	 if(menus_game.get(bear_menu_index).get_item(buffer_ability_team[count_bear_main]).get_isTouchEnabel()){	 
    		 ability_menu.add_item(this.campaign+"menus/rim.png", i, CGPoint.make(coord_x,coord_y), CGSize.make(160, 160));
    		 j++;
    		 coord_x+=10;
    		 coord_y+=10;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", j, CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		 ability_menu.get_item(j).setisTouchEnabled(true);
    		 count++;
    		 coord_x+=230;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", (j+1), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count++;
    		 coord_x+=250;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"a.png", (j+2), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count=1;
    		 coord_x=80;
    		 coord_y-=250;
    	 }
    	 else
    	 {
    		 ability_menu.add_item(this.campaign+"menus/rim.png", i, CGPoint.make(coord_x,coord_y), CGSize.make(160, 160));
    		 j++;
    		 coord_x+=10;
    		 coord_y+=10;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", j, CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		 ability_menu.get_item(j).setisTouchEnabled(false);
    		 count++;
    		 coord_x+=230;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", (j+1), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count++;
    		 coord_x+=250;
    		 path=this.campaign+"menus/ability_icons/"+j+"ab_i/"+count;
    		 ability_menu.add_item(path+"b.png", (j+2), CGPoint.make(coord_x,coord_y), CGSize.make(140, 140));
    		
    		 count=1;
    		 coord_x=80;
    		 coord_y-=250;
    	 }
    		 count_bear_main++;
    	}
    	 coord_y=752;
    	 for(int i=20;i<24;i++)
    	 {
    		 ability_menu.add_item(this.campaign+"menus/ability_icons/glass.png", i, CGPoint.make(285, coord_y), CGSize.make(521, 160));
    		 coord_y-=240;
    	 }
    	 
    	 
     }
     private void bears_menu_init( CGSize screen_size, CGSize percents)
     {
    	
    	// CGSize size_menu=CGSize.make( screen_size.width*percents.width, screen_size.height*percents.height);
    	// CGSize scale_factors = CGSize.make(1, 1); 
    	 float s = screen_size.height/1600f;
    	 MenuLayer bears_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),this.campaign,this.campaign+"menus/menu1.png",1, CGSize.make(800f, 1082f), s);    	
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
    		 
    		 bears_menu.add_item(this.campaign+"menus/rim.png", i, CGPoint.make(coordx, coordy), CGSize.make(160f, 160f));
    		 i++;
    		 bears_menu.add_item(this.campaign+"menus/neutral/"+(i/2)+"n.png", i, CGPoint.make(coordx+10, coordy+10), CGSize.make(140f, 140f));
    		 bears_menu.get_item(i).setisTouchEnabled(true);
    		 java.lang.System.out.println(this.campaign+"menus/neutral/"+i+"n.png");
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
    	final  MenuLayer top_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),this.campaign,this.campaign+"menus/menu4.png",5, CGSize.make(3840f, 188f), perc);
    	 top_menu.setOpacity(0);
    	 float orig_x= (top_menu.scaled_size_width-screenSize.width)/perc;
    	 top_menu.setPosition(screenSize.width-top_menu.scaled_size_width,screenSize.height-top_menu.scaled_size_height);
    	 top_menu.add_item(this.campaign+"menus/rim_stone.png", 1, CGPoint.make(orig_x+20,12f), CGSize.make(170f, 170f));
    	 top_menu.add_item(this.campaign+"menus/rim_stone.png", 2, CGPoint.make(3650f,12f ), CGSize.make(170, 170));
    	 
    	 top_menu.add_item(this.campaign+"menus/settings_items/zoom_in_unpress.png", 3, CGPoint.make(orig_x+30,22f ), CGSize.make(150, 150));
    	 top_menu.add_item(this.campaign+"menus/settings_items/zoom_out_unpress.png", 4, CGPoint.make(3660f,22f ), CGSize.make(150, 150));
    	 top_menu.get_item(3).setisTouchEnabled(true);
    	 top_menu.get_item(4).setisTouchEnabled(true);
    	 top_menu.setIsTouchEnabled(true);
    	 

    	 addChild(top_menu);
    	// java.lang.System.out.println("MASAAA"+top_menu.getVertexZ() );
    	menus_game.add(4, top_menu);
     }
     
    private void setting_menu_init ()
     {
    	
    	 float perc = screenSize.height/1600f;
    	 final MenuLayer setting_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),this.campaign,this.campaign+"menus/menu5.png",4, CGSize.make(762f, 754f), perc);
    	setting_menu.setOpacity(0);
    	 setting_menu.setPosition(screenSize.width-975f*perc,screenSize.height-188f*perc);
    	 setting_menu.add_item(this.campaign+"menus/settings_items/sound_on.png", 1, CGPoint.make(93,270 ), CGSize.make(150, 150));
    	 setting_menu.get_item(1).setisTouchEnabled(true);
    	 setting_menu.add_item(this.campaign+"menus/settings_items/restart_unpress.png", 2, CGPoint.make(343,270 ), CGSize.make(150, 150));
    	 setting_menu.get_item(2).setisTouchEnabled(true);
    	 setting_menu.add_item(this.campaign+"menus/settings_items/save_unpress.png", 3, CGPoint.make(588,270 ), CGSize.make(152, 150));
    	 setting_menu.get_item(3).setisTouchEnabled(true);
    	 setting_menu.add_item(this.campaign+"menus/settings_items/pause.png", 4, CGPoint.make(588,23 ), CGSize.make(152, 150));
    	 setting_menu.get_item(4).setisTouchEnabled(true);
    	 setting_menu.add_item(this.campaign+"menus/settings_items/stop_unpress.png", 5, CGPoint.make(588,518 ), CGSize.make(152, 150));
    	 setting_menu.get_item(5).setisTouchEnabled(true);
    	 
    	 setting_menu.setIsTouchEnabled(true);
    	 //setting_menu.setVsetVertexZ(-1);
    	 addChild(setting_menu);
    	 //java.lang.System.out.println("MASAAA"+setting_menu.getVertexZ() );
    	 menus_game.add(3, setting_menu);
     }
     
     private void button_item_bears_press(int item_index, int menu_tag, int menu_main_index)
     {
    	 int item_team=-1, place_free=-1;
    	 int delete_item=0;
    	 String path = new String();
    	 //java.lang.System.out.println("Zaibali "+menu_tag+" "+item_index);
    	 if(menus_game.get(menu_tag).get_item(item_index).get_touch_state())
    	 {
    		path =  this.campaign+"menus/neutral/"+(item_index/2)+"n.png";
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
    		 path = this.campaign+"menus/choosed/"+(item_index/2)+"a.png";
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
    				 path = this.campaign+"menus/neutral/"+(tag/2)+"n.png";
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
    			path=this.campaign+"menus/neutral/"+Integer.toString(buffer_team[i]/2)+"n.png";
    			menus_game.get(menu_index).change_Image_item(path, i*2+6);
    			path=this.campaign+"menus/choosed/"+Integer.toString(buffer_team[i]/2)+"a.png";
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
    		  // SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), raw.bear_growl_002);
    		  menus_game.get(menu_index).change_Image_item(this.campaign+"menus/main_unpress.png", item_index);
    		  
    		   menus_game.get(menu_move).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(-menus_game.get(menu_move).scaled_size_width, move_menu.y)), CCCallFunc.action(this, "Menu_Move")));
    		   statusLabel.setString(Float.toString(11f));
              
    		}
    		else
    		{
    			   menus_game.get(menu_index).get_item(item_index).set_touch_state(true);
    			  // SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), raw.bear_growl_001);
    			   menus_game.get(menu_index).change_Image_item(this.campaign+"menus/main_press.png", item_index);
	      		   menus_game.get(menu_move).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(0, move_menu.y)), CCCallFunc.action(this, "Menu_Move")));
	      		   statusLabel.setString(Float.toString(11f));
	      		   
    		}
    	// for(int i=0;i<buffer_team.length;i++)
    	 //{
    		// System.out.println("GGGGG "+buffer_team[i]);
    	// }
    	 
     }
     
     private void button_zoom_out (int menu_index, int item_tag)
     {
    	if(count_zoom<3&&count_zoom>0)
    	{
    		
    	  menus_game.get(menu_index).get_item(item_tag).button_press(this.campaign+"menus/settings_items/zoom_out_unpress.png",this.campaign+"menus/settings_items/zoom_out_press.png", 0.2f);
    	  scale_factor/=1.2f;
    	
    	  for(int i=0;i<arena.bears_element.size();i++)
    		  arena.bears_element.get(i).start_animation("death");
    	  
    	  arena.test.start_move_elipse(100f, CGPoint.make(250f, 250f), 250, CGPoint.make(500, 250), true);
    	  //arena.get_Main_Base_list(0).get_animation_element(0).start_action(0);
    	//  arena.get_Main_Base_list(0).get_animation_element(0).start_action(1);
    	 // arena.get_Main_List("b", 0).get_animation(0).start_action(1);
    	 // arena.get_Main_Base_list(0).get_animation_element(0).start_action(0);
    	 // arena.set_paralax_scale(scale_factor);
    	  ///System.out.println("Scale arena "+arena.getContentSize().width+" "+vp+" "+vimpir_base.getContentSize()+" "+vimpir_base.getScaleX());
    	  arena.paralax_zoom_out(1.2f);
    	  
    	 
    	 
    	  
    	     	  count_zoom--;
    	}

		
     }
     private void button_zoom_in (int menu_index, int item_tag)
     {
    	 
    	 
        // level.zoom_in();
    	 if(count_zoom<2&&count_zoom>=0)
    	 {
    		 //float mc = arena.getPosition();
    		 
    		 
    		
    		 //arena.get_Main_Base_list(0).get_animation_element(0).action_stop(1);
    		 //arena.get_Main_List("b", 0).get_animation(0).start_action(2);
    		 menus_game.get(menu_index).get_item(item_tag).button_press(this.campaign+"menus/settings_items/zoom_in_unpress.png",this.campaign+"menus/settings_items/zoom_in_press.png", 0.2f);
    		scale_factor *=1.2f;
    		//arena.get_Main_Base_list(0).get_animation_element(0).start_action(0);
    		
    		arena.paralax_zoom_in(1.2f);
    		
    		 
    		count_zoom++;
    		
    	 }
		
     }
      private void button_ability_press(int menu_index, int menu_item)
     {
    	   if(!menus_game.get(menu_index).get_item(menu_item).get_touch_state()){
    		   menus_game.get(menu_index).change_Image_item(this.campaign+"menus/ability_icons/"+buffer_ability_items[0]+"ab_i/"+buffer_ability_items[0]+"_mb_p.png",menu_item );
    	   	   menus_game.get(menu_index).get_item(menu_item).set_touch_state(true);
    	   	   if(menus_game.get(menu_index+1).getPosition().y==(screenSize.height-menus_game.get(menu_index+1).scaled_size_height+menus_game.get(menu_index+1).get_item(5).getSize().height*0.5f)){
    	   	   		button_pause_press(menu_index+1, 4);
    	   	   		menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(menus_game.get(menu_index).getPosition().x, 0)), CCCallFunc.action(this, "Menu_Move")));	
    	   	   }
    	   	   else
    	   	   {
    	   		menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(menus_game.get(menu_index).getPosition().x, 0)), CCCallFunc.action(this, "Menu_Move")));	
    	   		   ///SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wooden_door_open); 
    	   	   }
    	   }
    	   else
    	   {
    		   menus_game.get(menu_index).change_Image_item(this.campaign+"menus/ability_icons/"+buffer_ability_items[0]+"ab_i/"+buffer_ability_items[0]+"_mb_a.png",menu_item );
    	   	   menus_game.get(menu_index).get_item(menu_item).set_touch_state(false);
    	   	  /// SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wooden_door_being_closed); 
    	   	   menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(menus_game.get(menu_index).getPosition().x, 0-menus_game.get(menu_index).scaled_size_height+menus_game.get(menu_index-1).scaled_size_height)), CCCallFunc.action(this, "Menu_Move")));	
    	   }
     }
      private void button_stop_press(int menu_index, int item_tag )
      {
    	  menus_game.get(menu_index).get_item(item_tag).button_press(this.campaign+"menus/settings_items/stop_unpress.png", this.campaign+"menus/settings_items/stop_press.png", 0.2f);
    	  for(int i=menus_game.size()-1;i>=0;i--){
				//menus_game.get(i).Destroy();
				//menus_game.remove(i);
    		java.lang.System.out.println("DD "+i);
    	  }
    	 // SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
    		//mMyApp.finish();
    	 
      }
      
      private void button_sound_press (int menu_index, int item_tag)
      {
    	  if(menus_game.get(menu_index).get_item(item_tag).get_touch_state()){ 
   		   
     		 menus_game.get(menu_index).get_item(item_tag).set_touch_state(false);
     		 menus_game.get(menu_index).change_Image_item(this.campaign+"menus/settings_items/sound_on.png", item_tag);
     		   statusLabel.setString(Float.toString(41f));
     		  
     		 // SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
     		  
     		}
     		else
     		{
     			menus_game.get(menu_index).get_item(item_tag).set_touch_state(true);
       		    menus_game.get(menu_index).change_Image_item(this.campaign+"menus/settings_items/sound_off.png", item_tag);
       		    statusLabel.setString(Float.toString(41f));
       		// SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
   	      		 
     		}
    	  
      }
     private void button_pause_press(int menu_index, int item_index) 
     {
    	 float coord_x= menus_game.get(menu_index).getPosition().x;
    	 if(menus_game.get(menu_index).get_item(item_index).get_touch_state()){ 
    		   
    		 menus_game.get(menu_index).get_item(item_index).set_touch_state(false);
    		 menus_game.get(menu_index).change_Image_item(this.campaign+"menus/settings_items/pause.png", item_index);
    		// SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wood_internal_door_handle_movement_version_2);
    		menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(coord_x,screenSize.height-menus_game.get(4).scaled_size_height)), CCCallFunc.action(this, "Menu_Move")));
    		   statusLabel.setString(Float.toString(41f));
    		  
             
    		  
    		}
    		else
    		{
    			menus_game.get(menu_index).get_item(item_index).set_touch_state(true);
      		  	menus_game.get(menu_index).change_Image_item(this.campaign+"menus/settings_items/play.png", item_index);
      		  		if(menus_game.get(menu_index-1).getPosition().y==0)
      		  		{
      		  			button_ability_press(menu_index-1, 1);
      		  			menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(coord_x,screenSize.height-menus_game.get(menu_index).scaled_size_height+menus_game.get(menu_index).get_item(item_index).getSize().height*0.5f)), CCCallFunc.action(this, "Menu_Move")));   
      		  		}   
      		  		else
      		  		{
      		  		  menus_game.get(menu_index).runAction(CCSequence.actions(CCMoveTo.action(0.5f, CGPoint.make(coord_x,screenSize.height-menus_game.get(menu_index).scaled_size_height+menus_game.get(menu_index).get_item(item_index).getSize().height*0.5f)), CCCallFunc.action(this, "Menu_Move")));   
  		  			  statusLabel.setString(Float.toString(41f));
      		  			//SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.wooden_door_being_closed);
      		  		}
    		}
     }
     private void button_restart_press(int menu_index, int item_tag)
     {
    	 menus_game.get(menu_index).get_item(item_tag).button_press(this.campaign+"menus/settings_items/restart_unpress.png", this.campaign+"menus/settings_items/restart_press.png", 0.2f);
    	 //SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
     }
     private void button_save_press(int menu_index, int item_tag)
     {
    	 menus_game.get(menu_index).get_item(item_tag).button_press(this.campaign+"menus/settings_items/save_unpress.png", this.campaign+"menus/settings_items/save_press.png", 0.2f);
    	 //SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
     }
    public  CCScene scene()
    {
    	CCScene scene = CCScene.node();
    	//GameLayer layer = new GameLayer();
    	//mscene =CCScene.node(); 
    	scene.addChild(this);
    	
    	return scene;
    	
    }
    
   
    
    public void updateTimeLabel(float dt) {
 
    	thetime += 1;
    	String string = CCFormatter.format("%02d:%02d", (int)(thetime /60) , (int)thetime % 60 );
    	CCBitmapFontAtlas timerLabel = (CCBitmapFontAtlas) getChildByTag(TIMER_LABEL_TAG) ;
    	timerLabel.setString(string);
    	}
    private Main_Personage bear_build(int tag) 
    {
    	Main_Personage personage=null;
    	switch (tag) {
		case 1:
			
			  personage=const_level.box_bear_init("default/");
			break;

		default:
			break;
		}
		return personage;
    }  
      
}
