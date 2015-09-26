package com.example.engine.beargames;

import java.util.ArrayList;
import java.util.Random;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.beargames.Inputs_Outputs.Constants_Game;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Personage_Element;

public class Logic_Engine extends GameLayer

{
private Game_Arena arena = null;
    
    private float general_scale_factor=0;
    private Level level= null;
    private Random random_engine = null;
    private String campaign=null;
    private String current_level= null;
    private float time_vimp_build=0;
    private float time_count_vimp=0;
    private Main_Personage vimp_pers= null;
    private Boolean vimp_build_is_runing = false;
     private Boolean[] go_flag =null;
  
  public Logic_Engine( Level level,  Game_Arena arena,  float general_scale_factor)
  {
	 
	    super("campaign_"+level.get_campaign()+"/", arena );
		this.level = level;
		this.arena = arena;
		random_engine = new Random();
		this.general_scale_factor = general_scale_factor;
		this.const_level = this.detect_level(level.get_campaign(), level.get_current_level(), arena);
		campaign = "campaign_"+level.get_campaign()+"/";
		current_level = "level_"+level.get_current_level();
		go_flag = new Boolean[]{false,false};
		init_paralax();
		init_bases();
		init_action_base();
		const_level.start_flags_movie();
		const_level.start_vimpire_base_default_movie();
		const_level.start_bear_base_default_movie();
		
		const_level.bear_team_fight.add(const_level.box_bear_init("default/"));
		const_level.bear_team_fight.add(const_level.mace_bear_init("default/"));
		
		const_level.vimp_team_fight.add(const_level.claw_vimp_init("default/"));
		const_level.vimp_team_fight.add(const_level.scythe_vimp_init("default/"));
		const_level.vimp_team_fight.add(const_level.captain_vimp_init("default/"));
		const_level.vimp_team_fight.add(const_level.arbalet_vimp_init("default/"));
		this.schedule("main_control_activity", 0.1f);
		this.schedule("build_vimp_init", 6f);
		this.schedule("clear_memory_pers",4f);
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
	

	
	public void main_control_activity(float dt)
	{
		
	
		refresh_arena();
		
	
		for(int i=0;i<arena.bears_element.size()-1;i++)
		{
			if(intersect_personage_default_bear(arena.get_personage(i, "b"),arena.get_personage(i+1, "b")))
			{
					if(!arena.get_personage(i+1, "b").action_is_runing_now().equalsIgnoreCase("default"))
					{ arena.bears_element.get(i+1).set_state_action(0);
					  Main_Personage.pers_start_walk=false;
					}	
			}
			else
			{
				
			   go_army(i+1, "b");
		    }
		}
		refresh_arena();
		for(int i=0;i<arena.vimpire_element.size()-1;i++)
			if(intersect_personage_default_vimpire(arena.get_personage(i, "v"),arena.get_personage(i+1, "v"))) 
			{
				if(!arena.get_personage(i+1, "v").action_is_runing_now().equalsIgnoreCase("default"))
			          arena.vimpire_element.get(i+1).set_state_action(0);
			}
			else
			{
			   go_army(i+1, "v");	
			}
		
		
		refresh_arena();
		for(int i=0;i<arena.bears_element.size();i++)
			for(int j=0;j<arena.vimpire_element.size();j++)
				if(intersect_personage_attack(arena.bears_element.get(i),arena.vimpire_element.get(j)) && arena.bears_element.get(i).get_enemy_tag()!=j) 
				{
				   arena.bears_element.get(i).set_enemy_tag(j);
				   arena.bears_element.get(i).set_state_action(2);	
				   arena.bears_element.get(i).attack_action(arena.vimpire_element.get(j));
				   System.out.println("IOPTA urs");
				}

		refresh_arena();
		for(int i=0;i<arena.vimpire_element.size();i++)
			for(int j=0;j<arena.bears_element.size();j++)
				if(intersect_personage_attack(arena.bears_element.get(j),arena.vimpire_element.get(i)) &&arena.vimpire_element.get(i).get_enemy_tag()!=j)
				{ 
					arena.vimpire_element.get(i).set_enemy_tag(j);
					arena.vimpire_element.get(i).set_state_action(2);
					arena.vimpire_element.get(i).attack_action(arena.bears_element.get(j));
					System.out.println("IOPTA vamp");
				}
		
		refresh_arena();
	}
	
	private void go_army(int index, String is_who)
	{
		int index_flag=-1;
		if(is_who.equalsIgnoreCase("b"))
			index_flag=0;
		else index_flag=1;
		if(arena.get_personage(index, is_who).action_is_runing_now()!=null)
		  {	
			if(arena.get_personage(index, is_who).action_is_runing_now().equalsIgnoreCase("default"))
			{
				if(go_flag[index_flag]==false)
				{
					
					if(!arena.get_personage(index, is_who).action_is_runing_now().equalsIgnoreCase("walk"))
					{
					  arena.get_personage(index, is_who).set_state_action(1);
				      go_flag[index_flag]=true;
					}
				  
				  }
			}
			else
			{
				//System.out.println("Trebuie "+0+" "+arena.get_personage(0, "b").action_is_runing_now());
				if(arena.get_personage(index, is_who).action_is_runing_now()!=null)
					go_flag[index_flag]=false;
			}
			// mama = arena.get_personage(0, "b").action_is_runing_now();
			   
		 }
	
	}
	public void refresh_arena()
	{
		for(int i=0;i<arena.bears_element.size();i++ )
		{
			command_interface(arena.bears_element.get(i));
		}
		for(int i=0; i<arena.vimpire_element.size();i++)
			command_interface(arena.vimpire_element.get(i));
		
	}
	
	public void command_interface (Main_Personage pers)
	{
		switch (pers.get_state_action()) {
		case 0:
			 {pers.stop_walk("default", 0);
			   
			 break;}
		case 4:
			 {
				pers.death(arena, 4);
				
			 	break;
			 	
			 }
		case 1:
			{pers.start_walk(1);
			  break;
			}
		
		default:
			break;
		}
	}
	
	public void build_vimp_init(float dt)
	{
		if(!vimp_build_is_runing && arena.count_personages("v")<arena.unit_limit_vimpires)
		{
			//System.out.println("OH"+arena.get_pers_limit_count("v"));
			vimp_pers = new Main_Personage(const_level.vimp_team_fight.get(random_engine.nextInt(4)));
			
			this.schedule("build_vimp", 0.1f);
			vimp_build_is_runing=true;
		}
	}
	
	public void build_vimp(float dt)
	{
		if(time_count_vimp<=time_vimp_build)
		{
			time_count_vimp+=0.1f;
			
		}
		else
		{
		    arena.add_personage(vimp_pers);
		    vimp_pers.set_state_action(1);
		    vimp_pers.start_walk(1);
			time_count_vimp=0;
			this.unschedule("build_vimp");
			vimp_build_is_runing= false;
		}
	}
	
	private Boolean intersect_personage_attack(Main_Personage first, Main_Personage second)
	{
	  
		Boolean result  = false;
	 
		if(first!=null&& second!=null)
			
		{
		  if(first.is_live==true && second.is_live==true )
		  {	
			CGPoint first_pooint = CGPoint.make(first.getPosition().x+first.attack_coord.width, first.getPosition().y);
			CGRect  object = CGRect.make(first_pooint, CGSize.make((first.getContentSize().width-first.attack_coord.height-first.attack_coord.width),first.getContentSize().height));
			if((object.contains(second.getPosition().x+second.attack_coord.height, second.getPosition().y))||
				(object.contains(second.getPosition().x+(second.getContentSize().width-second.attack_coord.width), second.getPosition().y))||
				(object.contains((second.getPosition().x+second.attack_coord.height), second.getPosition().y+second.getContentSize().height))||
						(object.contains(second.getPosition().x+(second.getContentSize().width-second.attack_coord.width), second.getPosition().y+second.getContentSize().height))) 
						result=true;
		  }
		}
	 
	 
		return result;
	}
	private Boolean intersect_personage_default_bear(Main_Personage first, Main_Personage second)
	{
	  
		Boolean result  = false;
	 
		if(first!=null&& second!=null)
			
		{
		  if(first.is_live==true && second.is_live==true )
		  {	
			CGPoint first_pooint = CGPoint.make(first.getPosition().x+first.default_coord.width, first.getPosition().y);
			CGRect  object = CGRect.make(first_pooint, CGSize.make((first.getContentSize().width-first.default_coord.height-first.default_coord.width),first.getContentSize().height));
			if((object.contains(second.getPosition().x+second.default_coord.width, second.getPosition().y))||
				(object.contains(second.getPosition().x+(second.getContentSize().width-second.default_coord.height), second.getPosition().y))||
				(object.contains((second.getPosition().x+second.default_coord.width), second.getPosition().y+second.getContentSize().height))||
						(object.contains(second.getPosition().x+(second.getContentSize().width-second.default_coord.height), second.getPosition().y+second.getContentSize().height))) 
						result=true;
		  }
		}
	 
	 
		return result;
	}
	private Boolean intersect_personage_default_vimpire(Main_Personage first, Main_Personage second)
	{
	  
		Boolean result  = false;
	 
		if(first!=null&& second!=null)
			
		{
		  if(first.is_live==true && second.is_live==true )
		  {	
			CGPoint first_pooint = CGPoint.make(first.getPosition().x+first.default_coord.height, first.getPosition().y);
			CGRect  object = CGRect.make(first_pooint, CGSize.make((first.getContentSize().width-first.default_coord.width-first.default_coord.height),first.getContentSize().height));
			if((object.contains(second.getPosition().x+second.default_coord.height, second.getPosition().y))||
				(object.contains(second.getPosition().x+(second.getContentSize().width-second.default_coord.width), second.getPosition().y))||
				(object.contains((second.getPosition().x+second.default_coord.height), second.getPosition().y+second.getContentSize().height))||
						(object.contains(second.getPosition().x+(second.getContentSize().width-second.default_coord.width), second.getPosition().y+second.getContentSize().height))) 
						result=true;
		  }
		}
	 
	 
		return result;
	}
	
	
	
	
	public void clear_memory_pers(float dt)
	{
		arena.remove_personage_buffer();
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
			Const_Lev_1_1 lev = new Const_Lev_1_1(arena, general_scale_factor);
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
