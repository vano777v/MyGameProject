package com.example.game.arena.elements;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;

public class Flag_Element extends Personage_Element
{
	  
	ArrayList<CCSpriteFrame> flag_move = null;
	public Flag_Element(String location_path, float  general_scale_factor, String is_who)
	{
	   super(location_path, general_scale_factor, is_who);	
	   
	}
	
	public void init_animation(String path_anim_file, int start_index, int stop_index)
	{
		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames(path_anim_file+".plist");
	    // set png file created with 
		CCSpriteSheet spriteSheet = CCSpriteSheet.spriteSheet(path_anim_file+".png");
		
	    addChild(spriteSheet);
	    flag_move = new ArrayList<CCSpriteFrame>();
	    for(int i=start_index; i<stop_index;i++)
	    	flag_move.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName( i+".png"));
	   
	    CCAnimation atAnimation = CCAnimation.animation("flag_movie", 0.1f, flag_move);
	    CCRepeatForever atAction = CCRepeatForever.action(CCAnimate.action(atAnimation, false));
	    this.runAction(atAction);
		
	}
	
	

}
