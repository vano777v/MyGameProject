package com.example.engine.beargames;

import java.util.ArrayList;
import java.util.Random;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import android.view.MotionEvent;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.beargames.MenuLayer;
import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.beargames.Inputs_Outputs.Constants_Game;
import com.example.beargames.personage_campaign_1.Bear_Archer;
import com.example.beargames.personage_campaign_1.Bear_Box;
import com.example.beargames.personage_campaign_1.Bear_General;
import com.example.beargames.personage_campaign_1.Bear_Pitcher;
import com.example.beargames.personage_campaign_1.Vimpire_Claw;
import com.example.beargames.personage_campaign_1.Vimpire_General;
import com.example.beargames.personage_campaign_1.Vimpire_arbalet;
import com.example.beargames.personage_campaign_1.Vimpire_scythe;
import com.example.beragames.loadscreans.Main_Load_Screen;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;
import com.example.game.arena.elements.Personage_Element;
import com.example.particles.Arrow_Particle_Element;

public class Logic_Engine extends GameLayer

{
private Game_Arena arena = null;
    
    private float general_scale_factor=0;
    private Level level= null;
    private Random random_engine = null;
    //private String campaign_l=null;
    private String current_level= null;
    private float time_vimp_build=0;
    private float time_count_vimp=0;
    private Main_Personage vimp_pers= null;
    private Boolean vimp_build_is_runing = false;
     private Boolean[] go_flag =null;
     public Main_Load_Screen load=null; 
     
  
  public Logic_Engine( Level level,  Game_Arena arena,  float general_scale_factor, Main_Load_Screen _load)
  {
	 
	    super("campaign_"+level.get_campaign()+"/", arena, _load );
	    load=_load;
		this.level = level;
		this.arena = arena;
		random_engine = new Random();
		this.general_scale_factor = general_scale_factor;
		this.const_level = this.detect_level(level.get_campaign(), level.get_current_level(), arena, load);
		current_level = "level_"+level.get_current_level();
		go_flag = new Boolean[]{false,false};
		init_paralax();
		init_bases();
		init_action_base();
		const_level.start_flags_movie();
		const_level.start_vimpire_base_default_movie();
		const_level.start_bear_base_default_movie();
		load.setPercentageBarLife(load.getPercentageBarLife()+15f);
		const_level.Personages_level_init();
  	    //arena.test.start_move_elipse(100f, CGPoint.make(250f, 250f), 250, CGPoint.make(500, 250), true);
		this.schedule("main_control_activity", 0.1f);
		//this.schedule("build_vimp_init", 6f);
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
				pers.death(4);
				
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
			vimp_pers = build_vimpires( random_engine.nextInt(4));
			
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
  
  private Constants_Game detect_level (int value_camp, int value_level, Game_Arena arena, Main_Load_Screen _load)
  {
	  Constants_Game game_const=null;
	  switch (value_camp) {
	case 1:
	{
		switch (value_level) {
		case 1:
			Const_Lev_1_1 lev = new Const_Lev_1_1(arena, general_scale_factor, _load);
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
@Override
public Main_Personage bear_build(int tag) {
  Main_Personage personage= null;
	switch (tag) {
	case 1:
		personage = new Bear_Box(const_level.bear_team_fight.get(tag-1));
		break;
	case 2: 
		personage = new Bear_General(const_level.bear_team_fight.get(tag-1));
		break;
	case 3: 
		personage = new Bear_Pitcher(const_level.bear_team_fight.get(tag-1));
		break;
	case 4: 
		personage = new Bear_Archer(const_level.bear_team_fight.get(tag-1));
		break;
	default:
		break;
	}
	return personage;
}
  
public Main_Personage build_vimpires(int tag)
{
   	Main_Personage personage = null;
   	switch (tag) {
	case 0:
		personage= new Vimpire_Claw (const_level.vimp_team_fight.get(tag));
		break;
	case 1:
		personage= new Vimpire_scythe (const_level.vimp_team_fight.get(tag));
		break;
	case 2:
		personage= new Vimpire_General (const_level.vimp_team_fight.get(tag));
		break;
	case 3:
		personage= new Vimpire_arbalet (const_level.vimp_team_fight.get(tag));
		break;

	default:
		break;
	}
   	return personage;
}
@Override
public Boolean menu_control(MotionEvent event) 
{
	CGPoint location  = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
	 int menu_tag = -1;
	 
	 java.lang.System.out.println("AIIII "+location);
	 
	 for(int i=0; i<menus_game.size(); i++)
	 {

		if( menus_game.get(i).ccTouchesEnded(location));
		{
		   java.lang.System.out.println("Lungu "+menu_tag);	
	    	 menu_tag = menus_game.get(i).get_touch_menu_state();
	    	 if(menu_tag!=-1) { java.lang.System.out.println("AIIII loc "+menu_tag);
	    		 break;
	    	}
	    	
		  }
			
	    } 
		
	   
	 
    switch (menu_tag) 
    {
	   case 2: 
	   {   
	      
		   //System.out.println("Ibala"+menus_game.get(menu_tag-1).menuitems.size());
		   switch (menus_game.get(menu_tag-1).get_item_touch_tag()) {
	      	case 4: 
	      	{
	      		this.button_bears_choose(menu_tag-1,0,4);
	      		
	      		
	      		break;
	      	}
	      	case 20: 
	      	{
	      		menus_game.get(2).runAction(CCSequence.actions(CCMoveTo.action(1f, CGPoint.make(-350, 101)), CCCallFunc.action(this, "Menu_Move")));
	      		//menus_game.get(2).get_item(1).runAction(CCSequence.actions(CCMoveTo.action()), CCCallFunc.action(menus_game.get(2), "Menu_Move")));
	      		//menus_game.get(2).runAction(CCSequence.action;
	      		//SoundEngine.sharedEngine().playEffect(mMyApp.getApplicationContext(), R.raw.button_press);
	      		statusLabel.setString(Float.toString(12f));
	      		break;
	      	}
	      	
	      	case 3:
	      	{
	      		//this.button_ability();
	      		
	      		break;
	      	}
	      	
	      	case 6:
	      	{
	      		
	      		statusLabel.setString(Integer.toString(buffer_team[0]));
	      		 button_bear_team_item_select(menu_tag-1,0,6, buffer_team[0]/2, 1);
	      		break;
	      	}
	    	case 8:
	      	{
	      		
	      		statusLabel.setString(Integer.toString(buffer_team[1]));
	      		button_bear_team_item_select(menu_tag-1,0,8, buffer_team[1]/2, 2);
	      		break;
	      	}
	    	case 10:
	      	{
	      		
	      		statusLabel.setString(Integer.toString(buffer_team[2]));
	      		button_bear_team_item_select(menu_tag-1,0,10, buffer_team[2]/2, 3);
	      		break;
	      	}
	    	case 12:
	      	{
	      		
	      		statusLabel.setString(Integer.toString(buffer_team[3]));
	      		button_bear_team_item_select(menu_tag-1,0,12, buffer_team[3]/2, 6);
	      		break;
	      	}
	      	
		    default: statusLabel.setString(Float.toString(-1));
			  break;
		} 
		   
	
		 //menus_game.get(0).set_touch_menu_state(-1);
	     break;
	   }   
	   case 3: 
	   {
		 
		   
		   switch (menus_game.get(menu_tag-1).get_item_touch_tag())
		   {
		   case 1:
		   {
			   button_ability_press(2,1 );
			   break;
		   }
		   case 2:
	      	{

	   		statusLabel.setString(Float.toString(buffer_ability_items[1]));
	   		    button_ability_progress_action(1,2);
	      		break;
	      	}
		   case 3:
	      	{
	      		statusLabel.setString(Float.toString(buffer_ability_items[2]));
	      		button_ability_progress_action(2, 1);
	      		break;
	      	}
		   case 5:
	      	{
	      		button_ability_item_press(menus_game.get(menu_tag-1).get_item_touch_tag(),menu_tag-1);
	      		
	      		break;
	      	}
		   case 9:
	      	{
	      		button_ability_item_press(9,2);
	      		
	      		break;
	      	}
		   case 13:
	      	{
	      		button_ability_item_press(13,2);
	      		
	      		break;
	      	}
		   case 17:
	      	{
	      		button_ability_item_press(17,2);
	      		
	      		break;
	      	}
		   
		   		default:statusLabel.setString(Float.toString(-1));
		   			break;
		   	} 
		   
		   menus_game.get(1).set_touch_menu_state(-1);
		   break;
	   } 
	   case 1: 
	   {
		   
		   //statusLabel.setString(Float.toString(2f));
		   int item_tag = menus_game.get(menu_tag-1).get_item_touch_tag();
		   switch (item_tag ) {
	   		case 2: 
	   	   {
	   		statusLabel.setString(Float.toString(31f));
	   		  button_item_bears_press(item_tag, menu_tag-1,1);
	   		   
	   		   break;
	   	   }
	   		case 4: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(22f));
	   			break;
	   		}
	   		
	   		case 6: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(23f));
	   			break;
	   		}
	   		
	   		case 8: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(24f));
	   			break;
	   		}
	   		case 10: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(25f));
	   			break;
	   		}
	   		case 12: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(26f));
	   			break;
	   		}
	   		case 14: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(27f));
	   			break;
	   		}
	   		case 16: 
	   		{
	   		 button_item_bears_press(item_tag, menu_tag-1,1);
	   			statusLabel.setString(Float.toString(28f));
	   			break;
	   		}
	   		
	   		default:statusLabel.setString(Float.toString(-1));
	   			break;
	   	} 
	   
	   //statusLabel.setString(Float.toString(2f));
	   menus_game.get(menu_tag-1).set_touch_menu_state(-1);
	   break;
	   }  
	   case 4: 
	   {
		   switch (menus_game.get(menu_tag-1).get_item_touch_tag()) {
	   		case 1: 
	   	   {  
	   		   
	   		   button_sound_press(menu_tag-1,1);
	   		 
	   		   //statusLabel.setString(Float.toString(31f));
	   		   break;
	   	   }
	   		case 2: 
	   		{
	   			button_restart_press(menu_tag-1, 2);
	   			trans -=20;
	   			//level.setPosition(trans,menus_game.get(1).scaled_size_height);
	   			
	   			statusLabel.setString(Float.toString(22f));
	   			break;
	   		}
	   		case 3: 
	   		{
	   			button_save_press(menu_tag-1, 3);
	   			statusLabel.setString(Float.toString(22f));
	   			break;
	   		}
	   		case 4: 
	   		{
	   			this.button_pause_press(menu_tag-1,4);
	   			
	   			menus_game.get(3).set_touch_menu_state(-1);
	   			break;
	   		}
	   		case 5: 
	   		{
	   			button_stop_press(menu_tag-1,5);
	   			
	   			statusLabel.setString(Float.toString(45f));
	   			break;
	   		}
	
	   		default:statusLabel.setString(Float.toString(-1));
	   			break;
	   	} 
		   menus_game.get(3).set_touch_menu_state(-1);
		   break;
	   }  
	   case 5: 
	   {
		   //System.out.println("Aleoonnaa "+menus_game.get(4).get_item_touch_tag());
		   switch (menus_game.get(4).get_item_touch_tag()) 
		   {
	   		case 4: 
	   	   {
	   		   this.button_zoom_out(menu_tag-1, 4);
	   		   menus_game.get(4).set_touch_menu_state(-1);
	   		   statusLabel.setString(Float.toString(51f));
	   		   break;
	   	   }
	   		case 3: 
	   		{
	   			this.button_zoom_in(menu_tag-1,3);
	   			statusLabel.setString(Float.toString(22f));
	   			menus_game.get(4).set_touch_menu_state(-1);
	   			break;
	   		}
		   
		   
		   }
		   break;
	   }  
	   
	  
	
	default: 
	{arena.touch_detect_arena(menus_game.get(4), menus_game.get(1), screenSize,location );
		statusLabel.setString("0");
		break;
	}
	}	
	return true;
}
@Override
public void ability_menu_init() 
{
	float perc = screenSize.height/1600f;
	 MenuLayer ability_menu =  new MenuLayer(ccColor4B.ccc4(255,255, 255,255),campaign,campaign+"menus/menu3.png",3, CGSize.make(762f, 1227f), perc);
	
	
	 
	
	 ability_menu.setPosition(screenSize.width-ability_menu.scaled_size_width, 0f-(ability_menu.scaled_size_height-menus_game.get(1).scaled_size_height));
	
	 ability_menu.setIsTouchEnabled(true);
	
	 addChild(ability_menu);
	 button_ability_action = new Button_Action() {
		
		@Override
		public void button_action_time_progress() {
			// TODO Auto-generated method stub
			switch (buffer_ability_items[0]) {
			case 9:
				if(menus_game.get(2).get_item(press_id_ability+1).get_isTouchEnabel()&& menus_game.get(2).get_item(unpress_id_ability+1).get_isTouchEnabel() )
				 Bear_Pitcher.ability=0;
				break;

			default:
				break;
			}
			
		}
		
		@Override
		public void button_action_press() 
		{
				switch (buffer_ability_items[0]) {
				case 9:
					Bear_Pitcher.ability=press_id_ability;
					break;

				default:
					break;
				}
		}
	};
	 ablity_items_init(ability_menu,0);
	 menus_game.add(2, ability_menu);
	 
	 button_ability_item_press(last_touch_ability, 2);
   	
}
@Override
public void button_ability_progress_action(int deter_path_id, int unpress_id) {
	 
    	 menus_game.get(2).get_item(1+unpress_id).refrash_progress_bar();	
    	 int path_id=buffer_ability_items[0];
    	 String main_path=campaign+"menus/ability_icons/"+path_id+"ab_i/"+(deter_path_id+1);
    	 press_id_ability =deter_path_id;
    	 unpress_id_ability = unpress_id; 
    	 menus_game.get(2).get_item(1+deter_path_id).time_progress_button_press(main_path+"a.png", main_path+"b.png", main_path+"a.png", 3f);
		
		 
		 System.out.println("button_ability "+press_id_ability);
		 //unpress_id_ability=deter_path_id;
	
}



}
