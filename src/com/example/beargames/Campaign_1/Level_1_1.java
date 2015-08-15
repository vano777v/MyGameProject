package com.example.beargames.Campaign_1;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Base;

public class Level_1_1 
{
	    private float pers_dim = 512;
	    private float base_dimm = 1500;
		private  Game_Arena arena = null;
		private GameLayer level_1 = null;
	    private  Main_Base  bear_base=null,vimpir_base=null;
	public Level_1_1(String campaign,String level,float general_scale_factor, CGSize screenSize)
	{
		    arena =  new Game_Arena(ccColor4B.ccc4(255,0, 0,255),general_scale_factor,3f,screenSize, pers_dim, base_dimm,6);
	        arena.setPosition(0, 250*general_scale_factor);
	        arena.set_size_arena(CGSize.make(screenSize.width*3, screenSize.height ));
	        arena.set_action_arena(15);
	        arena.add_Paralax_Child(campaign+level+"/paralax/noise.png", CGPoint.make(0, 250f*general_scale_factor),CGPoint.make(0, 0), 0);
	        arena.add_Paralax_Child(campaign+level+"/paralax/tback.png", CGPoint.make(0, 0),CGPoint.make(0.2f, 0), 1);
	        arena.add_Paralax_Child(campaign+level+"/paralax/sback.png", CGPoint.make(0, 70f*general_scale_factor),CGPoint.make(0.3f, 0), 2);
	        arena.add_Paralax_Child(campaign+level+"/paralax/mback.png", CGPoint.make(0,60*general_scale_factor),CGPoint.make(0.1f, 0), 2);
	        bear_base=arena.add_base_node(campaign,"level_1_3" ,CGSize.make(base_dimm, base_dimm), CGPoint.make(16.66f, 0), "b");
	        bear_base.set_base_elemnt(CGSize.make(118, 948), CGPoint.make(0, 0), CGSize.make(base_dimm/1.2f, base_dimm/1.2f), CGPoint.make(0, 0),CGSize.make(pers_dim, pers_dim),CGPoint.make(780, 570), CGSize.make(600, 600), CGPoint.make(260, 600));
	        bear_base.init_flag_move(campaign, 1, 9);
	        bear_base.get_main_Personage().add_action(campaign,"bossm_b", 0.7f, 1, 8);
	        bear_base.get_main_Personage().add_action(campaign,"bossma1_b", 0.5f, 1, 6);
	        bear_base.get_main_Personage().start_action(0);
	        vimpir_base= arena.add_base_node(campaign, "level_1_3",CGSize.make(base_dimm, base_dimm), CGPoint.make(arena.get_action_arena()-base_dimm*general_scale_factor/3f, 0), "v");
	        vimpir_base.set_base_elemnt(CGSize.make(118, 948), CGPoint.make(base_dimm-118f, 0), CGSize.make(base_dimm/1.2f, base_dimm/1.2f), CGPoint.make(0, 0),CGSize.make(pers_dim, pers_dim),CGPoint.make(-50, 420), CGSize.make(600, 600), CGPoint.make(350, 820));
	        vimpir_base.init_flag_move(campaign, 1, 9);
	        vimpir_base.get_main_Personage().add_action(campaign,"bossm_v", 0.5f, 1, 7);
	        vimpir_base.get_main_Personage().start_action(0);
	        vimpir_base.get_main_Personage().add_action(campaign, "bossma1_v", 0.7f, 1, 8);
	         level_1 = new GameLayer(campaign, arena);
	
	}
	
	public GameLayer get_Level()
	{
			return level_1;
		
	}
}
