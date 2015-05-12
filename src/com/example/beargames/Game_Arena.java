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

import android.view.MotionEvent;

public class Game_Arena extends CCColorLayer
{
	private CCParallaxNode paralax; 
    private CGSize arena_size=null;
    private float scale_factor=0;
    private CGSize translation=null;
    private CGPoint start_touch=null;
    private ArrayList<CCSprite> paralax_element=null; 
  
	protected Game_Arena(ccColor4B color, float scale_factor, CGSize translation, int tag_arena) 
	{
		super(color);
		// TODO Auto-generated constructor stub
		paralax =  CCParallaxNode.node();
		paralax.setPosition(0,0);
		paralax.setAnchorPoint(0,0);
		arena_size = CGSize.make(0, 0);
		this.scale_factor = scale_factor;
		this.translation = translation;
		this.setAnchorPoint(0, 0);
		paralax_element = new ArrayList<CCSprite>();
		addChild(paralax, 0, 6);
	} 
    public void set_size_arena(CGSize new_size)
    { 
    	this.setContentSize(new_size);
    	this.arena_size.set(new_size);
    	
    }
	
	
    
    public CGSize getSize_arena()
    {
    	CGSize size= null;
    	if(arena_size.width!=0 && arena_size.height!=0)
    		return arena_size;
    	else
    		return size;
    }
	public void add_Paralax_Child(String path, CGPoint offset_coord, CGPoint speed, int tag) 
	{
		CGSize real_size;
		CGSize win_size = CCDirector.sharedDirector().displaySize();
		CCSprite paralax_node = new CCSprite(path);
		real_size = CGSize.make(paralax_node.getContentSize().width, paralax_node.getContentSize().height);
	
		if(paralax_element.isEmpty())
		{
			
			if(getSize_arena()!=null)
			{
				//float x = real_size.width
		      paralax_node.setTextureRect(0, 0,(real_size.width/win_size.width)*getSize_arena().width*(speed.x+1), real_size.height*(speed.y+1), false);
		      paralax_node.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
		      paralax_node.setContentSize((real_size.width/win_size.width)*getSize_arena().width*(speed.x+1), getSize_arena().height*(speed.y+1));
		      paralax_node.setTag(tag);
		      paralax_node.setScale(this.scale_factor);
		      paralax.addChild(paralax_node,tag,speed.x, speed.y,offset_coord.x, offset_coord.y);
		     
		      paralax_node.setAnchorPoint(CGPoint.make(0,0));
		      paralax_element.add(paralax_node);
		    }
	    }else
		{
	    	if(getSize_arena()!=null)
	    	{
	    		
	    	int nr_elem = paralax_element.size();
	    	 float coff = paralax_element.get(nr_elem-1).getContentSize().width/((real_size.width/win_size.width)*getSize_arena().width*(speed.x+1));
	    	  paralax_node.setTextureRect(0, 0,(real_size.width/win_size.width)*getSize_arena().width*(speed.x+1)*coff, real_size.height*(speed.y+1), false);
		      paralax_node.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
		      paralax_node.setContentSize((real_size.width/win_size.width)*getSize_arena().width*(speed.x+1)*coff, getSize_arena().height*(speed.y+1));
		      paralax_node.setScale(this.scale_factor);
		      paralax.addChild(paralax_node,tag,speed.x, speed.y,offset_coord.x, offset_coord.y);
		      paralax_node.setAnchorPoint(CGPoint.make(0,0));
		      paralax_element.add(paralax_node);
		  	//System.out.println("Lea"+paralax.getChildByTag(1).getPosition());
	    	}
		}
	}
	public void set_paralax_scale (float scale_factor)
	{
		int size= paralax_element.size();
		for(int i=0;i<size;i++)
			paralax_element.get(i).setScale(scale_factor);
		

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
		
	
		   float lim=this.getPosition().x+(translation.x)*(-1);
			//System.out.println("Leap leap "+winSize+" "+pp);
		   if(lim<0 && lim*(-1)<this.getContentSize().width)
		     this.setPosition(lim, 250f*scale_factor);
		
	}
	public boolean touch_detect_arena(MenuLayer top_menu, MenuLayer main_menu,CGSize screen , CGPoint touch_point)
	{
		boolean result= false;
		
		CGRect arena = CGRect.make(0, main_menu.scaled_size_height, screen.width, screen.height-top_menu.scaled_size_height-main_menu.scaled_size_height);
		if(arena.contains(touch_point.x, touch_point.y)) result=true;
		return result;
	}
   
}
