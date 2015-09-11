package com.example.engine.beargames;

import java.util.ArrayList;

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
  
    private String campaign=null;
    private String current_level= null;
  
  public Logic_Engine( Level level,  Game_Arena arena,  float general_scale_factor)
  {
	 
	    super("campaign_"+level.get_campaign()+"/", arena );
		this.level = level;
		this.arena = arena;
		this.general_scale_factor = general_scale_factor;
		this.const_level = this.detect_level(level.get_campaign(), level.get_current_level(), arena);
		campaign = "campaign_"+level.get_campaign()+"/";
		current_level = "level_"+level.get_current_level();
		init_paralax();
		init_bases();
		init_action_base();
		const_level.start_flags_movie();
		const_level.start_vimpire_base_default_movie();
		const_level.start_bear_base_default_movie();
		
		//const_level.box_bear_init("default/");
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
  public void init_action_base()
	{
		int p=0; 
	    for(int i=0;i<2;i++)
		    {
		    	for(int j=0;j<const_level.base_act_elem;j++)
		    	{
		    		for(int k=0;k<const_level.action_base_config[i][j];k++)
		    		{
		    			arena.get_Main_Base_list(i).get_animation_element(j).add_animation(campaign+const_level.action_base_list[p][0],const_level.action_base_list[p][1], const_level.action_base_list[p][2], Float.parseFloat(const_level.action_base_list[p][3]),Integer.parseInt(const_level.action_base_list[p][4]),Integer.parseInt(const_level.action_base_list[p][5]),Boolean.parseBoolean(const_level.action_base_list[p][6]));
		    			
		    		    p++;
		    		}
		    	}
		    }
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
  
  private Constants_Game detect_level (int value_camp, int value_level, Game_Arena arena)
  {
	  Constants_Game game_const=null;
	  switch (value_camp) {
	case 1:
	{
		switch (value_level) {
		case 1:
			Const_Lev_1_1 lev = new Const_Lev_1_1(arena);
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
