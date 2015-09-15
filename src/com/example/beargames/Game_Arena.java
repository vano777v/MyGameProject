package com.example.beargames;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.beargames.Inputs_Outputs.Const_Lev_1_1;
import com.example.engine.beargames.Action_Activity;
import com.example.game.arena.elements.Main_Base;
import com.example.game.arena.elements.Main_Personage;
import com.example.game.arena.elements.Personage_Element;

public class Game_Arena extends CCColorLayer
{
	private CCParallaxNode paralax; 
    private CGSize arena_size=null;
    private  float general_scale_factor=0;
    private  CGSize local_scale_factor=null;
    private CGSize screen_size=null;
    private CGPoint start_touch=null;
    private float start_point_arena =0, action_arena=0;
    private float base_dimm=0, pers_dimm=0;  
    private ArrayList<CCSprite> paralax_element=null; 
    public ArrayList<Main_Personage> bears_element = null;
    public ArrayList<Main_Personage> vimpire_element = null;
    private ArrayList<Main_Base> base_list=null; 
    private int arena_limit_pers_count=0;
    private int unit_limit;
   
    private float limt=0;
    
	public Game_Arena(ccColor4B color,  float general_scale_factor,  CGSize screen_size,float pers_dimm, float base_dimm, int tag_arena) 
	{
		super(color);
		// TODO Auto-generated constructor stub
		paralax =  CCParallaxNode.node();
		paralax.setPosition(0,0);
		paralax.setAnchorPoint(0,0);
		
		arena_size = CGSize.make(0, 0);
		this.general_scale_factor = general_scale_factor;
		//this.local_scale_factor = locala_scale_factor;
		this.screen_size = screen_size;
		this.setAnchorPoint(0, 0);
		this.pers_dimm = pers_dimm;
		this.base_dimm = base_dimm;
		paralax_element = new ArrayList<CCSprite>();
		base_list = new ArrayList<Main_Base>();
		bears_element = new ArrayList<Main_Personage>();
		vimpire_element = new ArrayList<Main_Personage>();
		addChild(paralax, 0, 6);
	} 
    public void set_size_arena(CGSize new_size)
    { 
    	
    	this.setContentSize(new_size);
    	CGSize local_scale_factor = CGSize.make(new_size.width/screen_size.width,new_size.height/screen_size.height );
    	this.local_scale_factor = CGSize.make(local_scale_factor.width,local_scale_factor.height);
    	paralax.setScaleX(1/this.local_scale_factor.width);
    	this.arena_size.set(new_size);
    	this.setScaleX(this.local_scale_factor.width);
    	this.setScaleY(this.local_scale_factor.height);
    }
	
	public void set_action_arena(int number_of_pers)
	{
		float scale_base = base_dimm*general_scale_factor/local_scale_factor.width;
		float scale_pers = pers_dimm*general_scale_factor/local_scale_factor.width;
		start_point_arena = (number_of_pers*scale_pers+2*scale_base)*3 - screen_size.width;
        action_arena=number_of_pers*scale_pers+2*scale_base;  	
        unit_limit = number_of_pers;  
	}
    
	public float get_action_arena ()
	{
		return action_arena;
	}
    public CGSize getSize_arena()
    {
    	CGSize size= null;
    	if(arena_size.width!=0 && arena_size.height!=0)
    		return arena_size;
    	else
    		return size;
    }
    
    
	public void add_Paralax_Child(String path, CGPoint offset_coord, CGPoint speed, int tag, String[] animation_info, float speed_anim, Integer[] anim_config, Boolean animation) 
	{
		CGSize real_size;
		CGSize win_size = CCDirector.sharedDirector().displaySize();
		int nr_paralx_animated=0;
		CCSprite paralax_node = new CCSprite(path);
		real_size = CGSize.make(paralax_node.getContentSize().width, paralax_node.getContentSize().height);
	    paralax_node=null;
		if(paralax_element.isEmpty())
		{
			
			if(getSize_arena()!=null)
			{
				//float x = real_size.width
				if(!animation)
					this.paralax_nod_init(win_size, 1, path, offset_coord, speed, tag, animation_info, speed_anim, anim_config, animation);
				else
				{
					nr_paralx_animated=(int)(this.getSize_arena().width/real_size.width);
					for(int i= 0;i<nr_paralx_animated;i++)
					{
						this.paralax_nod_init(win_size, 1, path, offset_coord, speed, tag, animation_info, speed_anim, anim_config, animation);
						offset_coord.set(offset_coord.x+real_size.width, offset_coord.y);
						
					}
				}
		    }
	    }else
		{
	    	if(getSize_arena()!=null)
	    	{
	    		
	    	int nr_elem = paralax_element.size();
	    	 float coff = paralax_element.get(nr_elem-1).getContentSize().width/((real_size.width/win_size.width)*getSize_arena().width*(speed.x+1));
	    	 if(!animation)
					this.paralax_nod_init(win_size, coff, path, offset_coord, speed, tag, animation_info, speed_anim, anim_config, animation);
				else
				{
					nr_paralx_animated=(int)(this.getSize_arena().width/real_size.width);
					for(int i= 0;i<nr_paralx_animated;i++)
					{
						this.paralax_nod_init(win_size, 1, path, offset_coord, speed, tag, animation_info, speed_anim, anim_config, animation);
						offset_coord.set(offset_coord.x+real_size.width, offset_coord.y);
						
					}
	    	     }
		}
	}
	}
	
