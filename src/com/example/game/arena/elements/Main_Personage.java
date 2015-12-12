package com.example.game.arena.elements;

import java.util.ArrayList;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;

import android.R.menu;
import android.hardware.Camera.Area;

import com.example.beargames.Game_Arena;
import com.example.engine.beargames.Action_Activity;

abstract public class Main_Personage  extends CCColorLayer{
	private  float general_scale_factor=0;
	public  CGSize default_coord= null;
	public  CGSize attack_coord = null;
	private  CGSize local_scale_factor=null;
	private  Progress_Bar_element bar_life=null;
	private ArrayList<Action_Activity>  animation_element=null; 
	private Personage_Element boss = null;
	private static  String source_path=null;
	private int state_action =-1;
	public ArrayList<Particle_Element> attack_particle=null; 
	private String is_who=null;
	private int total_pers_life=0;
	private float real_life=0;
	private Game_Arena arena = null;
	private CGSize attack_area=null;
	private int attack_demage=0;
	private ArrayList<Integer> imunnity_enimy=null;
	///private static int ability=0;
	private int building_time =0;
	private float walk_speed=0;
	private float attack_speed = 0;
	public  static Boolean pers_start_walk=false;
	public boolean is_live= false;
	private int enemy_tag =-1;
	private Integer[] demage_enemy = null;
	private Main_Personage enemy =null;
	abstract public void attack_particle_init(ArrayList<String> ammunition_update_path);
	abstract public void start_attack(Main_Personage enemy);
	abstract public void start_walk_personage(int state);
	abstract public void stop_walk_personage(int state, String animation);
	abstract public void deth_personage(int state);
	//private Main_Personage scan_death=null;
	
	
	public Main_Personage(ccColor4B color, final String campaign_path,String default_path, Game_Arena _arena,String is_who) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		arena =_arena;
		this.general_scale_factor=arena.get_general_scale_factor();
		this.local_scale_factor = CGSize.make(arena.get_local_scale_factor().width,arena.get_local_scale_factor().height);
		source_path = campaign_path;
	    animation_element = new ArrayList<Action_Activity>();
		bar_life=  new Progress_Bar_element(source_path+"bar/main_bar_pers.png",source_path+"bar/progress_bar_"+is_who+".png", general_scale_factor );
	    boss = new Personage_Element(source_path+"Personages/"+is_who+"/"+default_path+"/"+"1.png", general_scale_factor, is_who);
	    this.setOpacity(30);
		this.is_who = is_who;
		
