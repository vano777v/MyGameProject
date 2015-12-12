package com.example.particles;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;

public class Stone_Particle extends Particle_Element
{

	public Stone_Particle(String path, String is_who,CGSize size_request, Game_Arena _arena, int tag) {
		super(path, is_who, size_request, _arena);
		this.setTag(tag);
		// TODO Auto-generated constructor stub
	}
	
	public Stone_Particle(Particle_Element particle)
	{
		super(particle);
	}
	public void detect_colision()
	{
		 Main_Base base = null;
		 Main_Personage personage = null;
		 
		 base=detect_intersect_base();
		 personage=detect_intersect_pers();
		 if(base!=null)
		 {
			 //base.setTotalBaseLife(base.getBaseLife()-10);
			 this.stopAllActions();
		 }
		 if(personage!=null)
		 {
			 this.setVisible(false);
			 if(!this.has_imunity(personage.getTag()));
				 personage.set_bar_life(this.get_demage_enimy(personage.getTag()-1));
			 this.stopAllActions();
			 this.Destructor();
		 }
		 if(detect_out_border())
		 {
			 this.stopAllActions();
			// this.set_Size(128, 128);
			 this.setPosition(this.getPosition().x, 10);
			 this.get_animation_element().start_action(1);
			 this.schedule("start_destruct_particle", 0.1f); 
		 }
	}
   
	
}
