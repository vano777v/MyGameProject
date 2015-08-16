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
	   private ArrayList<CCAction> base_action=null;
	   private int last_action_runing=-1; 
	   private String is_who =null;
	   //ArrayList<CCSpriteFrame> personage_def = null;
  public Personage_Element (String location_path, float  general_scale_factor, String is_who)
  {
	  super(location_path);
	  this.setAnchorPoint(0, 0);
	  this.general_scale_factor = general_scale_factor;
	  base_action =  new ArrayList<CCAction>();
	  this.is_who = is_who;
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
  public void add_action(String path_anim_file, String name_action,  float speed_action, int start_index, int stop_index)
	{
		ArrayList<CCSpriteFrame>personage_frame =null; 
	   CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames(path_anim_file+"castle/personage/animation/"+is_who+"/"+name_action+".plist");
	    // set png file created with 
		CCSpriteSheet spriteSheet = CCSpriteSheet.spriteSheet(path_anim_file+"castle/personage/animation/"+is_who+"/"+name_action+".png");
		
	    addChild(spriteSheet);
	    personage_frame = new ArrayList<CCSpriteFrame>();
	    for(int i=start_index; i<stop_index;i++)
	    	personage_frame.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName( i+".png"));
	 
	   
	   
	    CCAnimation atAnimation = CCAnimation.animation("boss_movie_default", speed_action, personage_frame);
	    CCRepeatForever atAction = CCRepeatForever.action(CCAnimate.action(atAnimation, false));
         
	    base_action.add(atAction);
     
        //base_action.add(ghj);
	}  
		
  public void start_action(int index_action)
  {
	  if(last_action_runing!=-1)
	  {
		  this.stopAction(base_action.get(last_action_runing));
		  this.runAction(base_action.get(index_action));
		  last_action_runing=index_action;
	  }else
	  {
		  this.runAction(base_action.get(index_action));
		  last_action_runing=index_action;
	  }
	  
  }
  public CCAction getActionBase(int index_action )
  {
	  return base_action.get(index_action);
  }
  
  public void stop_action(int index_action)
  {
	  this.stopAction(base_action.get(index_action));
	  last_action_runing=-1;
  }
}
  

