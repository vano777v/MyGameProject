package com.example.game.arena.elements;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class Main_Base extends CCColorLayer{

private float scale_factor=0;
private Base_Element base_element=null;
private Progress_Bar_element bar_life=null;
private static String source_path=null;
	public Main_Base(ccColor4B color, String campaign_path, float scale_factor, String is_who ) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		this.scale_factor=scale_factor;
		
		source_path = new String(campaign_path);
		//System.out.println(source_path+"/bar/progress_bar_base"+is_who+".png");
		bar_life=  new Progress_Bar_element(source_path+"/bar/main_bar_base.png",source_path+"/bar/progress_bar_base"+is_who+".png", scale_factor );
	
		base_element =  new Base_Element(source_path+"/castle/base"+is_who+".png", scale_factor);
		bar_life.setBarPosition(CGPoint.make(100, 0));
		base_element.setPositionBaseElement(CGPoint.make(0, 0));
		//this.setOpacity(0);
		this.setContentSize(2048f,2048f);
		addChild(bar_life);
		addChild(base_element);
	}
	
	public void setSizeMain_Base(CGSize main_place, CGSize base_elem, CGSize bar_life_elem)
	{
		  this.setContentSize(main_place.width*scale_factor, main_place.height*scale_factor);
		  if(this.getContentSize().width>= base_elem.width*scale_factor && this.getContentSize().height>= bar_life_elem.height*scale_factor)
		  {
			  bar_life.setSize(bar_life_elem);
			  base_element.setSize(base_elem);
		  }
		  else
		  {
			  base_elem = this.getContentSize();
			  bar_life_elem.set(base_elem.width*0.63f, base_elem.height*0.63f);
			  bar_life.setSize(bar_life_elem);
			  base_element.setSize(base_elem);
		  }
	}
	public void setSize(CGSize main_size)
	{
		this.setContentSize(main_size.width*scale_factor, main_size.height*scale_factor);
		//this.setScale(scale_factor);
		 base_element.setSize(main_size);
		 System.out.println("kap"+base_element.getContentSize());
		 bar_life.setSize(CGSize.make(main_size.width*0.63f, main_size.height*0.63f));
	}
	public void setBasePosition (CGPoint location)
	{
		this.setPosition(location.x*scale_factor,location.y*scale_factor);
	}
	
	
}
