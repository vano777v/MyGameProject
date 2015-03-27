package com.example.beargames;

import java.lang.reflect.GenericSignatureFormatError;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.gesture.GesturePoint;
import android.graphics.Bitmap;

public class MenuItemLayer extends CCSprite
{
	private float size_width=0;
	private float size_height=0;
	private Boolean _isTouchEnabled=null;
	private Boolean is_touch = null;
	private float sfactor_x=0f;
	private float sfactor_y=0f;
	private String path_progress_bar_refrash=null;
	private static float time_request;
	private static float time_count;
	//private static float count
	private static CCProgressTimer image_progress = null;
	 public MenuItemLayer (String path, int tag )
	{
		super(path);
		Boolean state_item=false;
		path_progress_bar_refrash = new String();
		Boolean is_touch = false;
		time_count= 0;
		time_request=0;
		this._isTouchEnabled = state_item;
		this.is_touch= is_touch;
		this.setAnchorPoint(0f, 0f);
		setTag(tag);
		
	}
	
	 public void set_path_progress_bar(String path)
	 {
		  path_progress_bar_refrash= path; 
	 }
	 public String get_path_progress_bar()
	 {
		 return path_progress_bar_refrash;
	 }
	 public void set_scale_factors(CGSize value)
	 {
		 this.sfactor_x = value.width;
		 this.sfactor_y= value.height;
	 } 
	 public CGSize get_scale_factors()
	 {
		 return CGSize.make(sfactor_x, sfactor_y);
	 }
	 
	 public void time_progress_bar_init(String image_path )
	 {
		 image_progress = CCProgressTimer.progress(image_path);
		 image_progress.setAnchorPoint(CGPoint.make(0, 0));
		 image_progress.setPosition(0, 0);
		 image_progress.setType(CCProgressTimer.kCCProgressTimerTypeVerticalBarBT);
		 image_progress.setPercentage(0f);
		 image_progress.setScaleX(sfactor_x);
		 image_progress.setScaleY(sfactor_y);
		 this.addChild(image_progress);
	 }
	
	 
	
	 protected void time_progress(float time)
	 {
		 time_request = time;
		 time_count=0;
		 this.schedule("life", 0.1f);
	 }
	 public void life (float dtime)
	 {
		 if(time_count<=time_request)
		 {
			 time_count+=1;
			 //System.out.println("Auuuu");
			 image_progress.setPercentage(time_count*10); 
		 }
		 else
		 {
			 this.unschedule("life");
			 image_progress.setPercentage(0f);
				CCTexture2D s=  CCTextureCache.sharedTextureCache().addImage(path_progress_bar_refrash);
				this.setTexture(s);
		        this.setScaleX(sfactor_x);
		        this.setScaleY(sfactor_y);
				this.is_touch=false;
			 time_count=0;
			 time_request=0;
			 System.out.println("Bastaaaaaaaa");
		 }
	 }
	 public void set_Position ( MenuLayer menu, CGPoint position_intem, CGSize scale_local_factor, CGSize scale_general_factor ) 
	 {
		 //CGSize size_menu_panel= null;
		
			 
			 float pos_x_item =position_intem.x*scale_general_factor.width;//*scale_local_factor;
			 float pos_y_item =position_intem.y*scale_general_factor.height;//*scale_local_factor.height*
			  
			if((this.size_width+pos_x_item) > menu.scaled_size_width) 
				 pos_x_item = (menu.scaled_size_width-this.size_width);
			 
			 if((this.size_height+pos_y_item) > menu.scaled_size_height) 
				 pos_y_item = (menu.scaled_size_height-this.size_height);
			 //System.out.println(menu.get_size().width);
			 //System.out.println(menu.get_size().height);
	             this.setPosition(pos_x_item, pos_y_item);
      	     
		 
	}
	
	 public void copy_to(MenuItemLayer item)
	 {
		 item._isTouchEnabled = this._isTouchEnabled;
		 item.is_touch = this.is_touch;
		 item.size_height = this.size_height;
		 item.size_width= this.size_width;
		 item.setTag(this.getTag());
		 CCTexture2D s=  this.getTexture();
		 item.setTexture(s);
		 
	 }
	 public CGPoint get_Position(CGSize local_scale_factor, CGSize general_scale_factor ) 
	 {
		CGPoint position = null;
		float x_coord= this.getPosition().x/local_scale_factor.width/general_scale_factor.width;
		float y_coord = this.getPosition().y/local_scale_factor.height/general_scale_factor.height;
		return position.make(x_coord,y_coord); 
	 } 
	 public void setSize ( CGSize new_size, CGSize local_scale_factor, CGSize general_scale_factor)
	 {
			size_width =  new_size.width;//*local_scale_factor.width*general_scale_factor.width;
			size_height = new_size.height;//*local_scale_factor.height*general_scale_factor.width;
			
			
			this.setContentSize(size_width, size_height);
	 }
	 public CGSize getSize (CGSize local_scale_factor, CGSize general_scale_factor)
	 {
	     float width = this.size_width/general_scale_factor.width;//local_scale_factor.width;
	     float height = this.size_height/general_scale_factor.height;//local_scale_factor.height/general_scale_factor.height;
		 CGSize size = CGSize.make(width, height);
		 
		 return size;
	 }
	 
	 
	 public void change_path (String path, CGSize local_scale_factor, CGSize general_scale_factor)
	 {
		
	
		CCTexture2D s=  CCTextureCache.sharedTextureCache().addImage(path);
		this.setTexture(s);
        this.setScaleX(general_scale_factor.width);
        this.setScaleY(general_scale_factor.height);
	 }
	
      public Boolean get_isTouchEnabel()
      {
    	 return this._isTouchEnabled ;
      }	
      public void setisTouchEnabled (Boolean value)
      {
    	  this._isTouchEnabled = value;
      }
      
      public int Touch_Detect (MenuLayer menu, CGPoint location_touch, CGSize local_scale_factor, CGSize general_scale_factor )
      { 
    	  int tag=-1; 
    	  
    	  float x_coord = (this.getPosition().x+menu.getPosition().x)/general_scale_factor.width;//local_scale_factor.width+menu.getPosition().x;
    	  float y_coord = (this.getPosition().y+menu.getPosition().y)/general_scale_factor.height;//local_scale_factor.height+menu.getPosition().y;
    	  
          CGRect sprite = CGRect.make(x_coord,y_coord,this.size_width,this.size_height);        
    		if(sprite.contains(location_touch.x, location_touch.y))	           
                {tag= this.getTag();
      
                }
    	  
    	  return tag;
      }
      public void set_touch_state (Boolean state)
      {
    	  this.is_touch = state;
      }
      public Boolean get_touch_state ()
      {
    	  return this.is_touch;
      }
 
     public void Destroy() 
     {   
    	  size_width=0;
          size_height=0; 
         _isTouchEnabled=null;
         this.is_touch=null;
     } 
    

}
