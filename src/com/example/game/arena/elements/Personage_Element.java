package com.example.game.arena.elements;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class Personage_Element extends CCSprite
{
	   private float parent_scale_x = 0;
	   private float parent_scale_y = 0;
	   private float local_scale_x = 0;
	   private float local_scale_y = 0;
	   private float general_scale_factor =0;
	   ArrayList<CCSpriteFrame> personage_def = null;
  public Personage_Element (String location_path, float  general_scale_factor)
  {
	  super(location_path);
	  this.setAnchorPoint(0, 0);
	  this.general_scale_factor = general_scale_factor;
  }
  
  public void setSize(CGSize new_size)
  {
	     local_scale_x = new_size.width/this.getContentSize().width;
	    local_scale_y = new_size.height/this.getContentSize().height;
		
		this.setContentSize(new_size.width*(1/parent_scale_x)*general_scale_factor,new_size.height*(1/parent_scale_y)*general_scale_factor);
		this.setScaleX(local_scale_x*(1/parent_scale_x)*general_scale_factor);
		this.setScaleY(local_scale_y*(1/parent_scale_y)*general_scale_factor);
  }
  public void set_pers_Position(CGPoint location)
  {
	   this.setPosition(location.x*local_scale_x*(1/parent_scale_x)*general_scale_factor, location.y*local_scale_y*(1/parent_scale_y)*general_scale_factor);
  }
  
  public void setParentScaleFactors(float scx, float scy)
  {
	   parent_scale_x=scx;
	   parent_scale_y=scy;
  }
  
  public void init_default_animation(String path_anim_file, int start_index, int stop_index)
	{
		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames(path_anim_file+".plist");
	    // set png file created with 
		CCSpriteSheet spriteSheet = CCSpriteSheet.spriteSheet(path_anim_file+"+.png");
		
	    addChild(spriteSheet);
	    personage_def = new ArrayList<CCSpriteFrame>();
	    for(int i=start_index; i<stop_index;i++)
	    	personage_def.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName( i+".png"));
	   
	    CCAnimation atAnimation = CCAnimation.animation("boss_movie_default", 0.8f, personage_def);
	    CCRepeatForever atAction = CCRepeatForever.action(CCAnimate.action(atAnimation, false));
	    this.runAction(atAction);
		
	}
  
}
