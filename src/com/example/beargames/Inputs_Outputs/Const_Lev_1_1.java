package com.example.beargames.Inputs_Outputs;

import java.util.ArrayList;
import java.util.Random;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.beargames.Campaign_1.Level_1_1;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;
import com.example.particles.Arrow_Particle_Element;

public class Const_Lev_1_1 extends Constants_Game 
{
   
   private static float general_scale_factor =0;
   
   private Game_Arena arena=null;
  
   public Const_Lev_1_1(Game_Arena arena, float general_scale_factor)
   {
	   
	   this.unit_limit          =      Level_1_1.units;
	   this.paralax_child=5;
	   this.arena = arena;
	   
	   Const_Lev_1_1.general_scale_factor = general_scale_factor;
	   Constants_Game.parlax_value= new Float[][]{{0f,250f, 0f,0f},{0f, 0f, 0.2f, 0f},{0f, 70f, 0.3f, 0f},{0f, 60f, 0.1f, 0f},{0f, 60f, 0.2f, 0f}};
       this.paralax_path = new String[] {"/paralax/noise.png","/paralax/tback.png", "/paralax/sback.png", "/paralax/mback.png","/paralax/fback.png"};
       this.paralax_anim = new Boolean[]{false, false, true, false, true};
       this.pralax_conf = new String[][]
    		   {{"campaign_1/level_1/paralax/","sback", "sb_fire","0.1","1","7"}, 
    		    {"campaign_1/level_1/paralax/","fback","fb_fire","0.1","1","7"}
    		   };
       this.bases_pos = new Float[]{16.66f,0f,(float)Constants_Game.base_dim/Constants_Game.arena_area_coff,0f};
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
      
      
       bear_team_fight = new ArrayList<Main_Personage>();
       vimp_team_fight = new ArrayList<Main_Personage>();
       
	  
   }
   public void start_flags_movie()
	{
		 arena.get_Main_Base_list(0).get_animation_element(1).start_action(0);
		 arena.get_Main_Base_list(1).get_animation_element(1).start_action(0);
	}
	public void start_bear_base_default_movie()
	{
		arena.get_Main_Base_list(0).get_animation_element(0).start_action_sequences(0);
		arena.get_Main_Base_list(0).get_animation_element(0).start_action_sequences(1);
	}
	public void start_vimpire_base_default_movie()
	{
		arena.get_Main_Base_list(1).get_animation_element(0).start_action_sequences(0);
		arena.get_Main_Base_list(1).get_animation_element(0).start_action_sequences(1);
		
	}
	
