package com.example.engine.beargames;


import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Constants_Game;
import com.example.game.arena.elements.Main_Base;

public class Logic_Engine_Level_1_1 extends GameLayer
{
    private Game_Arena arena = null;
    
    private float general_scale_factor=0;
    private Level level= null;
  
	public Logic_Engine_Level_1_1( Level level,  Game_Arena arena,  float general_scale_factor) 
	{
		super("1", arena);
		this.level = level;
		this.arena = arena;
		this.general_scale_factor = general_scale_factor;
		init_paralax();
		init_bases();
        //box_bear_init("default/");
	}
	

	public void init_bases()
	{       Main_Base bear_base=null, vimpir_base=null;
          //====================init Bear Base==================================	
		    //bear_base=arena.add_base_node(level.get_campaign(),"level_1_3" ,CGSize.make(Constants_Game.base_dim, Constants_Game.base_dim), CGPoint.make(16.66f, 0), "b");
	        bear_base.set_base_elemnt(CGSize.make(118, 948), CGPoint.make(0, 0), CGSize.make(Constants_Game.base_dim/1.2f, Constants_Game.base_dim/1.2f), CGPoint.make(150, 0),CGSize.make(Constants_Game.base_pers_dim, Constants_Game.base_pers_dim),CGPoint.make(930, 570), CGSize.make(600, 600), CGPoint.make(400, 610));
	        init_action_bear_base();
	        start_bear_base_default_movie();
	      //================================================================================================  
	        //vimpir_base= arena.add_base_node(level.get_campaign(), "level_1_3",CGSize.make(Constants_Game.base_dim, Constants_Game.base_dim), CGPoint.make(arena.get_action_arena()-Constants_Game.base_dim*general_scale_factor/3f, 0), "v");
	        vimpir_base.set_base_elemnt(CGSize.make(118, 948), CGPoint.make(Constants_Game.base_dim/1.2f+10f, 0), CGSize.make(Constants_Game.base_dim/1.2f, Constants_Game.base_dim/1.2f), CGPoint.make(0, 0),CGSize.make(Constants_Game.base_pers_dim, Constants_Game.base_pers_dim),CGPoint.make(-50, 420), CGSize.make(600, 600), CGPoint.make(350, 820));
	        init_action_vimpire_base();
	        start_vimpire_base_default_movie();
	        start_flags_movie();
	        
	} 
	
	public void init_paralax()
	{
		    //arena.add_Paralax_Child(level.get_campaign()+level.get_current_level()+"/paralax/noise.png", CGPoint.make(0, 250f*general_scale_factor),CGPoint.make(0, 0), 0);
	        //arena.add_Paralax_Child(level.get_campaign()+level.get_current_level()+"/paralax/tback.png", CGPoint.make(0, 0),CGPoint.make(0.2f, 0), 1);
	        //arena.add_Paralax_Child(level.get_campaign()+level.get_current_level()+"/paralax/sback.png", CGPoint.make(0, 70f*general_scale_factor),CGPoint.make(0.3f, 0), 2);
	        //arena.add_Paralax_Child(level.get_campaign()+level.get_current_level()+"/paralax/mback.png", CGPoint.make(0,60*general_scale_factor),CGPoint.make(0.1f, 0), 2);
	}
	public void init_action_bear_base ()
	{
		    arena.get_Main_Base_list(0).get_animation_element(0).add_animation(level.get_campaign()+Constants_Game.bbase_animation_path,"bossm_","smoking_first",0.4f, 9, 11, false);
	        arena.get_Main_Base_list(0).get_animation_element(0).add_animation(level.get_campaign()+Constants_Game.bbase_animation_path,"bossm_","smoking_loop", 0.5f,1, 9, true);
	        arena.get_Main_Base_list(0).get_animation_element(1).add_animation(level.get_campaign()+"castle/flag/", "flagm_", "falg_movie", 0.1f, 1, 9, true);
	} 
	
	public void init_action_vimpire_base()
	{
		arena.get_Main_Base_list(1).get_animation_element(0).add_animation(level.get_campaign()+Constants_Game.vbase_animation_path,"bossm_", "first_step", 0.3f, 1,3,false);
		arena.get_Main_Base_list(1).get_animation_element(0).add_animation(level.get_campaign()+Constants_Game.vbase_animation_path,"bossm_", "first_step", 0.4f, 3,7,true);
		arena.get_Main_Base_list(1).get_animation_element(1).add_animation(level.get_campaign()+"castle/flag/", "flagm_", "falg_movie", 0.1f, 1, 9, true);	
	}
	
	public void start_flags_movie()
	{
		 arena.get_Main_Base_list(0).get_animation_element(1).start_action(0);
		 arena.get_Main_Base_list(1).get_animation_element(1).start_action(0);
	}
	public void start_bear_base_default_movie()
	{
		arena.get_Main_Base_list(0).get_animation_element(0).start_action(0);
		arena.get_Main_Base_list(0).get_animation_element(0).start_action(1);
	}
	public void start_vimpire_base_default_movie()
	{
		arena.get_Main_Base_list(1).get_animation_element(0).start_action(0);
		arena.get_Main_Base_list(1).get_animation_element(0).start_action(1);
	}
	
	public void box_bear_init( String update_path)
	{
		//arena.add_personage(level.get_campaign(),"b_box", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		arena.get_Main_List("b", 0).set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(614f, 614f), CGPoint.make(0, 0));
		init_animation_bear_box_engine(update_path);
	}
	
	public void init_animation_bear_box_engine(String update_path)
	{
	    arena.get_Main_List("b", 0).get_animation(0).add_animation(level.get_campaign()+Constants_Game.bear_box_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        arena.get_Main_List("b", 0).get_animation(0).add_animation(level.get_campaign()+Constants_Game.bear_box_engine_path+update_path,"engine_", "default",0.2f, 3, 9, true);
        arena.get_Main_List("b", 0).get_animation(0).add_animation(level.get_campaign()+Constants_Game.bear_box_engine_path+update_path,"engine_", "default",0.4f, 9, 16, true);

	}
	
	public void add_bear_personage(int personage_tag)
	{
		switch (personage_tag) 
		{
		case 2:
		    this.box_bear_init("default/");   	
			break;

		default:
			break;
		}
	}
	
	
	
	
	public Game_Arena get_Arena()
	{
		return arena;
	}
}