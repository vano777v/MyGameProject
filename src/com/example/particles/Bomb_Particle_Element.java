package com.example.particles;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Particle_Element;

public class Bomb_Particle_Element extends Particle_Element
{

	public Bomb_Particle_Element(String path, String is_who,CGSize size_request, Game_Arena _arena, int tag) {
		super(path, is_who, size_request, _arena);
		this.setTag(tag);
		// TODO Auto-generated constructor stub
	}
	public Bomb_Particle_Element(Particle_Element particle)
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
			 if(!this.has_imunity(personage.getTag()))
			 {
			     this.stopAllActions();
			     this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(this.getPosition().x, 0)), CCCallFunc.action(this, "finish_attack")));
			 }
			 
		 }
		 if(detect_out_border())
		 {
			 this.stopAllActions();
			 finish_attack();
			 this.schedule("start_destruct_particle", 0.1f); 
		 }  	
		
	}
	
	private void finish_attack()
	{
	   this.set_Size(1024, 1024);
	   this.setPosition(this.getPosition().x, 10f);
	   this.get_animation_element().start_action(1);
	}

}
