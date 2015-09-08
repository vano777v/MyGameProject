package com.example.engine.beargames;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.beargames.Inputs_Outputs.Constants_Game;
import com.example.game.arena.elements.Main_Base;

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
		init_bases();
		init_action_bear_base();
		init_action_vimpire_base();
		start_bear_base_default_movie();
		start_vimpire_base_default_movie();
		start_flags_movie();
  }
  private void  init_paralax()
  {
	  int k=0;
	  for(int i =0;i<const_level.paralax_child;i++)
	  {
		  
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
	  
	   }
  }
  private void init_bases()
	{       Main_Base base=null;
	        float[] vector = new float[14];
	        float vim_base_pos=0;
	      for(int i=0;i<2;i++)
	      {
	    	  vector = this.dispach_string_vector(const_level.bases_config[i],3, 9);
	    	  if(i<1) vim_base_pos =const_level.bases_pos[i*2];                      
	    	  else vim_base_pos = arena.get_action_arena()-const_level.bases_pos[i*2]*general_scale_factor;
	    	  base=arena.add_base_node(campaign,const_level.bases_config[i][0],CGSize.make(const_level.base_dim, const_level.base_dim), CGPoint.make(vim_base_pos, const_level.bases_pos[i*2+1]), const_level.bases_config[i][1]);
	    	  base.set_base_elemnt(CGSize.make(vector[0], vector[1]),CGPoint.make(vector[2], vector[3]),CGSize.make(const_level.base_dim/Float.parseFloat(const_level.bases_config[i][2]), const_level.base_dim/Float.parseFloat(const_level.bases_config[i][2])) ,CGPoint.make(vector[4],vector[5]),CGSize.make(const_level.base_pers_dim, const_level.base_pers_dim), CGPoint.make(vector[6], vector[7]), CGSize.make(vector[8], vector[9]),CGPoint.make(vector[10], vector[11]));
	         
	    	  //for(int k=0;k<12;k++)
	        	 //System.out.println("Vector["+k+"]="+vector[k]);
	    	  
	      }
         
	    
	} 
  public void init_action_bear_base ()
	{
		    arena.get_Main_Base_list(0).get_animation_element(0).add_animation(campaign+Constants_Game.bbase_animation_path,"bossm_","smoking_first",0.4f, 9, 11, false);
	        arena.get_Main_Base_list(0).get_animation_element(0).add_animation(campaign+Constants_Game.bbase_animation_path,"bossm_","smoking_loop", 0.5f,1, 9, true);
	        arena.get_Main_Base_list(0).get_animation_element(1).add_animation(campaign+"castle/flag/", "flagm_", "falg_movie", 0.1f, 1, 9, true);
	} 
	
	public void init_action_vimpire_base()
	{
		arena.get_Main_Base_list(1).get_animation_element(0).add_animation(campaign+Constants_Game.vbase_animation_path,"bossm_", "first_step", 0.3f, 1,3,false);
		arena.get_Main_Base_list(1).get_animation_element(0).add_animation(campaign+Constants_Game.vbase_animation_path,"bossm_", "first_step", 0.4f, 4,7,true);
		arena.get_Main_Base_list(1).get_animation_element(1).add_animation(campaign+"castle/flag/", "flagm_", "falg_movie", 0.1f, 1, 9, true);	
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
	
  private float[] dispach_string_vector(String[]  vector, int start_index,int last_index)
  {
	  float[] result_vector = new float[(last_index-start_index)*2];
	  String[] result= new String[2];
	  int k=0;
	  for(int i=start_index; i<last_index;i++)
	  {
		  result=vector[i].split(";");
		  result_vector[k]=Float.parseFloat(result[0]);
		///  System.out.println("Result "+result_vector[k]);
		  k++;
		  result_vector[k]=Float.parseFloat(result[1]);
		   //System.out.println("Result "+result_vector[k]); 
		  k++;
		  
	  }
	  
	  
	  return result_vector;
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
