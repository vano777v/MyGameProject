package com.example.beargames.Campaign_1;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.GameLayer;
import com.example.beargames.Game_Arena;
import com.example.engine.beargames.Logic_Engine_Level_1;
import com.example.game.arena.elements.Main_Base;

public class Level_1_1 
{
	    private float pers_dim = 512;
	    private float base_dimm = 1500;
		private  Game_Arena arena = null;
		private GameLayer level_1 = null;
	    private  Main_Base  bear_base=null,vimpir_base=null;
	public Level_1_1(String campaign,String level,float general_scale_factor, CGSize screenSize)
	{
		    arena =  new Game_Arena(ccColor4B.ccc4(255,0, 0,255),general_scale_factor,3f,screenSize, pers_dim, base_dimm,6);
	        arena.setPosition(0, 250*general_scale_factor);
	        arena.set_size_arena(CGSize.make(screenSize.width*3, screenSize.height ));
	        arena.set_action_arena(15);
	        Logic_Engine_Level_1 logic =  new Logic_Engine_Level_1(arena, general_scale_factor);
	        //arena.add_personage(campaign,"b_box", CGSize.make(614f, 614f), CGPoint.make(bear_base.getPosition().x+bear_base.getContentSize().width, 0), "b");
	       /// arena.get_Main_List("b", 0).set_Pers_element(CGSize.make(118f, 118f), CGPoint.make(248f, 496f), CGSize.make(614f, 614f), CGPoint.make(0, 0));
	        //arena.get_Main_List("b", 0).get_animation(0).add_animation(campaign+"Personages/b/b_box/default/","engine_", "default",1.2f, 1, 3, true);
	        //arena.get_Main_List("b", 0).get_animation(0).add_animation(campaign+"Personages/b/b_box/default/","engine_", "default",0.2f, 3, 9, true);
	        //arena.get_Main_List("b", 0).get_animation(0).add_animation(campaign+"Personages/b/b_box/default/","engine_", "default",0.4f, 9, 16, true);
	        
	       //System.out.println("wer"+arena.get_Main_List("b", 0).getContentSize());
	        //System.out.println("wer2"+bear_base.getPosition());
	        //arena.get_Main_List("b", 0).get_animation(0).start_action(0);
	        
	         
	         level_1 = new GameLayer(campaign, logic);
	
	}
	
	public GameLayer get_Level()
	{
			return level_1;
		
	}
}
