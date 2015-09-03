package com.example.engine.beargames;

import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Constants_Game;


public class Level 
{
	private Game_Arena arena= null;
	private int campaign= 0;
	private int level_game=0;
	private float general_scale_factor=0;
	private CGSize screen = null;
	private int arena_area_coff =0;
	private int pers_limit=0;
	
   public Level (int campaign, int level, int arena_area, int pers_limit, float general_scale_factor, CGSize screenSize)
   {
	   
	   this.campaign= campaign;
	   this.level_game = level;
	   this.general_scale_factor  = general_scale_factor;
	   this.screen = screenSize;
	   this.arena_area_coff = arena_area;
	   this.pers_limit = pers_limit;
	 
   }
   
   public Game_Arena Game_Arena_init( )
   {
	   arena =  new Game_Arena(ccColor4B.ccc4(255,0, 0,255),general_scale_factor,screen,Constants_Game.pers_dim, Constants_Game.base_dim,6);
       arena.setPosition(0, Constants_Game.main_menu_dim*general_scale_factor);
       arena.set_size_arena(CGSize.make(screen.width*arena_area_coff, screen.height ));
       arena.set_action_arena(this.pers_limit);
       return arena;
   }
   public int get_current_level()
   {
	   return level_game;
   }
   public int get_campaign()
   {
	   return campaign;   
   }
   
  
   
}
