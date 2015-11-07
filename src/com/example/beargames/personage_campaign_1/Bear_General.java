package com.example.beargames.personage_campaign_1;

import java.util.ArrayList;

import org.cocos2d.types.ccColor4B;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;

public class Bear_General extends Main_Personage
{

	public Bear_General(ccColor4B color, String campaign_path,
			String default_path, Game_Arena _arena, String is_who) {
		super(color, campaign_path, default_path, _arena, is_who);
		this.attack_particle =  new ArrayList<Particle_Element>();
		// TODO Auto-generated constructor stub
		
	}
   public Bear_General(Main_Personage personage)
   {
	   super(personage);
   }
@Override
public void attack_particle_init(ArrayList<String> ammunition_update_path) {
	// TODO Auto-generated method stub
	
}


@Override
public void start_attack(Main_Personage enemy) 
{
  this.attack_action(enemy);	
}
@Override
public void start_walk_personage(int state) {
	// TODO Auto-generated method stub
	start_walk(state);
}
@Override
public void stop_walk_personage(int state, String animation) {
	// TODO Auto-generated method stub
	stop_walk(animation, state);
}
@Override
public void deth_personage(int state) {
	// TODO Auto-generated method stub
	death(state);
}

   
   
}
