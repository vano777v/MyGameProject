package com.example.particles;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;

public class Garlic_Particle_Element extends Particle_Element
{

	public Garlic_Particle_Element(String path, String is_who,CGSize size_request, Game_Arena _arena, int tag) {
		super(path, is_who, size_request, _arena);
		this.setTag(tag);
		// TODO Auto-generated constructor stub
	}
	
	public Garlic_Particle_Element(Particle_Element particle)
	{
		super(particle);
	}

	@Override
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
			 int demage_enimy = personage.get_demage_enemy(personage.getTag()-1);
			 this.setVisible(false);
			 if(!this.has_imunity(personage.getTag()))
				 personage.set_demage_enemy(personage.getTag()-1, demage_enimy-this.get_demage_enimy(personage.getTag()-1));
			 this.stopAllActions();
			 this.Destructor();
		 }
		 if(detect_out_border())
		 {
            //this.setVisible(false);
			 this.stopAllActions();
			 this.set_Size(1024, 1024);
			 this.setPosition(this.getPosition().x, 0);
			 this.get_animation_element().start_action(2);
			 //this.Destructor();
		 }  	
		
	}
	

}
