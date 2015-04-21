package com.example.beargames;

import java.util.ArrayList;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;

import android.R.bool;
import android.gesture.GesturePoint;
import android.view.MotionEvent;

public class MenuLayer extends CCColorLayer{

	private CCSprite menu;
    protected float scaled_size_width=0;
    protected float scaled_size_height=0;
    private int item_touch_tag=-1;
    private int touch_state = -1;
    private float general_scale_factor= 0;
    static private CGPoint translation = null;
    private  ArrayList<MenuItemLayer> menuitems = null;
	protected MenuLayer(ccColor4B color, String image_path, int tag, CGSize menu_size, float general_scale_factor) 
	{
		super(color);
		
		this.setTag(tag);
		this.setAnchorPoint(0f, 0f);
		CGSize real_size = CGSize.make(menu_size.width,menu_size.height);
		menu_size.set(menu_size.width*general_scale_factor, menu_size.height*general_scale_factor);
		
		this.setContentSize(menu_size);
		float trans_x = this.getContentSize().width/real_size.width; 
		float trans_y = this.getContentSize().height/real_size.height;
		translation = CGPoint.make(trans_x, trans_y);
		this.general_scale_factor= general_scale_factor;
	
		CGSize scale_menu= null;
		
		if(image_path!= null)
		{
		  menu = CCSprite.sprite(image_path);
		  scale_menu =calc_image_size(menu,menu_size);
		  menu.setScaleX(scale_menu.width);
		  menu.setScaleY(scale_menu.height);
		  menu.setContentSize(this.getContentSize());
		  this.addChild(menu);
		  menu.setAnchorPoint(0f, 0f);
			menu.setPosition(0f, 0f);
		}
		
		scaled_size_height = this.getContentSize().height;
		scaled_size_width = this.getContentSize().width;
		
		
		menuitems = new ArrayList<MenuItemLayer>();
	    
		// TODO Auto-generated constructor stub
	}
	
    /* public void set_size( CGSize new_size, CGSize general_scale_factor)
     {
       
    	 
    	 this.setContentSize(new_size);
    	 CGSize menu_size = null;
 		 menu_size=calc_image_size(menu, new_size, general_scale_factor);
 		 menu.setScaleX(menu_size.width);
 		 menu.setScaleY(menu_size.height);
 		 for(int i=0;i<menuitems.size();i++){
 			 this.setItemSize(menuitems.get(i).getSize( general_scale_factor),  general_scale_factor, i+1);
 			 this.setItemPosition(this.getItemPosition(i+1), i+1);
 		 }
 		 this.scaled_size_width = this.getContentSize().width*general_scale_factor.width;
 		 this.scaled_size_height = this.getContentSize().height*general_scale_factor.height;
 	
     }*/
     
     public void setItemSize(CGSize new_size,  float general_scale_factor, int tag)
     {
    	 CGSize item_image_size = null;
    	 menuitems.get(tag-1).setSize(new_size, general_scale_factor);
    	 item_image_size=calc_image_size(menuitems.get(tag-1), new_size);
    	 menuitems.get(tag-1).setScaleX(item_image_size.width);
    	 menuitems.get(tag-1).setScaleY(item_image_size.height);
     } 
     
     public CGSize get_size()
     
     {
    	 CGSize size_menu = CGSize.make(this.scaled_size_width, this.scaled_size_height);
    	 return size_menu;
     }
    
     public void add_item(String path, int tag, CGPoint position_item, CGSize new_item_size)
     {
    	  MenuItemLayer item = null;
    	  item = new MenuItemLayer(path, tag);
    	  CGSize item_s = CGSize.make(new_item_size.width*general_scale_factor, new_item_size.height*general_scale_factor);
    	 
    	 // item.setPosition(512f, 360f);
    	  //item.setColor(ccColor3B.ccBLUE);
    	  CGSize item_size = null;
    	  item_size = calc_image_size(item, item_s);
    	  item.setScaleX(item_size.width);
    	  item.setScaleY(item_size.height);
    	  item.set_scale_factors(item_size);
    	  item.setSize(new_item_size,general_scale_factor);
    	  System.out.println("IIIIIII"+translation);
    	  item.set_Position(this, position_item, translation);
    	  menuitems.add(item); 
    	  addChild(menuitems.get(tag-1));
     }
     
