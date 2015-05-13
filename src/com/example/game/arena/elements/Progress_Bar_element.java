package com.example.game.arena.elements;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Progress_Bar_element  extends CCSprite
{
   private  CCProgressTimer progress_bar_life = null;
   Progress_Bar_element (String path_image, String path_progress_bar)
   {
	   super(path_image);
	   this.setAnchorPoint(0, 0);
	   progress_bar_init(path_progress_bar);
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
   
}
