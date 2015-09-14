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

public class Main_Personage  extends CCColorLayer{
	private  float general_scale_factor=0;
	private  CGSize local_scale_factor=null;
	private  Progress_Bar_element bar_life=null;
	private ArrayList<Action_Activity>  animation_element=null; 
	private Personage_Element boss = null;
	private static  String source_path=null;
	private int state_action =0;
	private String is_who=null;
	private int total_pers_life=0;
	private float real_life=0;
	private CGSize attack_area=null;
	private int attack_demage=0;
	private ArrayList<Integer> imunnity_enimy=null;
	private int ability=0;
	private int building_time =0;
	private float walk_speed=0;
	private float attack_speed = 0;
	private Integer[] demage_enemy = null;
	private Main_Personage enemy =null;
	
	
	public Main_Personage(ccColor4B color, final String campaign_path,String default_path,CGSize local_scale_factor, float general_scale_factor, String is_who) 
	{
		super(color);
		this.setAnchorPoint(0, 0);
		this.general_scale_factor=general_scale_factor;
		this.local_scale_factor = CGSize.make(local_scale_factor.width,local_scale_factor.height);
		source_path = campaign_path;
	    animation_element = new ArrayList<Action_Activity>();
		bar_life=  new Progress_Bar_element(source_path+"bar/main_bar_pers.png",source_path+"bar/progress_bar_"+is_who+".png", general_scale_factor );
	    boss = new Personage_Element(source_path+"Personages/"+is_who+"/"+default_path+"/"+"1.png", general_scale_factor, is_who);
	    this.setOpacity(30);
		this.is_who = is_who;
		this.attack_area=CGSize.make(0, 0);
		demage_enemy =  new Integer[8];
		
		addChild(bar_life);
		addChild(boss);
		animation_element.add(new Action_Activity(boss, is_who));
		this.addChild( animation_element.get(0));
	}
	
	public Main_Personage(Main_Personage personage) 
	{
		
		super(personage.getccColor4B());
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
		 
		 this.setScaleX(personage.getScaleX());
		 this.setScaleY(personage.getScaleY());
		 this.setContentSize(personage.getContentSize());
		 this.setPosition(personage.getPosition());
		 
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
	public void set_ability(int ability)
	{
		this.ability= ability;
	}
	public int get_ability ()
	{
		return ability;
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
		start_animation("walk"); 
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
		//this.stop_animation("walk");
	    this.start_animation(animation);
	    this.state_action =-1;}
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
	public String action_is_runing_now()
	{
		return this.animation_element.get(0).who_is_runing();
	} 
	
	public void attack_action(Main_Personage enemy)
	{
	   if(!find_imunity(enemy.getTag()))
	   {
		   
		   this.enemy = enemy;
		   state_action =2;
		   this.start_animation("attack");
		   this.schedule("start_attack_action", attack_speed);
		   this.stopAllActions();
	   }
	  
	}
	
	public void start_attack_action (float dt)
	{
		if(state_action ==2 && enemy.bar_life.getPercentageBarLife()>0){
		    enemy.set_bar_life(this.get_demage_enemy(enemy.getTag()-1));
		    System.out.println("yup"+this.state_action+" "+enemy.getTag()); 
		}
		else 
		{
			   enemy.stop_walk("death", 4);
			    	 
			   //this.sto;
			this.unschedule("start_attack_action");
		 
		  
		   
		}
	}
	
	public void death(Game_Arena arena, int state)
	{
		if(this.state_action == state)
		{
		   this.start_animation("death");
		   this.state_action =-1;
		   if(this.is_who.equalsIgnoreCase("b"))
		      arena.bears_element.remove(this);
		   else
			   arena.vimpire_element.remove(this);
		 }
		
	}
	public void set_bar_life(int demage)
	{
		System.out.println("KIZ"+this.is_who+real_life +" "+demage);
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
}