     public void change_Image_item(String new_image_path, int tag)
     {
    	 CCSprite img = CCSprite.sprite(new_image_path);
    	 CGSize image_size = this.get_item(tag).getSize();
    	 System.out.println("PSD  "+new_image_path);
    	 this.get_item(tag).change_path(new_image_path);
    	 CGSize scale_factors = calc_image_size(img, image_size);
   	      this.get_item(tag).setScaleX(scale_factors.width);
   	      this.get_item(tag).setScaleY(scale_factors.height);
   	    
     }
     public void setItemPosition( CGPoint location, int tag )
     {
    	 menuitems.get(tag-1).set_Position(this, location, translation);
    
     } 
     
     public CGPoint getItemPosition(int tag)
     { 
    	return  menuitems.get(tag-1).get_Position( );

     }
    
     public MenuItemLayer get_item(int tag)
     {
    	 return menuitems.get(tag-1);
     }
     
     public void set_touch_menu_state (int value)
     {
    	 this.touch_state = value;
     }
     
     public int get_touch_menu_state()
     {
    	
    	 return this.touch_state;
     }
     
    
    
     public int Touch_Detect ( CGPoint location_touch)
     { 
   	  int tag=-1; 
   	  
   	  float x_coord = this.getPosition().x;
   	  float y_coord = this.getPosition().y;
   	System.out.println("Bleaa suka "+x_coord+" "+y_coord);
         CGRect sprite = CGRect.make(x_coord,y_coord,this.getContentSize().width,this.getContentSize().height);        
   		if(sprite.contains(location_touch.x, location_touch.y) && this.isTouchEnabled()==true)	           
               {tag= this.getTag();
     
               }
   	  
   	  return tag;
     }
     public boolean ccTouchesEnded(CGPoint location)
     {
    	 
    	 //CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(),event.getY())); 
    	  this.touch_state= this.Touch_Detect(location);
    	  
    	if(this.touch_state!=-1)
    	{  
    		 	
    	 int menu_item_length = menuitems.size();
    	 System.out.println("touch state "+touch_state+" "+menu_item_length);
         for(int i =0; i<menu_item_length;i++)
        	 if(menuitems.get(i).get_isTouchEnabel())
        	 {
        		 
          	   item_touch_tag=menuitems.get(i).Touch_Detect(this,location);
               if(item_touch_tag!=-1) return true;   
               
        	 }
         return true;
    	}
       //;
   	  //System.out.println("COORDOnate "+menuitems.size()+" "+location);
    	 return false;
     }
     
     public int get_item_touch_tag()
     {
    	 return item_touch_tag;
     }
     public CGSize calc_local_scale_factor (CGSize screen_size, CGSize menu_size)
     {
    	 CGSize local_scale=null;
    	 
    	 float width = screen_size.width/menu_size.width;
    	 float height = screen_size.height/menu_size.height;
    	 local_scale = CGSize.make(width, height);
    	 return local_scale;
     }
     public CGSize calc_image_size (CCSprite image, CGSize req_size)
     { 
    	
    	 float scalex = req_size.width/image.getContentSize().width;
    	 float scaley = req_size.height/image.getContentSize().height;
         return CGSize.make(scalex, scaley);
    	
     }
     
     public void Destroy()
     {
    	 for(int i=this.menuitems.size()-1; i>=0; i--)
    	 {
    		 menuitems.get(i).Destroy();
    		 
    		 menuitems.remove(i);
    	 } 
    	 
    		     menuitems = null;
    		    scaled_size_width=0;
    		    scaled_size_height=0;
    		    item_touch_tag=0;
    		    touch_state = 0;
    		    general_scale_factor = 0;
    		   
     }
}
