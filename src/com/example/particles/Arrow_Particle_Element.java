package com.example.particles;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
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
		 
	}

}