	// ==============================Initializare urshi=============================================
	public Main_Personage box_bear_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","b_box", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_bear_box_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(107, 168), CGSize.make(107, 168));
		Integer[] enemy_demage = new Integer[]{26, 10, 25,45, 30, 0,0,20};
		pers.set_main_parameters(1, 1500, enemy_demage, 1, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.4f);
		return pers;
	}
	
	public Main_Personage mace_bear_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","b_mace", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_bear_mace_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(98, 158), CGSize.make(78, 158));
		Integer[] enemy_demage = new Integer[]{30, 35, 20,60, 30, 0,0,20};
		pers.set_main_parameters(2, 1000, enemy_demage, 2, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.6f);
		return pers;
	}
	
	public Main_Personage pitcher_bear_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","b_pitcher", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_bear_pitcher_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(98, 158), CGSize.make(78, 158));
		Integer[] enemy_demage = new Integer[]{30, 35, 20,60, 30, 0,0,20};
		pers.set_main_parameters(3, 1000, enemy_demage, 2, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.6f);
		return pers;
	}
	public Main_Personage archer_bear_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","b_archer", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(0).getPosition().x+arena.get_Main_Base_list(0).getContentSize().width, 0), "b");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_bear_archer_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(98, 158), CGSize.make(78, 158));
		Integer[] enemy_demage = new Integer[]{30, 35, 20,60, 30, 0,0,20};
		pers.set_main_parameters(4, 1000, enemy_demage,1, CGSize.make(0, 0),14);
		pers.set_attack_speed(1.8f);
		return pers;
	}
	public void init_animation_bear_box_engine(String update_path, Main_Personage pers)
	{
	    pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "walk",0.2f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "attack",0.2f, 9, 16, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_box_engine_path+update_path,"engine_", "death",0.1f, 16, 30, false);
	}
	
	public void init_animation_bear_mace_engine(String update_path, Main_Personage pers)
	{
	    pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_mace_engine_path+update_path,"engine_", "default",1.2f, 10, 12, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_mace_engine_path+update_path,"engine_", "walk",0.23f, 4, 10, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_mace_engine_path+update_path,"engine_", "attack",0.2f, 1, 4, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_mace_engine_path+update_path,"engine_", "death",0.2f, 12, 26, false);

	}
	
	public void init_animation_bear_pitcher_engine(String update_path, Main_Personage pers)
	{
	    pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_pitcher_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_pitcher_engine_path+update_path,"engine_", "walk",0.23f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_pitcher_engine_path+update_path,"engine_", "attack",0.2f, 9, 12, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_pitcher_engine_path+update_path,"engine_", "death",0.2f, 12, 26, false);

	}
	
	public void init_animation_bear_archer_engine(String update_path, Main_Personage pers)
	{
	    pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_archer_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_archer_engine_path+update_path,"engine_", "walk",0.23f, 11, 17, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_archer_engine_path+update_path,"engine_", "attack",0.16f, 3, 11, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.bear_archer_engine_path+update_path,"engine_", "death",0.2f, 17, 31, false);
	}
	
	
	//============================Initializare vimpiri==========================================
	
	public void init_animation_vimp_claw_engine(String update_path, Main_Personage pers)
	{
		pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_claw_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_claw_engine_path+update_path,"engine_", "walk",0.23f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_claw_engine_path+update_path,"engine_", "attack",0.2f, 9, 13, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_claw_engine_path+update_path,"engine_", "death",0.14f, 13, 22, false);
	}
	
	public void init_animation_vimp_scythe_engine(String update_path, Main_Personage pers)
	{
		pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_scythe_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_scythe_engine_path+update_path,"engine_", "walk",0.23f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_scythe_engine_path+update_path,"engine_", "attack",0.2f, 9, 13, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_scythe_engine_path+update_path,"engine_", "death",0.14f, 13, 23, false);
	}
	public void init_animation_vimp_captain_engine(String update_path, Main_Personage pers)
	{
		pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_cap_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_cap_engine_path+update_path,"engine_", "walk",0.23f, 3, 10, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_cap_engine_path+update_path,"engine_", "attack",0.2f, 10, 13, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_cap_engine_path+update_path,"engine_", "death",0.14f, 13, 23, false);
	}
	public void init_animation_vimp_arbalet_engine(String update_path, Main_Personage pers)
	{
		pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_arbalet_engine_path+update_path,"engine_", "default",1.2f, 1, 3, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_arbalet_engine_path+update_path,"engine_", "walk",0.23f, 3, 9, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_arbalet_engine_path+update_path,"engine_", "attack",0.2f, 9, 12, true);
        pers.get_animation(0).add_animation("campaign_1/"+Constants_Game.vimp_arbalet_engine_path+update_path,"engine_", "death",0.15f, 12, 21, false);
	}
	
	public Main_Personage claw_vimp_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","v_claw", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(1).getPosition().x-Constants_Game.pers_dim*general_scale_factor/Constants_Game.arena_area_coff, 0), "v");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_vimp_claw_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(81f, 81f),CGSize.make(81f,220f));
		Integer[] enemy_demage = new Integer[]{25, 10, 50,50, 50, 0,0,20};
		pers.set_main_parameters(1, 500, enemy_demage, 1, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.4f);
		return pers;
		
	}	
	public Main_Personage scythe_vimp_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","v_scythe", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(1).getPosition().x-Constants_Game.pers_dim*general_scale_factor/Constants_Game.arena_area_coff, 0), "v");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_vimp_scythe_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(61f, 53f),CGSize.make(61f,172f));
		Integer[] enemy_demage = new Integer[]{40,35, 50,50, 50, 0,0,20};
		pers.set_main_parameters(2, 700, enemy_demage, 2, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.8f);
		return pers;
		
	}
	
	
	public Main_Personage captain_vimp_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","v_cap", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(1).getPosition().x-Constants_Game.pers_dim*general_scale_factor/Constants_Game.arena_area_coff, 0), "v");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_vimp_captain_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(95f, 58f),CGSize.make(58, 0));
		Integer[] enemy_demage = new Integer[]{50, 10, 50,50, 50, 0,0,20};
		pers.set_main_parameters(3, 1000, enemy_demage, 2, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.6f);
		return pers;
		
	}
	
	public Main_Personage arbalet_vimp_init( String update_path)
	{
		Main_Personage pers=null; 
		pers= arena.add_personage("campaign_1/","v_arbalet", CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(arena.get_Main_Base_list(1).getPosition().x-Constants_Game.pers_dim*general_scale_factor/Constants_Game.arena_area_coff, 0), "v");
		//System.out.println("ERT"+arena.bears_element.size());
		pers.set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(Constants_Game.pers_dim, Constants_Game.pers_dim), CGPoint.make(0, 0));
		init_animation_vimp_arbalet_engine(update_path, pers);
		pers.set_coordanate(CGSize.make(141f, 141f),CGSize.make(141, 168));
		Integer[] enemy_demage = new Integer[]{30, 15, 0,0, 0, 0,0,0};
		pers.set_main_parameters(4, 400, enemy_demage, 1, CGSize.make(0, 0),14);
		pers.set_attack_speed(0.4f);
		return pers;
		
	}
	
	public Main_Personage get_bear(int index)
	{
		Main_Personage bear = bear_team_fight.get(index);
		return bear;
	}
    public void ammunition_init()
    {
    	arrow_particle_init();
    }	
    
    private void arrow_particle_init()
    {
    	Particle_Element arrow_particle = new Arrow_Particle_Element("campaign_1/Particles/arrows/1.png", "b", CGSize.make(200f, 200f), arena, 1);
    	arrow_particle.set_speed_fly(144f);
    	arena.add_level_ammunition(arrow_particle);
    }
	
	public int get_unit_limit()
	{
		return unit_limit;
	}
	
      
}
