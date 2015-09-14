package com.example.game.arena.elements;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class Progress_Bar_element  extends CCSprite
{
   private  CCProgressTimer progress_bar_life = null;
   private float parent_scale_x = 0;
   private float parent_scale_y = 0;
   private float local_scale_x = 0;
   private float local_scale_y = 0;
   private float general_scale_factor =0;
   private CCSprite img=null;
   public Progress_Bar_element (String path_image, String path_progress_bar, float scale_factor)
   {
	  // img = new CCSprite(path_image);
	   super(path_image);
	   this.setAnchorPoint(0, 0);
	   progress_bar_init(path_progress_bar);
	   general_scale_factor = scale_factor;
	  // System.out.println("Marcela");
   }
   
   public Progress_Bar_element (Progress_Bar_element main)
   {
	   super(main.getTexture());
	   this.setAnchorPoint(main.getAnchorPoint());
	   this.general_scale_factor = main.general_scale_factor;
	   this.progress_bar_life = CCProgressTimer.progress( main.progress_bar_life.getSprite().getTexture());
	   this.progress_bar_life.setAnchorPoint(main.progress_bar_life.getAnchorPoint());
	   this.progress_bar_life.setPosition(main.progress_bar_life.getPosition());
	   this.progress_bar_life.setType(main.progress_bar_life.getType());
	   this.progress_bar_life.setPercentage(main.progress_bar_life.getPercentage());
	   this.local_scale_x = main.local_scale_x;
	   this.local_scale_y = main.local_scale_y;
	   this.parent_scale_x = main.parent_scale_x;
	   this.parent_scale_y = main.local_scale_y;
	   this.setScaleX(main.getScaleX());
	   this.setScaleY(main.getScaleY());
	   this.setPosition(main.getPosition());
	   this.addChild(this.progress_bar_life);
   }
   private void progress_bar_init(String path)
   {
	     progress_bar_life = CCProgressTimer.progress(path);
		 progress_bar_life.setAnchorPoint(CGPoint.make(0, 0));
		 progress_bar_life.setPosition(0, 0);
		 progress_bar_life.setType(CCProgressTimer.kCCProgressTimerTypeVerticalBarBT);
		 progress_bar_life.setPercentage(100f);
		 this.addChild(progress_bar_life);
   }
   public void setPercentageBarLife(int real_value, int total_base_life)
   {
	   float value = real_value/total_base_life*100;
	   progress_bar_life.setPercentage(value);
   }
   public float  getPercentageBarLife()
   {
	   return this.progress_bar_life.getPercentage();
   }
   
   public void setSize(CGSize new_size)
   {
	     local_scale_x = new_size.width/this.getContentSize().width;
	    local_scale_y = new_size.height/this.getContentSize().height;
		
		this.setContentSize(new_size.width*(1/parent_scale_x)*general_scale_factor,new_size.height*(1/parent_scale_y)*general_scale_factor);
		this.setScaleX(local_scale_x*(1/parent_scale_x)*general_scale_factor);
		this.setScaleY(local_scale_y*(1/parent_scale_y)*general_scale_factor);
   }
   public void setBarPosition(CGPoint location)
   {
	   this.setPosition(location.x*local_scale_x*(1/parent_scale_x)*general_scale_factor, location.y*local_scale_y*(1/parent_scale_y)*general_scale_factor);
   }
   
   public void setParentScaleFactors(float scx, float scy)
   {
	   parent_scale_x=scx;
	   parent_scale_y=scy;
   }
}
