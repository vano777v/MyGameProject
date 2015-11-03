package com.example.particles;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;

public class Arrow_Particle_Element extends Particle_Element 
{

	public Arrow_Particle_Element(String path, String is_who, CGSize size_request, Game_Arena arena,CGSize local_factor, float general_factor)
	{
		super(path, is_who, size_request, arena,local_factor, general_factor);
		// TODO Auto-generated constructor stub
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
			 this.setVisible(false);
			 this.stopAllActions();
		 }
		 if(personage!=null)
		 {
			 
		 }
		 if(detect_out_border())
		 {
			 System.out.println("Border "+this.getPosition()+" "+arena.get_Main_Base_list(1).getPosition());
			 this.stopAllActions();
		 }
	}

}
