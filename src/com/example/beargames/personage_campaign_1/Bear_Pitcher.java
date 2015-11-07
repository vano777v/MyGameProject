package com.example.beargames.personage_campaign_1;

import java.util.ArrayList;

import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;
import com.example.particles.Bomb_Particle_Element;
import com.example.particles.Garlic_Particle_Element;
import com.example.particles.Stone_Particle;

public class Bear_Pitcher extends Main_Personage
{

	public Bear_Pitcher(ccColor4B color, String campaign_path,
			String default_path, Game_Arena _arena, String is_who) {
		super(color, campaign_path, default_path, _arena, is_who);
		this.attack_particle = new ArrayList<Particle_Element>();
		// TODO Auto-generated constructor stub
	}
	
	public Bear_Pitcher(Main_Personage personage)
	{
		super(personage);
	}

	@Override
	public void attack_particle_init(ArrayList<String> ammunition_update_path) 
	{
		attack_particle.add(stone_particle_init(ammunition_update_path.get(0)));
		attack_particle.add(bomb_particle_init(ammunition_update_path.get(1)));
		attack_particle.add(garlic_particle_init(ammunition_update_path.get(2)));
	}
	private Particle_Element garlic_particle_init(String ammunition_path)
	{
		Particle_Element particle = new Garlic_Particle_Element("campaign_1/Particles/garlic/1.png", "b", CGSize.make(45, 45), get_arena(),3);
	    particle.set_speed_fly(160);
	    particle.get_animation_element().add_animation("campaign_1/Particles/garlic/"+ammunition_path, "engine_","fly",0.2f,1, 5, true);
	    particle.get_animation_element().add_animation("campaign_1/Particles/garlic/"+ammunition_path, "engine_", "explosion", 0.2f, 5, 8, false);
		return particle;
	}
    private Particle_Element bomb_particle_init(String ammunition_path)
    {
    	Particle_Element particle = new Bomb_Particle_Element("campaign_1/Particles/bomb/1.png", "b", CGSize.make(50, 50), get_arena(),4);
    	particle.set_speed_fly(110);
    	particle.get_animation_element().add_animation("campaign_1/Particles/bomb/"+ammunition_path, "engine_", "fly", 0.2f, 1, 5, true);
    	particle.get_animation_element().add_animation("campaign_1/Particles/bomb/"+ammunition_path, "engine_", "explosion", 0.2f, 5, 13, false);
    	return particle;
    }
    private Particle_Element stone_particle_init(String ammunition_path)
    {
    	Particle_Element particle = new Stone_Particle("campaign_1/Particles/stone/1.png", "b", CGSize.make(45, 45), get_arena(),2);
    	particle.set_speed_fly(110);
    	particle.get_animation_element().add_animation("campaign_1/Particles/stone/"+ammunition_path, "engine_", "fly", 0.2f, 1, 5, true);
    	return particle;
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