	private void  paralax_nod_init(CGSize win_size, float coff,String path, CGPoint offset_coord, CGPoint speed, int tag, String[] animation_info, float speed_anim, Integer[] anim_config, Boolean animation)
	{
		CGSize real_size;
		CCSprite paralax_node = new CCSprite(path);
		real_size = CGSize.make(paralax_node.getContentSize().width, paralax_node.getContentSize().height);
		 paralax_node.setTextureRect(0, 0,(real_size.width/win_size.width)*getSize_arena().width*(speed.x+1)*coff, real_size.height*(speed.y+1), false);
	     if(!animation)
	     {
	    	  paralax_node.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
	    	  
	     }
	     else
	     {
	    	 Action_Activity anim_paralax = new Action_Activity(paralax_node, "p");
	    	  anim_paralax.add_animation(animation_info[0], animation_info[1], animation_info[2], speed_anim, anim_config[0], anim_config[1], true);
	    	  anim_paralax.start_action(0); 
	     }
	      paralax_node.setContentSize((real_size.width/win_size.width)*getSize_arena().width*(speed.x+1)*coff, getSize_arena().height*(speed.y+1));
	      paralax_node.setScale(this.general_scale_factor*2);
	      paralax.addChild(paralax_node,tag,speed.x, speed.y,offset_coord.x, offset_coord.y);
	      paralax_node.setAnchorPoint(CGPoint.make(0,0));
	      paralax_element.add(paralax_node);
	}
	public void set_paralax_scale (float scale_factor)
	{
		int size= paralax_element.size();
		for(int i=0;i<size;i++)
			paralax_element.get(i).setScale(paralax_element.get(i).getScale()*scale_factor);
		
	   this.setScale(this.getScale()*scale_factor);

	}
	
	public void ccTouchesBegan(CGPoint event)
	{
		
		start_touch = event;
		
	}
	public void ccTouchesMoved(CGPoint move_point)
	{
		CGPoint touchLocation, translation;
		touchLocation = move_point;
		translation= CGPoint.ccpSub(start_touch,touchLocation);
		
	
		    limt=this.getPosition().x+(translation.x)*(-1);
			//System.out.println("Leap leap "+winSize+" "+pp);
		   if(limt<0 && limt*(-1)<start_point_arena)
		     {
			   this.setPosition(limt, 250f*general_scale_factor);
			   java.lang.System.out.println("Marc "+limt);
		     }			  // paralax.setPosition(lim, 250f*general_scale_factor);
		
	}
	public boolean touch_detect_arena(MenuLayer top_menu, MenuLayer main_menu,CGSize screen , CGPoint touch_point)
	{
		boolean result= false;
		
		CGRect arena = CGRect.make(0, main_menu.scaled_size_height, screen.width, screen.height-top_menu.scaled_size_height-main_menu.scaled_size_height);
		if(arena.contains(touch_point.x, touch_point.y)) result=true;
		return result;
	}
	
	
	public Main_Base add_base_node(String source_path, String castle_level,CGSize base_size, CGPoint base_location, String is_who)
	{
	     Main_Base base = new Main_Base(ccColor4B.ccc4(0,255, 255,255), source_path,castle_level,local_scale_factor,general_scale_factor, is_who );
	    base.setSize(base_size);
	    base.setBasePosition(base_location);
	    base_list.add(base);
	    addChild(base);
	    return base;
	}
	public Main_Personage add_personage (String source_path, String name_path,CGSize pers_size, CGPoint pers_location, String is_who)
	{
		Main_Personage personage = new Main_Personage(ccColor4B.ccc4(0,255, 0,255), source_path, name_path, this.local_scale_factor, this.general_scale_factor, is_who);
		personage.setSize(pers_size);
		personage.set_Pers_Position(pers_location);
		
		//if(is_who.equalsIgnoreCase("b")) 
			//bears_element.add(personage);
		//else 
			//vimpire_element.add(personage);
		return personage;
	}
	
