package com.example.engine.beargames;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;

import android.os.storage.StorageManager;

public class Action_Activity 
{
   private ArrayList<CCRepeatForever> forever_animation= null;
   private ArrayList<CCRepeat> interval_animation= null;
   private String is_who = null;
   private CCSprite sprite= null;
   private ArrayList<Boolean> action_type = null;
   private int last_action_index=-1;
   public Action_Activity(CCSprite sprite, String is_who)
   {
	  forever_animation = new ArrayList<CCRepeatForever>();
	  interval_animation = new ArrayList<CCRepeat>();
	  action_type = new ArrayList<Boolean>();
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
		    	forever_animation.add(atAction_forever);
		    }
		    else 
		    {
		    	atAction_interval = CCRepeat.action(CCAnimate.action(atAnimation, false), 1);
		    	atAction_interval.setTag(action_type.size()-1);
		    	interval_animation.add(atAction_interval);
		    }
		    
		    
		  
		   
		    
		    
	}
	
	/*public CCAction get_Animation (int index)
	{
	   if(index>action_animation.size()) return null;
	   else
		 return action_animation.get(index);
	}*/
	
	public Boolean get_is_forever(int index)
	{
		if(action_type.size()< index) return null;
		return action_type.get(index);
	}
	
	/*public int remove_action (int index)
	{
		if(action_animation.size()>index && action_type.size()>index && !action_animation.isEmpty())
		{
			sprite.stopAction(action_animation.get(index));
			action_animation.remove(index);
			action_type.remove(index);
			return 1;
		}
		else return 0;
	}*/
	private int get_index(int index)
	{ 
		int get_index =-1, count=0;
		Boolean value = get_is_forever(index);
		if(value)
		{
			for(int i=0;i<index;i++)
				if(action_type.get(i)) count++;
		}
		else
		{
			for(int i=0;i<index;i++)
				if(!action_type.get(i)) count++;
		}
		get_index=count;
		return get_index;
	}
	public void start_action(int index)
	{ 
	
	
		if(last_action_index!=-1)
		{
			if(!get_is_forever(last_action_index)&& get_is_forever(last_action_index)!=null)
			{
				 Boolean value;
				 value = interval_animation.get(0).isDone();
				while (value !=true)
				{
					value = interval_animation.get(0).isDone();
				}
			     
			    	 
				       // sprite.stopAction(0);
						sprite.runAction(forever_animation.get(0));
						last_action_index = index;
						System.out.println("Vera1");
						
				
			}
			else 
			{
				if(get_is_forever(last_action_index)!=null)
				{
					sprite.getAction(last_action_index).stop();
					//sprite.runAction(action_animation.get(index));
					last_action_index = index;
				}
			}
		}
		else
		{
			sprite.runAction(interval_animation.get(0));
			last_action_index = index;
		}
			
	}
	
	public void action_stop(int index)
	{
		
			if(!get_is_forever(index)&& get_is_forever(index)!=null)
			{
				loop:
					if(sprite.getAction(index).isDone())
					{
						sprite.getAction(index).stop();
						last_action_index = -1;
					}
					else
						break loop;
				
			}
			else 
			{ 
				if(get_is_forever(index)!=null)
				{
						sprite.getAction(index).stop();
						last_action_index = -1;
				}
			}
		
	}
	/*public void Destroy()
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
	}*/
	
	
	
}
