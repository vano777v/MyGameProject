package com.example.particles;

import org.cocos2d.types.CGSize;

import com.example.beargames.Game_Arena;
import com.example.game.arena.elements.Particle_Element;

public class Stone_Particle extends Arrow_Particle_Element
{

	public Stone_Particle(String path, String is_who, CGSize size_request,
			Game_Arena arena, int tag) {
		super(path, is_who, size_request, arena, tag);
		// TODO Auto-generated constructor stub
	}
	
	public Stone_Particle(Particle_Element particle)
	{
		super(particle);
	}
 
}
