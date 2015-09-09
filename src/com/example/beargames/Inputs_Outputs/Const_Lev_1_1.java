package com.example.beargames.Inputs_Outputs;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Personage;

public class Const_Lev_1_1 extends Constants_Game 
{
   public static int unit_limit          =         15;
   
   private Game_Arena arena=null;
   public Const_Lev_1_1(Game_Arena arena)
   {
	   this.paralax_child=5;
	   this.arena = arena;
	   this.parlax_value= new Float[][]{{0f,250f, 0f,0f},{0f, 0f, 0.2f, 0f},{0f, 70f, 0.3f, 0f},{0f, 60f, 0.1f, 0f},{0f, 60f, 0.2f, 0f}};
       this.paralax_path = new String[] {"/paralax/noise.png","/paralax/tback.png", "/paralax/sback.png", "/paralax/mback.png","/paralax/fback.png"};
       this.paralax_anim = new Boolean[]{false, false, true, false, true};
       this.pralax_conf = new String[][]
    		   {{"campaign_1/level_1/paralax/","sback", "sb_fire","0.1","1","7"}, 
    		    {"campaign_1/level_1/paralax/","fback","fb_fire","0.1","1","7"}
    		   };
       this.bases_pos = new Float[]{16.66f,0f,(float)Constants_Game.base_dim/this.arena_area_coff,0f};
       this.bases_config = new String[][]
    		   {
    		      {"level_1_3", "b","1.2","118;948","0;0","150;0","930;570","600;600","400;610"},
    		      {"level_1_3", "v", "1.2","118;948", "1260;0", "0;0","-15;420","600;600","350;820"}
    		   
    		   };
       this.base_act_elem = 2;
      this.action_base_config = new Integer[][]
    		  { {2,1},
    		    {2,1} 
    		  };
      this.action_base_list= new String[][]
    		  {
    		     {"castle/personage/animation/b/","bossm_", "smoking_first","0.4", "9", "11", "false"},
    		     {"castle/personage/animation/b/","bossm_", "smoking_loop", "0.5","1", "9", "true"},
    		     {"castle/flag/", "flagm_", "falg_movie", "0.1", "1", "9", "true"},
    		     {"castle/personage/animation/v/","bossm_", "first_step", "0.3", "1","3","false"},
    		     {"castle/personage/animation/v/","bossm_", "first_step", "0.4", "4","7","true"},
    		     {"castle/flag/", "flagm_", "falg_movie", "0.1", "1", "9", "true"}
    		  };
      
      
      
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
	public Main_Personage box_bear_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","b_box", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_bear_box_engine(update_path, pers);
		//pers.start_animation("attack");
		pers.set_building_time(1);
		pers.set_walk_speed(12);
		return pers;
	}
	public void init_animation_bear_box_engine(String update_path, Main_Personage pers)
	{
	    pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "walk",0.2f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "attack",0.2f, 9, 16, true);

	}
	
      
}
