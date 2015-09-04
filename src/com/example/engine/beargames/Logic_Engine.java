package com.example.engine.beargames;

import org.cocos2d.types.CGPoint;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.beargames.Inputs_Outputs.Constants_Game;

public class Logic_Engine extends GameLayer

{
private Game_Arena arena = null;
    
    private float general_scale_factor=0;
    private Level level= null;
    private Constants_Game const_level= null;
    private String campaign=null;
    private String current_level= null;
  
  public Logic_Engine( Level level,  Game_Arena arena,  float general_scale_factor)
  {
	 
	  super("campaign_"+level.get_campaign()+"/", arena);
		this.level = level;
		this.arena = arena;
		this.general_scale_factor = general_scale_factor;
		const_level = this.detect_level(level.get_campaign(), level.get_current_level());
		campaign = "campaign_"+level.get_campaign()+"/";
		current_level = "level_"+level.get_current_level();
		init_paralax();
  }
  public void  init_paralax()
  {
	  int k=0;
	  for(int i =0;i<const_level.paralax_child;i++)
	  {
		  //
	  //String [] par={campaign+current_level+"/paralax/","sback","fire"};
	  //Integer[] al={1,7};
		  if(const_level.paralax_anim[i])
		  {
			  Integer[] buff = new Integer[]{Integer.parseInt(const_level.pralax_conf[k][4]),Integer.parseInt(const_level.pralax_conf[k][5])};
			  arena.add_Paralax_Child(campaign+current_level+const_level.paralax_path[i], CGPoint.make(const_level.parlax_value[i][0], const_level.parlax_value[i][1]*general_scale_factor),CGPoint.make(const_level.parlax_value[i][2], const_level.parlax_value[i][3]), i+1,const_level.pralax_conf[k],Float.parseFloat(const_level.pralax_conf[k][3]),buff,true);
		  	  k++;
		  }
		  else
		  {
			  arena.add_Paralax_Child(campaign+current_level+const_level.paralax_path[i], CGPoint.make(const_level.parlax_value[i][0], const_level.parlax_value[i][1]*general_scale_factor),CGPoint.make(const_level.parlax_value[i][2], const_level.parlax_value[i][3]), i+1,null,0,null,false);
		  }
	  //arena.add_Paralax_Child(campaign+current_level+const_level.paralax_path[1], CGPoint.make(const_level.parlax_value[1][0], const_level.parlax_value[1][1]*general_scale_factor),CGPoint.make(const_level.parlax_value[1][2], const_level.parlax_value[1][3]), 1,null,0,null,false);
	  //arena.add_Paralax_Child(campaign+current_level+const_level.paralax_path[2], CGPoint.make(const_level.parlax_value[2][0], const_level.parlax_value[2][1]*general_scale_factor),CGPoint.make(const_level.parlax_value[2][2], const_level.parlax_value[2][3]), 2,par,0.1f,al,true);
	  
	   }
  }
  
  private Constants_Game detect_level (int value_camp, int value_level)
  {
	  Constants_Game game_const=null;
	  switch (value_camp) {
	case 1:
	{
		switch (value_level) {
		case 1:
			Const_Lev_1_1 lev = new Const_Lev_1_1();
			game_const = lev;
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
		
	
		
		break;
	}

	default:
		break;
	}
	  return game_const;
  }
  
}
