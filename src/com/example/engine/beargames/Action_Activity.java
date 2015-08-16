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

public class Action_Activity 
{
   private ArrayList<CCAction> action_animation= null;
   private String is_who = null;
   private CCSprite sprite= null;
   public Action_Activity(CCSprite sprite, String is_who)
   {
	  action_animation = new ArrayList<CCAction>();
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
		    
		    action_animation.add(atAction);
		    
	}
	
	public CCAction get_Animation (int index)
	{
	   if(index>action_animation.size()) return null;
	   else
		 return action_animation.get(index);
	}
	
	
	
	
}
