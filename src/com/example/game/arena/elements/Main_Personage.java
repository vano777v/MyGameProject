package com.example.game.arena.elements;

import java.util.ArrayList;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.engine.beargames.Action_Activity;

public class Main_Personage  extends CCColorLayer{
	private  float general_scale_factor=0;
	private  CGSize local_scale_factor=null;
	private  Progress_Bar_element bar_life=null;
	private ArrayList<Action_Activity>  animation_element=null;
	private Personage_Element boss = null;
	private static  String source_path=null;
	private String is_who=null;
	public Main_Personage(ccColor4B color, final String campaign_path,String default_path,CGSize local_scale_factor, float general_scale_factor, String is_who) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		this.general_scale_factor=general_scale_factor;
		this.local_scale_factor = CGSize.make(local_scale_factor.width,local_scale_factor.height);
		source_path = campaign_path;
	    animation_element = new ArrayList<Action_Activity>();
		bar_life=  new Progress_Bar_element(source_path+"bar/main_bar_pers.png",source_path+"bar/progress_bar_"+is_who+".png", general_scale_factor );
	    boss = new Personage_Element(source_path+"Personages/"+is_who+"/"+default_path+"/"+"1.png", general_scale_factor, is_who);
	    this.setOpacity(30);
		this.is_who = is_who;
		addChild(bar_life);
		addChild(boss);
		animation_element.add(new Action_Activity(boss, is_who));
		this.addChild( animation_element.get(0));
	}
	public void setSize(CGSize main_size)
	{
		 //this.setContentSize(main_size.width*general_scale_factor*, main_size.height*general_scale_factor);
		 float scalex = main_size.width*general_scale_factor/this.getContentSize().width;
		 float scaley =  main_size.height*general_scale_factor/this.getContentSize().height;
		 this.setContentSize(main_size.width*general_scale_factor/local_scale_factor.width, main_size.height*general_scale_factor/local_scale_factor.height);
		 this.setScaleX(scalex/local_scale_factor.width);
		 this.setScaleY(scaley/local_scale_factor.height);
		//base_element.setScaleX(scalex);
		// base_element.setScaleY(scaley);
		 
		 bar_life.setParentScaleFactors(scalex, scaley);
		 boss.setParentScaleFactors(scalex, scaley);
		 
		
	}
	public void set_Pers_Position (CGPoint location)
	{
		this.setPosition(location.x,location.y);
	}
	
	public void set_Pers_element(CGSize bar_size, CGPoint bar_loc,CGSize pers_size, CGPoint pers_loc  )
	{
		
		 boss.setSize(pers_size);
		 bar_life.setSize(bar_size);
		
		 bar_life.setBarPosition(bar_loc);
		 boss.set_pers_Position(pers_loc);
	
	}
	
	public void zoom_in( float scale_factor)
	{
		this.setScaleX(this.getScaleX()*scale_factor);
		this.setScaleY(this.getScaleY()*scale_factor);
		this.setPosition(this.getPosition().x*scale_factor,this.getPosition().y*scale_factor );
	}
	public void zoom_out(float scale_factor)
	{
		this.setScaleX(this.getScaleX()/scale_factor);
		this.setScaleY(this.getScaleY()/scale_factor);
		this.setPosition(this.getPosition().x/scale_factor,this.getPosition().y/scale_factor );
	}

	public Action_Activity get_animation (int index)
	{
		
		return animation_element.get(index);
	}
}
