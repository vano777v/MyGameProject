package com.example.game.arena.elements;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.ccColor4B;

public class Main_Base extends CCColorLayer{

private float scale_factor=0;
private Base_Element base_element=null;
private Progress_Bar_element bar_life=null;
	protected Main_Base(ccColor4B color, String campaign_path, float scale_factor) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		this.scale_factor=scale_factor;
		base_element =  new Base_Element(campaign_path+"/castle/base.png");
		bar_life =  new Progress_Bar_element(campaign_path+"/bar/main_bar_base.png",campaign_path+"/bar/progress_bar_base.png" );
	}
}
