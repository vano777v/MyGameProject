package com.example.beargames.Campaign_1;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.engine.beargames.Level;
import com.example.engine.beargames.Logic_Engine_Level_1_1;

public class Level_1_1 extends Level

{
   public Level_1_1(String campaign, String level, float general_scale_factor, CGSize screenSize)
   {
	   super(campaign, level,Const_Lev_1_1.arena_area_coff, Const_Lev_1_1.unit_limit, general_scale_factor, screenSize);
	   Logic_Engine_Level_1_1 lev_1_1 = new Logic_Engine_Level_1_1(this, this.Game_Arena_init(), general_scale_factor);
   }
}
