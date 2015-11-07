package com.example.beargames.personage_campaign_1;

import java.util.ArrayList;

import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;
import com.example.particles.Arrow_Particle_Element;

public class Vimpire_arbalet extends Main_Personage
{

	public Vimpire_arbalet(ccColor4B color, String campaign_path,String default_path, Game_Arena _arena, String is_who) {
		super(color, campaign_path, default_path, _arena, is_who);
		this.attack_particle =  new ArrayList<Particle_Element>();
		// TODO Auto-generated constructor stub
	}
    public Vimpire_arbalet(Main_Personage personage)
    {
    	super(personage);
    }
	@Override
	public void attack_particle_init(ArrayList<String> ammunition_update_path) 
	{
		Particle_Element particle=null;
		particle=particle_arbalet_init(ammunition_update_path.get(0));
		attack_particle.add(particle);
	}
    
	private Particle_Element particle_arbalet_init(String update_path)
	{
		Particle_Element arrow_particle = new Arrow_Particle_Element("campaign_1/Particles/arbalet/1.png", "v", CGSize.make(200f, 200f),get_arena(), 1);
    	arrow_particle.set_speed_fly(144f);
    	return arrow_particle;
	}
	@Override
	public void start_attack(Main_Personage enemy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void start_walk_personage(int state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop_walk_personage(int state, String animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deth_personage(int state) {
		// TODO Auto-generated method stub
		
	}
}
