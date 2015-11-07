package com.example.beargames.personage_campaign_1;

import java.util.ArrayList;

import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;
import com.example.particles.Arrow_Particle_Element;

public class Bear_Archer extends Main_Personage 
{

	
	public Bear_Archer(ccColor4B color, String campaign_path,String default_path, Game_Arena arena,String is_who) 
	{
		super(color, campaign_path, default_path,arena,is_who);
		this.attack_particle = new ArrayList<Particle_Element>();
		// TODO Auto-generated constructor stub
	}
	public Bear_Archer(Main_Personage personage)
	{
		super(personage);
	}
	
	public  void attack_particle_init(ArrayList<String>ammunition_update_path)
	{
	    Particle_Element element=null;	
    	element=arrow_amunition_init(ammunition_update_path.get(0));
    	attack_particle.add(element); 
	}
	private Particle_Element arrow_amunition_init(String update_path)
	{
		Particle_Element arrow_particle = new Arrow_Particle_Element("campaign_1/Particles/arrows/1.png", "b", CGSize.make(200f, 200f),get_arena(), 1);
    	arrow_particle.set_speed_fly(144f);
    	arrow_particle.get_animation_element().add_animation("campaign_1/Particles/arrows/engine/"+update_path, "engine_","fly",1.4f, 1,5 , true);
    	return arrow_particle;
	}
	@Override
	public void start_attack(Main_Personage enemy) {
		// TODO Auto-generated method stub
		attack_action(enemy);
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