	public void add_personage (Main_Personage personage)
	{
	 if(arena_limit_pers_count<unit_limit)
		if(personage.is_who().equalsIgnoreCase("b"))
			{
			    bears_element.add(personage);
			    arena_limit_pers_count++;
			}
		else {
			   vimpire_element.add(personage);
			   this.addChild(personage);
			   arena_limit_pers_count++;
			 }
	}
	
	public Main_Base get_Main_Base_list(int index)
	{
		return base_list.get(index);
	}
	public Main_Personage get_Main_List (String is_who, int index)
	{
	   
		if(is_who.equalsIgnoreCase("b"))
			return bears_element.get(index);
		else return vimpire_element.get(index);
	}
	public void paralax_zoom_in(float scale_factor)
	{
		this.setContentSize(this.getContentSize().width*scale_factor,this.getContentSize().height*scale_factor);
		

		for(int i=0;i<paralax_element.size();i++)
		{
			paralax_element.get(i).setScaleX(paralax_element.get(i).getScaleX()*1.2f);
			paralax_element.get(i).setScaleY(paralax_element.get(i).getScaleY()*1.2f);
			paralax_element.get(i).setPosition(paralax_element.get(i).getPosition().x/1.2f, paralax_element.get(i).getPosition().y*1.2f);
		}
		for (int i = 0; i < base_list.size(); i++) 
		{
		    base_list.get(i).zoom_in(scale_factor);	
		}
		float a_seg = this.getPosition().x*(-1); 
		float roi =  a_seg+screen_size.width;
		float b_seg = start_point_arena+screen_size.width - roi-a_seg;
		start_point_arena = (start_point_arena+screen_size.width)*1.2f-screen_size.width;
		float anch_point=0;
		float scaled_roi=0;
		if(b_seg<a_seg) 
			{
				anch_point = b_seg;
				scaled_roi = roi*scale_factor;
				if(scaled_roi> start_point_arena+screen_size.width) 
					this.setPosition(start_point_arena*(-1),this.getPosition().y);
				else  this.setPosition((scaled_roi-screen_size.width)*(-1), this.getPosition().y);		
			}
		else 
		{
			anch_point = a_seg;
			scaled_roi = anch_point*scale_factor;
			this.setPosition(scaled_roi*(-1), this.getPosition().y);
		 }
		
	}
	public void paralax_zoom_out(float scale_factor)
	{
		float anch_point =0, scaled_roi=0, cord_x=0, cord_y=0;
		for(int i=0;i<paralax_element.size();i++)
		{
			cord_x=paralax_element.get(i).getPosition().x;
			cord_y=paralax_element.get(i).getPosition().y;
			paralax_element.get(i).setScaleX(paralax_element.get(i).getScaleX()/1.2f);
			paralax_element.get(i).setScaleY(paralax_element.get(i).getScaleY()/1.2f);
			System.out.println("offset"+paralax_element.get(i).getPosition());
			paralax_element.get(i).setPosition(cord_x/1.2f, cord_y/1.2f);
			System.out.println("offset2"+paralax_element.get(i).getPosition());
		}
		for (int i = 0; i < base_list.size(); i++) 
		{
		    base_list.get(i).zoom_out(scale_factor);	
		}
		float a_seg = this.getPosition().x*(-1); 
		float roi =  a_seg+screen_size.width;
		float b_seg = start_point_arena+screen_size.width - roi-a_seg;
		start_point_arena = (start_point_arena+screen_size.width)/1.2f-screen_size.width;
		if(b_seg<a_seg) 
			{
				anch_point = b_seg;
				scaled_roi = roi/scale_factor;
				if(scaled_roi> start_point_arena+screen_size.width) 
					this.setPosition(start_point_arena*(-1),this.getPosition().y);
				else  this.setPosition((scaled_roi-screen_size.width)*(-1), this.getPosition().y);		
			}
		else 
		{
			anch_point = a_seg;
			scaled_roi = anch_point/scale_factor;
			if(scaled_roi<0) 
				this.setPosition(0,this.getPosition().y);
			else  this.setPosition(scaled_roi*(-1), this.getPosition().y);
		}
			
	}
	
	public remove_personage  (Main_Personage pers)
	{
		if(pers.is_who().equalsIgnoreCase("b"))
		{	
			bears_element.remove(pers);
			
		}
		
			
	}
  public int get_pers_limit_count()
  {
	  return arena_limit_pers_count;
  }

}
