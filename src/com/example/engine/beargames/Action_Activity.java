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
   private ArrayList<CCAction> action_animation= null;
   private String is_who = null;
   private CCSprite sprite= null;
   private ArrayList<Boolean> action_type = null;
   private int last_action_index=-1;
   public Action_Activity(CCSprite sprite, String is_who)
   {
	  action_animation = new ArrayList<CCAction>();
	  action_type = new ArrayList<Boolean>();
	  this.is_who= is_who;
	  this.sprite = sprite;
   }
	
	public void  add_animation(String animation_path,String file_name, String name_action, float speed_action, int start_index, int stop_index, Boolean is_forever )
	{
		ArrayList<CCSpriteFrame>personage_frame =null; 
		CCAction atAction = null;
	
		   CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames(animation_path+file_name+is_who+".plist");
		    // set png file created with 
			CCSpriteSheet spriteSheet = CCSpriteSheet.spriteSheet(animation_path+file_name+is_who+".png");
			
		    sprite.addChild(spriteSheet);
		    personage_frame = new ArrayList<CCSpriteFrame>();
		    for(int i=start_index; i<stop_index;i++)
		    	personage_frame.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName( i+".png"));
		    CCAnimation atAnimation = CCAnimation.animation(name_action, speed_action, personage_frame);
		    if(is_forever)
		    	atAction = CCRepeatForever.action(CCAnimate.action(atAnimation, false));
		    else 
		    	atAction = CCRepeat.action(CCAnimate.action(atAnimation, false), 1);
		    action_type.add(is_forever);
		    
		    action_animation.add(atAction);
		    atAction.setTag(action_animation.size()-1);
		    
	}
	
	public CCAction get_Animation (int index)
	{
	   if(index>action_animation.size()) return null;
	   else
		 return action_animation.get(index);
	}
	
	public Boolean get_is_forever(int index)
	{
		if(action_type.size()< index) return null;
		return action_type.get(index);
	}
	
	public int remove_action (int index)
	{
		if(action_animation.size()>index && action_type.size()>index && !action_animation.isEmpty())
		{
			sprite.stopAction(action_animation.get(index));
			action_animation.remove(index);
			action_type.remove(index);
			return 1;
		}
		else return 0;
	}
	public void start_action(int index)
	{
		if(last_action_index!=-1)
		{
			if(!get_is_forever(last_action_index)&& get_is_forever(last_action_index)!=null)
			{
				loop:
					if(sprite.getAction(last_action_index).isDone())
					{
						sprite.runAction(action_animation.get(index));
						last_action_index = index;
					}
					else
						break loop;
				
			}
			else 
			{
				if(get_is_forever(last_action_index)!=null)
				{
					sprite.getAction(last_action_index).stop();
					sprite.runAction(action_animation.get(index));
					last_action_index = index;
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
	}
	
	
	
}
