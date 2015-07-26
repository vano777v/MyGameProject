package com.example.game.arena.elements;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class Main_Base extends CCColorLayer{

private  float general_scale_factor=0;
private  CGSize local_scale_factor=null;
private Base_Element base_element=null;
private  Progress_Bar_element bar_life=null;
private Personage_Element boss = null;
private Flag_Element flag = null;
private static  String source_path=null;
private String is_who=null;
	  public Main_Base(ccColor4B color, final String campaign_path,CGSize local_scale_factor, float general_scale_factor, String is_who ) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		this.general_scale_factor=general_scale_factor;
		this.local_scale_factor = CGSize.make(local_scale_factor.width,local_scale_factor.height);
		source_path = campaign_path;
		
		bar_life=  new Progress_Bar_element(source_path+"/bar/main_bar_base.png",source_path+"/bar/progress_bar_base"+is_who+".png", general_scale_factor );
	    boss = new Personage_Element(source_path+"/castle/personage/boss_"+is_who+".png", general_scale_factor);
		base_element =  new Base_Element(source_path+"/castle/base"+is_who+".png", general_scale_factor);
		flag = new Flag_Element(source_path+"/castle/flag/flag_"+is_who+".png", general_scale_factor);
		//bar_life.setBarPosition(CGPoint.make(300, 0));
		
		this.setOpacity(0);
		this.is_who = is_who;
		//this.setContentSize(2048f,2048f);
		addChild(boss);
		addChild(base_element);
		addChild(flag);
		addChild(bar_life);
		//System.gc();
		
	}
	
	
	public void setSizeMain_Base(CGSize main_place, CGSize base_elem, CGSize bar_life_elem)
	{
		  this.setContentSize(main_place.width*general_scale_factor, main_place.height*general_scale_factor);
		  if(this.getContentSize().width>= base_elem.width*general_scale_factor && this.getContentSize().height>= bar_life_elem.height*general_scale_factor)
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
		 //this.setContentSize(main_size.width*general_scale_factor*, main_size.height*general_scale_factor);
		 float scalex = main_size.width*general_scale_factor/this.getContentSize().width;
		 float scaley =  main_size.height*general_scale_factor/this.getContentSize().height;
		 this.setContentSize(main_size.width*general_scale_factor, main_size.height*general_scale_factor);
		 this.setScaleX(scalex/3);
		 this.setScaleY(scaley);
		//base_element.setScaleX(scalex);
		// base_element.setScaleY(scaley);
		 base_element.setscalefactor(scalex, scaley);
		 bar_life.setParentScaleFactors(scalex, scaley);
		 boss.setParentScaleFactors(scalex, scaley);
		 flag.setParentScaleFactors(scalex, scaley);
		
	}
	public void setBasePosition (CGPoint location)
	{
		this.setPosition(location.x,location.y);
	}
	
	public void set_base_elemnt(CGSize bar_size, CGPoint bar_loc,CGSize base_size, CGPoint base_loc,CGSize pers_size, CGPoint pers_loc ,CGSize flag_size, CGPoint flag_loc )
	{
		 base_element.setSize(base_size);
		 boss.setSize(pers_size);
		 bar_life.setSize(bar_size);
		 base_element.setPositionBaseElement(base_loc);
		 bar_life.setBarPosition(bar_loc);
		 boss.set_pers_Position(pers_loc);
		 flag.setSize(flag_size);
		 flag.set_pers_Position(flag_loc);
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
	public void init_flag_move( String path_anim_file, int start_index, int stop_index)
	{
		String path = path_anim_file+"/castle/flag/flagm_"+is_who;
		flag.init_animation(path, start_index, stop_index);
	}
	public void init_pers_default_movie(String path_anim_file, int start_index, int stop_index)
	{
		String path = path_anim_file+"/castle/personage/bossm_"+is_who;
		boss.init_default_animation(path, start_index, stop_index);
	}
}