		this.attack_area=CGSize.make(0, 0);
		demage_enemy =  new Integer[8];
		imunnity_enimy = new ArrayList<Integer>();
		addChild(bar_life);
		addChild(boss);
		animation_element.add(new Action_Activity(boss, is_who));
		this.addChild( animation_element.get(0));
	}
	
	public Main_Personage(Main_Personage personage) 
	{
		
		super(personage.getccColor4B());
		this.setContentSize(personage.getContentSize());
		this.setScaleX(personage.getScaleX());
		 this.setScaleY(personage.getScaleY());
		demage_enemy =  new Integer[8];
		this.setAnchorPoint(personage.getAnchorPoint());
		this.general_scale_factor= personage.general_scale_factor;
		this.local_scale_factor=CGSize.make(personage.local_scale_factor.width, personage.local_scale_factor.height);
		this.source_path = new String(personage.source_path);
		this.animation_element = new ArrayList<Action_Activity>();
		 this.bar_life = new Progress_Bar_element(personage.bar_life);
		 this.boss = new Personage_Element(personage.boss);
		 this.setOpacity(personage.getOpacity());
		 this.is_who = new String(personage.is_who);
		 imunnity_enimy = new ArrayList<Integer>();
		 this.attack_particle = new ArrayList<Particle_Element>();
		 for(int i=0;i<personage.attack_particle.size();i++)
			 this.attack_particle.add(personage.attack_particle.get(i));
		 this.arena = personage.arena;
		 
		 this.setPosition(personage.getPosition());
		 this.default_coord = personage.default_coord;
		 this.attack_coord = personage.attack_coord;
		 this.set_main_parameters(personage.getTag(), personage.total_pers_life, personage.demage_enemy, personage.building_time, personage.attack_area, personage.walk_speed);
		 this.attack_speed = personage.attack_speed;
		 addChild(bar_life);
	     addChild(boss);
		this.attack_area = CGSize.make(personage.attack_area.width, personage.attack_area.height);
		Action_Activity action = new Action_Activity(this.boss, personage.get_animation(0));
		animation_element.add(action);
		this.addChild( animation_element.get(0));
	
	}
	
	public void set_demage_enemy(Integer[] demage)
	{
		for(int i=0;i<7;i++)
			this.demage_enemy[i] = demage[i];
	}
	private ccColor4B getccColor4B ()
	{
		ccColor4B result = new ccColor4B(this.getColor().r, this.getColor().g, this.getColor().b, 255);
		return result; 
	}
	public void setSize(CGSize main_size)
	{
		 //this.setContentSize(main_size.width*general_scale_factor*, main_size.height*general_scale_factor);
		 float scalex = main_size.width*general_scale_factor/this.getContentSize().width;
		 float scaley =  main_size.height*general_scale_factor/this.getContentSize().height;
		 this.setContentSize(main_size.width*general_scale_factor/local_scale_factor.width, main_size.height*general_scale_factor/local_scale_factor.height);
		 this.setScaleX(scalex/local_scale_factor.width);
		 this.setScaleY(scaley/local_scale_factor.height);
		//base_element.setScaleX(scalex);
		// base_element.setScaleY(scaley);
		 
		 bar_life.setParentScaleFactors(scalex, scaley);
		 boss.setParentScaleFactors(scalex, scaley);
		 
		
	}
	public void set_Pers_Position (CGPoint location)
	{
		this.setPosition(location.x,location.y);
	}
	
	public void set_coordanate(CGSize default_coord, CGSize attack_coord)
	{
		this.default_coord = CGSize.make(default_coord.width*general_scale_factor/local_scale_factor.width, default_coord.height*general_scale_factor/local_scale_factor.width);
		
		this.attack_coord = CGSize.make(attack_coord.width*general_scale_factor/local_scale_factor.width, attack_coord.height*general_scale_factor/local_scale_factor.width);
		System.out.println("showwww"+general_scale_factor+local_scale_factor);
	}
	
	public void set_Pers_element(CGSize bar_size, CGPoint bar_loc,CGSize pers_size, CGPoint pers_loc  )
	{
		
		 boss.setSize(pers_size);
		 bar_life.setSize(bar_size);
		
		 bar_life.setBarPosition(bar_loc);
		 boss.set_pers_Position(pers_loc);
	
	}
	
	public void zoom_in( float scale_factor)
	{
		this.setScaleX(this.getScaleX()*scale_factor);
		this.setScaleY(this.getScaleY()*scale_factor);
		this.setPosition(this.getPosition().x*scale_factor,this.getPosition().y*scale_factor );
	}
	public void zoom_out(float scale_factor)
	{
		this.setScaleX(this.getScaleX()/scale_factor);
		this.setScaleY(this.getScaleY()/scale_factor);
		this.setPosition(this.getPosition().x/scale_factor,this.getPosition().y/scale_factor );
	}

	public Action_Activity get_animation (int index)
	{
		
		return animation_element.get(index);
	}
	public void set_building_time(int time)
	{
		this.building_time=time;
	}
	public int get_building_time()
	{
		return this.building_time;
	}
	 
	public void set_total_life (int life)
	{
		this.total_pers_life = life;
	}
	public int get_bear_lif()
	{
		return total_pers_life;
	}
	public void set_attack_area (int width, int height)
	{
		this.attack_area.set(width, height);
	}
	public CGSize get_attack_area ()
	{
		return this.attack_area;
	}
	public void set_attack_demage (int value)
	{
		this.attack_demage = value;
	}
	public int get_attack_demage()
	{
		return this.attack_demage;
	}
	public void add_imunity(int enimy_index)
	{
		this.imunnity_enimy.add(enimy_index);
	}
	public ArrayList<Integer> get_imunity_list()
	{
		return imunnity_enimy;
	}
	public void set_real_life(int value)
	{
		this.real_life= value;
		bar_life.setPercentageBarLife(value, this.total_pers_life);
	}
	public float get_real_life()
	{
		return real_life;
	}
	public void set_walk_speed (float speed)
	{
		this.walk_speed=speed;
	}
	public void start_animation(String name)
	{
	   ArrayList<Integer> result = new ArrayList<Integer>();
	   result=animation_element.get(0).find_by_name(name);
	   System.out.println("Pugaci"+result.get(0));
	   for(int i=0;i<result.size();i++)
		   animation_element.get(0).start_action_sequences(result.get(i));
	}
	
	public void stop_animation(String name)
	{
		 ArrayList<Integer> result = new ArrayList<Integer>();
		   result=animation_element.get(0).find_by_name(name);
		   //System.out.println("Pugaci"+result.get(0));
		   for(int i=0;i<result.size();i++)
			   animation_element.get(0).stop_action(result.get(i));
	}
	public void start_walk( int state)
	{
	   if(state==this.state_action)
	   {
		//this.boss.stopAllActions();
		clear_buffer_animation(0);
		this.start_animation("walk");  
		this.state_action = -1;
	    if(this.is_who.equalsIgnoreCase("b"))
		  this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(this.getPosition().x+this.walk_speed, this.getPosition().y)), CCCallFunc.action(this, "Move_Control")));
	    else 
	    	  this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(this.getPosition().x-this.walk_speed, this.getPosition().y)), CCCallFunc.action(this, "Move_Control")));
	    }
	}
	
	public void stop_walk( String animation, int state)
	{
		
		if(state == this.state_action){
		this.stopAllActions();
		this.clear_buffer_animation(0);
		//this.stop_animation("walk");
	    //this.start_animation(animation);
	    //this.state_action =-1;
		   this.start_animation("attack");
		   this.schedule("start_attack_action", attack_speed);
		   stop_walk_personage(2, "attack");
		   this.stopAllActions();
	    
	    }
	}
	
	public void Move_Control()
	{
		if(this.is_who.equalsIgnoreCase("b"))
			  this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(this.getPosition().x+this.walk_speed, this.getPosition().y)), CCCallFunc.action(this, "Move_Control")));
		    else 
		      this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(this.getPosition().x-this.walk_speed, this.getPosition().y)), CCCallFunc.action(this, "Move_Control")));
	}
	public String is_who()
	{
		return is_who;
	}
	
	public int get_demage_enemy (int index)
	{
		return this.demage_enemy[index];
	}
	public void set_demage_enemy(int index, int value)
	{
		this.demage_enemy[index] = value;
	}
	public String action_is_runing_now()
	{
		return this.animation_element.get(0).who_is_runing();
	} 
	
	public void attack_action(Main_Personage enemy)
	{
	   if(!find_imunity(enemy.getTag()))
	   {
		   
		   this.enemy = enemy;
		   this.clear_buffer_animation(0);
		   this.start_animation("attack");
		   this.schedule("start_attack_action", attack_speed);
		   this.stopAllActions();
	   }
	  
	}
	
	public void start_attack_action (float dt)
	{
		if( enemy.bar_life.getPercentageBarLife()>0 &&this.state_action==2){
		    enemy.set_bar_life(this.get_demage_enemy(enemy.getTag()-1));
		    System.out.println("yup"+this.state_action+" "+enemy.getTag()); 
		}
		else 
		{
			this.unschedule("start_attack_action");
			enemy.unschedule("start_attack_action");
		         
			 this.set_state_action(1);
			   enemy.state_action=4; 
			   
			   enemy.bar_life.setVisible(false);
			   
			   this.enemy_tag=-1;
			   //this.sto;
			
		  
		   
		}
	}
	//private Game_Arena arena_=null;
	private Main_Personage winner=null;
	public void death( int state)
	{
		Main_Personage pers=null;
		if(this.state_action == state)
		{
		   this.start_animation("death");
		   this.state_action =-1;
		  
		   if(this.is_who().equalsIgnoreCase("b"))
		   
				{
			      
			   		if(arena.get_personage(1, "b")!=null) 
			   		{
			   			arena.get_personage(1, "b").clear_buffer_animation(0);
			   			arena.get_personage(1, "b").set_state_action(1);
			   		    pers_start_walk =true;
			   		} 
			       
			        this.is_live=false;
			         arena.bears_element.remove(this);
			         
			      
			       
				}
			else 
				{
				
				 
				
				  if(arena.get_personage(1, "v")!=null)
				   {
					  arena.get_personage(1, "v").clear_buffer_animation(0);
					  arena.get_personage(1, "v").set_state_action(1);
			          pers_start_walk =true;
				   }
				 
				  this.is_live=false;
				  arena.vimpire_element.remove(this);
				  
				  
				}
		   
		   this.schedule("scan_death", 0.01f);
		 //  arena.add_death_personage_buffer(this);
		   arena = arena;
		}
		
	}
	
	public void clear_buffer_animation(int index_animation)
	{
		this.animation_element.get(index_animation).clear_activity_buffer();
	}
	
	public void scan_death(float dt)
	{
		int action_runing= animation_element.get(0).get_who_is_runing();
		if(this.animation_element.get(0).action_animation.get(action_runing).isDone())
		{
			this.unschedule("scan_death");
			
			
			if(this.is_who.equalsIgnoreCase("b"))
			{
				this.runAction(CCSequence.actions(CCMoveTo.action(0.3f, CGPoint.make(this.getPosition().x, this.getPosition().y+35)), CCCallFunc.action(this, "bear_death_move")));
			}
			else
			{
				this.boss.setVisible(false);
				arena.add_death_personage_buffer(this);
				System.out.println("o iesit vampir");
			}
		}
		System.out.println("o intrat");
	}
	
	public void bear_death_move()
	{
		if((this.getPosition().y+this.getContentSize().height)<=1600*general_scale_factor) 
		   this.runAction(CCSequence.actions(CCMoveTo.action(0.3f, CGPoint.make(this.getPosition().x, this.getPosition().y+35)), CCCallFunc.action(this, "bear_death_move")));
		else 
		{
			this.stopAllActions();
			this.boss.setVisible(false);
			arena.add_death_personage_buffer(this);
			System.out.println("o iesit");
		}
	}
	
	public void set_bar_life(int demage)
	{
		
		this.real_life -= demage;
		
		
		bar_life.setPercentageBarLife(real_life, this.total_pers_life);
	}
	
	public Boolean find_imunity(int index)
	{
		Boolean result = false;
		
		if(this.imunnity_enimy!=null)
		{
			for(int i=0;i<imunnity_enimy.size();i++)
				if(imunnity_enimy.get(i)==index)
					result = true;
		}
		return result;
	}
	
	public void set_main_parameters(int tag, int total_life, Integer[] demage, int building_time, CGSize attack_area, float walk_speed)
	{
		this.setTag(tag);
		this.total_pers_life = total_life;
		this.real_life = total_life;
		this.set_demage_enemy(demage);
		this.building_time = building_time;
		this.walk_speed = walk_speed;
		this.attack_area = CGSize.make(attack_area.width,attack_area.height) ;
	}
	public void set_attack_speed (float speed)
	{
		this.attack_speed = speed;
	}
	public float get_attack_speed ()
	{
		return this.attack_speed;
	}
	public void set_state_action (int i)
	{
		state_action =i;
	}
	public int get_state_action ()
	{
		return state_action;
	}
	public int get_enemy_tag()
	{
		return enemy_tag;
	}
	public void set_enemy_tag(int tag)
	{
		enemy_tag=tag;
	}
	

	public void destroy()
	{
	   
	   Action_Activity  activity=null;
		//ability=0;
	   attack_area = null;
	   is_live=false;
	   attack_coord = null;
	   default_coord =null;
	   attack_demage=0;
	   attack_speed=0;
	   building_time=0;
	   clean_demage_enemy();
	   enemy_tag=0;
	   general_scale_factor=0;
	  imunnity_enimy.clear();
	  imunnity_enimy=null;
	   is_who=null;
	  local_scale_factor=null;
	   real_life=0;
	   state_action=0;
	   total_pers_life=0;
	   walk_speed=0;
	   for(int i=animation_element.size()-1;i>=0;i--)
		   {
		       animation_element.get(i).Destroy();
		       this.removeChild(animation_element.get(i), true);
		       activity = animation_element.get(i);
		       animation_element.remove(i);
		       activity = null;
		   }
	    for(int i=0;i<this.attack_particle.size();i++)
	    	this.attack_particle.get(i).Destructor();
	    this.attack_particle.clear();
	    this.attack_particle=null;
	    animation_element=null;
		bar_life.destroy();
		this.removeChild(bar_life, true);
		bar_life=null;
		boss.destroy();
		this.removeChild(boss, true);
		boss=null;
		
		System.out.println("Destroy personage");
	}
	public Game_Arena get_arena()
	{
		return arena;
	}
	private void clean_demage_enemy()
	{
		for(int i=0;i<7;i++)
			demage_enemy[i]=0;
		demage_enemy=null;
	}
	

}
