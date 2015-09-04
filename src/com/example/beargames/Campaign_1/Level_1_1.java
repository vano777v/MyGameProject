package com.example.beargames.Campaign_1;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.engine.beargames.Level;
import com.example.engine.beargames.Logic_Engine;


public class Level_1_1 extends Level

{
	private Logic_Engine logic_level=null;
   public Level_1_1(int campaign, int level, float general_scale_factor, CGSize screenSize)
   {
	   super(campaign, level,Const_Lev_1_1.arena_area_coff, Const_Lev_1_1.unit_limit, general_scale_factor, screenSize);
	   logic_level =  new Logic_Engine(this, this.Game_Arena_init(), general_scale_factor);
   }
   
   public Logic_Engine get_logic_game ()
   {
	 return logic_level;   
   }
   
}
