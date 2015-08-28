package com.example.engine.beargames;

import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.os.storage.StorageManager;

public class Action_Activity extends CCLayer
{
   
   private ArrayList<CCAction> action_animation = null;
   private String is_who = null;
   private CCSprite sprite= null;
   private ArrayList<Boolean> action_type = null;
   private int last_action_index=-1,  stop_index=-1;
   private ArrayList<Integer> index_current = null;

   public Action_Activity(CCSprite sprite, String is_who)
   {
	
	  action_animation= new ArrayList<CCAction>();
	  action_type = new ArrayList<Boolean>();
	  index_current = new ArrayList<Integer>();
	  this.is_who= is_who;
	  this.sprite = sprite;
	 
	  
   }
	
	public void  add_animation(String animation_path,String file_name, String name_action, float speed_action, int start_index, int stop_index, Boolean is_forever )
	{
		ArrayList<CCSpriteFrame>personage_frame =null; 
		CCRepeatForever atAction_forever = null;
	    CCRepeat atAction_interval = null;
		   CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames(animation_path+file_name+is_who+".plist");
		    // set png file created with 
			CCSpriteSheet spriteSheet = CCSpriteSheet.spriteSheet(animation_path+file_name+is_who+".png");
			CCRepeat d;
			
		    sprite.addChild(spriteSheet);
		    personage_frame = new ArrayList<CCSpriteFrame>();
		    for(int i=start_index; i<stop_index;i++)
		    	personage_frame.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName( i+".png"));
		    CCAnimation atAnimation = CCAnimation.animation(name_action, speed_action, personage_frame);
		    action_type.add(is_forever);
		    if(is_forever)
		    {
		    	atAction_forever = CCRepeatForever.action(CCAnimate.action(atAnimation, false));
		    	atAction_forever.setTag(action_type.size()-1);
		    	action_animation.add(atAction_forever);
		    }
		    else 
		    {
		    	atAction_interval = CCRepeat.action(CCAnimate.action(atAnimation, false), 1);
		    	atAction_interval.setTag(action_type.size()-1);
		    	action_animation.add(atAction_interval);
		    }
		    
		  
		    
	}
	
	
	public Boolean get_is_forever(int index)
	{
		if(action_type.size()< index) return null;
		return action_type.get(index);
	}
	
	public void remove_action (int index)
	{
		if(action_animation.size()>index && action_type.size()>index && !action_animation.isEmpty())
		{
			sprite.stopAction(action_animation.get(index));
			action_animation.remove(index);
			action_type.remove(index);
		
		}
	
	}
	
	public void start_action(int index)
	{ 
	
	   
		if(last_action_index!=-1)
		{
			if(!get_is_forever(last_action_index)&& get_is_forever(last_action_index)!=null)
			{
				
				if(index_current.isEmpty())
						this.schedule("action_run",0.01f);
				index_current.add(index);
			}
			else 
			{
				if(get_is_forever(last_action_index)!=null)
				{
				   
					if(index_current.isEmpty())
						this.schedule("action_run", 0.01f);
					index_current.add( index);
				}
			}
		}
		else
		{
			sprite.runAction(action_animation.get(index));
			last_action_index = index;
		}
			
	}
	
	public void action_stop(int index)
	{
		stop_index = index;
			if(!get_is_forever(index)&& get_is_forever(index)!=null)
			{
			
					this.schedule("action_scan", 0.01f);
					
			}
			else 
			{ 
				if(get_is_forever(index)!=null)
				{
					
					this.schedule("action_scan", 0.01f);
				}
			}
		
	}
	public void Destroy()
	{
		for (int i=action_animation.size()-1; i>=0;i--)
		{
			sprite.getAction(i).stop();
			action_animation.remove(i);
			action_type.remove(i);
		}
		
	     sprite.cleanup();
	     action_animation = null;
	     action_type = null;
	     last_action_index=0;
	     index_current.clear();
	     index_current=null;
	}
 public void action_run(float dt)
 {
	
	 System.out.println("Vera1");
   if(!get_is_forever(last_action_index))
   {
	 if(action_animation.get(last_action_index).isDone()&&!this.index_current.isEmpty())
	 {
		 
		 sprite.runAction(action_animation.get(index_current.get(0)));
		 last_action_index=index_current.get(0);
		 index_current.remove(0);
		 //this.unschedule("action_run");
	 }
   }
   else
   {
	   if(action_animation.get(last_action_index).is_Done()&&!index_current.isEmpty())
		 {
			 
			 sprite.stopAction(last_action_index);
		     sprite.runAction(action_animation.get(index_current.get(0)));
			 last_action_index=index_current.get(0);
			 index_current.remove(0);
			 //this.unschedule("action_run");
		 }
   }
   if(index_current.isEmpty()) this.unschedule("action_run");
 }


public void action_scan(float dt)
{
	if(stop_index!=-1)
	{
		
		    if(get_is_forever(stop_index))
		    {
		      if(action_animation.get(stop_index).is_Done())
		      {
		    	    sprite.stopAction(stop_index);
					last_action_index=-1;
					this.unschedule("action_scan");	
		      }
		    }
		    else
		    {
		    	
		    	if(action_animation.get(stop_index).isDone())
		    	{
		    		sprite.stopAction(stop_index);
		    		last_action_index=-1;
		    		this.unschedule("action_scan");
		    	}
		    }
			
			
	}
	
}
	
public int get_who_is_runing()
{
	return last_action_index;
}
	
}
