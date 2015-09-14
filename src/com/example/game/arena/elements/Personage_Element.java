package com.example.game.arena.elements;

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
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class Personage_Element extends CCSprite
{
	   private float parent_scale_x = 0;
	   private float parent_scale_y = 0;
	   private float local_scale_x = 0;
	   private float local_scale_y = 0;
	   private float general_scale_factor =0;
	  ; 
	   private String is_who =null;
	   //ArrayList<CCSpriteFrame> personage_def = null;
  public Personage_Element (String location_path, float  general_scale_factor, String is_who)
  {
	  super(location_path);
	  
	  this.setAnchorPoint(0, 0);
	  this.general_scale_factor = general_scale_factor;
	
	  this.is_who = is_who;
  }
  
  public Personage_Element(Personage_Element main)
  {
	  super(main.getTexture());
	  this.setAnchorPoint(main.getAnchorPoint());
	  this.general_scale_factor = main.general_scale_factor;
	  this.is_who= new String(main.is_who);
	  this.setContentSize(main.getContentSize());
	  this.setScaleX(main.getScaleX());
	  this.setScaleY(main.getScaleY());
	  this.local_scale_x = main.local_scale_x;
	  this.local_scale_y = main.local_scale_y;
	  this.setPosition(main.getPosition());
	  this.parent_scale_x = main.parent_scale_x;
	  this.parent_scale_y = main.parent_scale_y;
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
  int k=1;
 


  
 
}
  

