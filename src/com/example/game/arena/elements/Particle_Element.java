package com.example.game.arena.elements;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import org.cocos2d.types.ccGridSize;

import com.example.beargames.Game_Arena;
import com.example.engine.beargames.Action_Activity;

public abstract class Particle_Element extends CCSprite
{
	private String is_who= null; 
	private CGSize local_scale_factor = null;
	private float general_scale_factor = 0;
	private CGSize reference_size=null;
	private CGSize main_size=null;
	private Action_Activity animation =null;
	private CGPoint main_position=null;
	protected CGPoint enimy_position = null;
	private float step_move=0;
	private Boolean is_bear=false;
	private CGSize elispe_element=null;
	private float orbit_counter=0;
	private CGSize elipse_axis=null;
	private float height_attack=0;
	private CGPoint elipse_center_calc =null;
	private int total_time_fly=0;
	private float angle=0;
	private float start_point=0;
	protected CGPoint _soldier;
	protected Game_Arena arena=null; 
	private Integer[] demage_enimy =null;
	private CGPoint rf_location = null; 
	private ArrayList<Integer> Imunity_defence =null;
	abstract public void detect_colision();
	public Particle_Element(String path, String is_who, CGSize size_request, Game_Arena _arena, CGSize local_factor, float general_factor) 
	{
		super(path);
		this.is_who = is_who;
		general_scale_factor = general_factor; 
		local_scale_factor = CGSize.make(local_factor.width, local_factor.height);
		main_size = CGSize.make(this.getContentSize().width, this.getContentSize().height);
		float f_width = size_request.width/this.getContentSize().width;
		float f_height = size_request.height/this.getContentSize().height;
		this.setContentSize(size_request.width*f_width*general_factor/local_factor.width, size_request.height*general_factor/local_factor.height*f_height);
		this.setScaleX(f_width*general_factor/local_factor.width);
		this.setScaleY(f_height*general_scale_factor/local_factor.height);
		reference_size = CGSize.make(size_request.width,size_request.height);
		animation = new Action_Activity(this, is_who);
		main_position = CGPoint.make(0, 0);
		enimy_position= CGPoint.make(0, 0);
		elispe_element = CGSize.make(0, 0);
		rf_location = CGPoint.make(0, 0);
		arena =_arena;
		elipse_axis = CGSize.make(0, 0);
		elipse_center_calc= CGPoint.make(0, 0);
		_soldier=CGPoint.make(0, 0);
		demage_enimy = new Integer[9];
		Imunity_defence = new ArrayList<Integer>();	
		this.addChild(animation);
	}
	public Particle_Element (Particle_Element particle)
	{
		super(particle.getTexture());
		this.is_who = particle.is_who;
		this.general_scale_factor = particle.general_scale_factor;
		this.local_scale_factor = CGSize.make(particle.local_scale_factor.width,particle.local_scale_factor.height);
		this.setContentSize(particle.getContentSize());
		this.setScaleX(particle.getScaleX());
		this.setScaleY(particle.getScaleY());
		reference_size =CGSize.make(particle.reference_size.width,particle.reference_size.height);
		animation = new Action_Activity(this, particle.get_animation_element());
		main_position = CGPoint.make(0, 0);
		enimy_position= CGPoint.make(0, 0);
		elispe_element = CGSize.make(0, 0);
		rf_location= CGPoint.make(0, 0);
		this.set_Refrence_location(particle.get_unscaled_Refrence_Location().x, particle.get_unscaled_Refrence_Location().y);
		this.arena =particle.arena;
		elipse_axis = CGSize.make(0, 0);
		elipse_center_calc= CGPoint.make(0, 0);
		_soldier=CGPoint.make(0, 0);
		demage_enimy = new Integer[9];
		this.set_demage_enimy_list(particle.demage_enimy);
		Imunity_defence = new ArrayList<Integer>();
	    for(int i=0;i<particle.Imunity_defence.size();i++)
	    	Imunity_defence.add(particle.Imunity_defence.get(i));
	    this.addChild(animation);
	}
	public void set_Size (float width, float height)
	{
		float f_width = width/main_size.width;
		float f_height = height/main_size.height;
		this.setContentSize(main_size.width*f_width*general_scale_factor/local_scale_factor.width, main_size.height*general_scale_factor/local_scale_factor.height*f_height);
		this.setScaleX(f_width*general_scale_factor/local_scale_factor.width);
		this.setScaleY(f_height*general_scale_factor/local_scale_factor.height);
		reference_size = CGSize.make(width,height);
	}
	
	public CGSize getReferencesize()
	{
		return reference_size;
	}
	
	
	
	public void set_Position(float x, float y)
	{
		System.out.println("partic"+local_scale_factor+" "+general_scale_factor);
		this.setPosition(x*general_scale_factor/local_scale_factor.width , y*general_scale_factor/local_scale_factor.height);
	    main_position.set(x, y);
	}
	public CGPoint get_PositionRefrence()
	{
		return main_position;
	}
float rotation;
int direction=0;
	public void start_move_elipse(float height_atac_orbit, CGPoint soldier_pos, float start_attact_point,CGPoint enimy_pos, Boolean is_who)
	{
		float half_perimeter=0, time_step=0;
	    
		float y;
		//height_attack = height_atac_orbit;
	    this.enimy_position.set(enimy_pos);
	    is_bear = is_who;
	    orbit_counter=0;
	   if(is_who)
	   {	
		     direction=detect_ellipse_parameters(soldier_pos, enimy_pos, elipse_axis, elipse_center_calc);
		     System.out.println("parameters_ellips "+elipse_axis+" "+elipse_center_calc+" "+start_point);
		     height_attack=elipse_axis.height/2;
		     //elipse_axis.set(lenght_segm_calulation(soldier_pos, enimy_pos),height_attack*2);
		     //elipse_center_calc.set(calc_midle_point(soldier_pos, enimy_position));
		     angle=max_covert_detect(soldier_pos.x, (elipse_center_calc.x+elipse_axis.width/2),step_move, height_attack);
		     y=calc_elispe_orbit(elipse_axis, elipse_center_calc, this.height_attack,start_point, orbit_counter);
		     //default_angle=get_default_angle(soldier_pos, enimy_pos);
		     rotation = 360*(y/angle);
		     orbit_counter*=direction;
		    this.setRotation(360-rotation);
		     half_perimeter = this.half_elipse_perimeter(this.height_attack,lenght_segm_calulation(soldier_pos, enimy_pos));
		     
		     time_step = total_time_fly/(half_perimeter/step_move);
		    
		     this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(start_point+orbit_counter, y)), CCCallFunc.action(this, "Move_Control_elipse")));
		     System.out.println("rotation "+ angle);
		     orbit_counter+=step_move;
	   } 
	   else 
	   {
		     elipse_axis.set(soldier_pos.x, enimy_pos.x);
		     orbit_counter+=step_move;
		   //  calc1 = (enimy_position.x+soldier_pos.x)/2;
		     //calc2= soldier_pos.y+height_atac_orbit;
		     //elipse_center_calc.set(calc1, calc2);
		     y=calc_elispe_orbit(elipse_axis, elipse_center_calc, this.height_attack,start_attact_point, orbit_counter*(-1));
		     //this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(soldier_pos.x+orbit_counter, y)), CCCallFunc.action(this, "Move_Control_elipse")));
		     System.out.println("elispe "+orbit_counter+y);
	   }
	   
	   
	}
	
	Boolean change=false;
	public void Move_Control_elipse()
	{
		float half_perimeter,time_step;
		String inter=null, format=null;
		float y=0;
        float last_point;
		
		if(is_bear)
		   {	
			  
			if(direction<0)
				{
				      last_point=elipse_center_calc.x-elipse_axis.width/2;
				      if((start_point+orbit_counter*direction)<=last_point) orbit_counter=0;
				}
			else 
			{
				 last_point = elipse_center_calc.x+elipse_axis.width/2;
				 if((start_point+orbit_counter*direction)>=last_point) orbit_counter=0;
			}
		     orbit_counter+=step_move;
		     //if(start_point+orbit_counter>=(elipse_center_calc.x+elipse_axis.width/2) ||start_point+orbit_counter<=(elipse_center_calc.x+elipse_axis.width/2)) orbit_counter=0;
		    
		     y=calc_elispe_orbit(elipse_axis, elipse_center_calc,this.height_attack,start_point, orbit_counter);
		     
		     rotation = 360*(y/angle);
		    
		    	if(start_point+orbit_counter*direction>=elipse_center_calc.x) this.setRotation(360-rotation);
		         this.setRotation(rotation);
		     //angle = max_covert_detect(start_point,(start_point+elipse_axis.width),step_move, height_attack);
             half_perimeter = this.half_elipse_perimeter(this.height_attack,elipse_axis.width);
		     time_step = total_time_fly/(half_perimeter/step_move);
		     System.out.println("rotation "+ angle);
		
		     this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(start_point+orbit_counter*direction, y)), CCCallFunc.action(this, "Move_Control_elipse")));
		     System.out.println("elispe "+(start_point+orbit_counter*direction)+" "+y);
		    
		   } 
		   else 
		   {
			 
			   orbit_counter+=step_move;
			     //calc1 = (enimy_position.x+elipse_axis.width)/2;
			     //calc2= enimy_position.y+height_attack;
			    // elipse_center_calc.set(calc1, calc2);
			     y=calc_elispe_orbit(elipse_axis, elipse_center_calc,this.height_attack, 250, orbit_counter*(-1));
			     //this.runAction(CCSequence.actions(CCMoveTo.action(0.8f, CGPoint.make(soldier_pos.x+orbit_counter, y)), CCCallFunc.action(this, "Move_Control_elipse")));
			     System.out.println("elispe "+orbit_counter+y);
		   }
	}
   
	public void  set_step_move(float step)
	{
		this.step_move = step;
	}
	
	private CGSize clac_ecuation(float a, float b, float c)
	{
		CGSize  rezult = null;
		String sqrt_delta=null;
		double sqrt;
		float delta=0, square_b, radic_delta=0, res1, res2;
		square_b = b*b;
		if(b==0)
           delta = 4*a*c*(-1);
		else 
			delta=square_b-4*a*c;
        sqrt = Math.sqrt(delta);
        NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
	    sqrt_delta= nf.format(sqrt);
	    radic_delta = Float.parseFloat(sqrt_delta);
	    res1 = (b*(-1)+radic_delta)/2;
	    res2 = (b*(-1)-radic_delta)/2;
	    rezult= CGSize.make(res1, res2);
		return rezult;
	}
   
	private float lenght_segm_calulation(CGPoint first, CGPoint second)
	{
		float lenght=0;
		float a,b;
		String trans=null;
		double calc=0;
		a= first.x - second.x;
		b= first.y - second.y;
		calc = Math.sqrt(a*a+b*b);
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
		trans = nf.format(calc);
		lenght = Float.parseFloat(trans);
		return lenght;
	}
	
	private float half_elipse_perimeter(float elipse_height, float long_axis_lenght)
	{
		float result = 0;
		double calc=0;
		String trans=null;;
		calc = (Math.PI*((elipse_height*2+long_axis_lenght)/2))/2;
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
		trans = nf.format(calc);
		result=Float.parseFloat(trans);
		return result;
	}
	
	private float max_covert_detect( float start_point_at, float finish_point,float step, float height)
	{
		float y_max =-1, a, b, c,n,m, x_mod, square_x_mod, square_m, square_n, square_yc;
		float covert=0;
		CGSize rez=null;
		a=1;
        if(elipse_center_calc.y==0) b=0;
        else
        	b= 2*elipse_center_calc.y*(-1);
		n=elipse_axis.height/2;
		m= elipse_axis.width/2;
		square_m=m*m;
		square_n=n*n;
		square_yc = elipse_center_calc.y*elipse_center_calc.y;
		float i = 0;
		while(i<finish_point)
		{
			x_mod = start_point_at+i-elipse_center_calc.x;
			square_x_mod = x_mod*x_mod;
			
			  c= square_yc-((1-(square_x_mod/square_m))*square_n);
			
			  System.out.println("coficineti ecu_ang "+a+" "+b+" "+c);  
		    rez=clac_ecuation(a, b, c);
		    
		    if(rez.width>=rez.height) y_max = rez.width;
		    else y_max= rez.height;
		    System.out.println("detect_y "+y_max+" "+height);
		    if((y_max+0.1)>=(elipse_center_calc.y+height))
		    {
		       covert=y_max;
		       break;
		    } 
		    i+=step;
		}
		return covert;
	}
	private CGPoint calc_midle_point(CGPoint first, CGPoint second)
	{
		float mx,my;
		CGPoint result = CGPoint.make(0, 0);
		mx= (first.x+second.x)/2;
		my= (first.y+second.y)/2;
		result.set(mx, my);  
		return result;
	}
	private  float calc_elispe_orbit(CGSize elipse_axis, CGPoint elipse_center, float attack_height_orbit, float start_atac_point, float delta_step )
	{
		float y_orbit=-1, a, b, c;
		CGSize result=null;
		float x_mod, square_x_mod,  m, square_m,square_n,n, square_yc; 
		x_mod = start_atac_point+delta_step-elipse_center.x;
		square_x_mod = x_mod*x_mod;
		
		m = elipse_axis.width/2;
		n= elipse_axis.height/2;
		square_m = m*m;
		square_n = n*n;
		square_yc = elipse_center.y *elipse_center.y; 

		 c=square_yc-((1-(square_x_mod/square_m))*square_n);
		
		if(elipse_center.y==0) b =0;
		else
			b= 2*elipse_center.y*(-1);
		
		a=1;
		System.out.println("coficineti ecu "+a+" "+b+" "+c);
		 result= clac_ecuation(a, b, c);
		 System.out.println("info_ecu "+square_x_mod+" "+c+" "+result+" "+delta_step+" "+square_m);
		 System.out.println("info_result "+result);
		 if(result.width >=result.height) y_orbit=result.width;
		 else y_orbit=result.height;
		return y_orbit;
				
	}
	
	private float get_default_angle(CGPoint first, CGPoint second)
	{
		float result=0;
		double arctan=0;
		String trans=null;
		float gradient = (first.y - second.y)/(first.x-second.x);
		if(gradient<=0) gradient*=-1;
		arctan=Math.atan(gradient);
		System.out.println("Arctan "+gradient+" "+arctan);
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
		trans= nf.format(arctan);
		result=Float.parseFloat(trans)*59.2958f;
		return result;
	}
	private int detect_ellipse_parameters(CGPoint sold, CGPoint enimy,CGSize axis, CGPoint elipse_center)
	{
		int direction=1;
		float axis_a=0, axis_b=0;
		CGPoint center=null;
	   if(sold.y==enimy.y)
	   {
		   start_point = sold.x;
		   axis_b = lenght_segm_calulation(sold, enimy);
		   axis_a = 175f*general_scale_factor*2;
		   center =  calc_midle_point(sold, enimy);
		   axis.set(axis_b, axis_a);
		   elipse_center.set(center);
		   
	   }
	   if(sold.y<enimy.y)
	   {
		   
		   start_point = sold.x;
		   System.out.println("soldier "+sold+" "+start_point);
		   axis_b = lenght_segm_calulation(sold, CGPoint.make(enimy.x, sold.y))*2;
		   axis_a =(enimy.y-sold.y)*2;
		   center= CGPoint.make(enimy.x, sold.y);
		   axis.set(axis_b, axis_a);
		   elipse_center.set(center);
		   
	   }
	   if(sold.y>enimy.y)
	   {
		   axis_b = lenght_segm_calulation(CGPoint.make(sold.x, enimy.y), enimy)*2;
		   axis_a =(sold.y-enimy.y)*2;
		   center= CGPoint.make(sold.x, enimy.y);
		   axis.set(axis_b, axis_a);
		   elipse_center.set(center);
		   start_point = sold.x;
	   }
	   
	   if(sold.x >enimy.x) direction =-1;
	   return direction;
	}
	public void set_time_of_fly(int value)
	{
		total_time_fly=value;
	}
	public int get_time_of_fly()
	{
		return total_time_fly;
	}
	
	public void start_liniar_move(CGPoint sold, CGPoint enimy)
	{
		
		float y=0, dx,dy,x,dir_angle=0,  a;
		_soldier.set(sold);
		enimy_position.set(enimy);
		
		float lenght = lenght_segm_calulation(sold, enimy);
		float angle = get_default_angle(sold, enimy);
		
		System.out.println("Angle_l "+angle);
		if(_soldier.x>enimy.x) direction=-1;
		else direction=1;
		if((_soldier.y>enimy.y && direction==-1 )||(_soldier.y<enimy.y&&direction==1)) dir_angle=360-angle;
		else dir_angle=angle;
		//this.setRotation(dir_angle);
		float time_step = total_time_fly/step_move;
		orbit_counter=step_move;
	    a= (enimy_position.x-_soldier.x);
	    if(a==0)
	    { 
	    	dx=0; 
	    	if(_soldier.y>enimy_position.y) direction=-1;
	    	else direction=1;
			dy=0;
			y=_soldier.y+orbit_counter*direction;
			x=_soldier.x;
	    }
	    else
	    {
	    	   dx= (orbit_counter*direction)/(enimy_position.x-_soldier.x);
			   dy= (enimy_position.y-_soldier.y);
			   y=dx*dy+_soldier.y;
			   //time_step = total_time_fly/step_move;
			   x=_soldier.x+(orbit_counter*direction);
	    }
		 System.out.println("Liniar_x_y "+x+" "+y);
		this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(x, y)), CCCallFunc.action(this, "Move_Control_liniar")));
	}
	
	public void Move_Control_liniar()
	{
		float dx,dy,x,y,time_step,a;
		
			   orbit_counter+=step_move;
			   time_step = total_time_fly/step_move;
			   a = (enimy_position.x-_soldier.x);
			   if(a==0)
			   {
				    dx=0; 
			    	if(_soldier.y>enimy_position.y) direction=-1;
			    	else direction=1;
					dy=0;
					y=_soldier.y+orbit_counter*direction;
					x=_soldier.x;
					 
			   }
			   else{
				   dx= (orbit_counter*direction)/a;
				   dy= (enimy_position.y-_soldier.y);
				   y=dx*dy+_soldier.y;
				   
				   x=_soldier.x+(orbit_counter*direction);
			   }
			   System.out.println("Liniar_x_y "+x+" "+y);
			   this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(x, y)), CCCallFunc.action(this, "Move_Control_liniar")));
			   
			
		 detect_colision();
	}
	
	protected Main_Personage detect_intersect_pers()
	{
		Main_Personage enimy=null;
		if(is_who.equalsIgnoreCase("b"))
		{
			for(int i=0;i<arena.vimpire_element.size();i++)
			{
				if(intersect_attack(arena.get_personage(i, "v")))
				{
					enimy=arena.get_personage(i, "v");
					return enimy;
				}
			}
		}
		else 
		{
			for(int i=0;i<arena.bears_element.size();i++)
			{
				if(intersect_attack(arena.get_personage(i, "b")))
				{
					enimy=arena.get_personage(i, "b");
					return enimy;
				}
			}
		}
		return enimy;
	}
	
	protected Main_Base detect_intersect_base()
	{
		Main_Base base = null;
		if(is_who.equalsIgnoreCase("b"))
		{
			if(intersect_base(arena.get_Main_Base_list(1)))
				return arena.get_Main_Base_list(1);
		}
		else
		{
			if(intersect_base(arena.get_Main_Base_list(0)))
				return arena.get_Main_Base_list(0);
		}
		
		return base;
	}
	
	protected Boolean detect_out_border()
	{
		Boolean result= false;
		CGPoint orig = CGPoint.make(0, 0);
		CGRect obj = CGRect.make(orig,arena.getContentSize());
		if(!obj.contains(this.getPosition().x, this.getPosition().y))
		{
			result=true;
		}
		return result;
	} 
	private Boolean intersect_attack( Main_Personage second)
	{
	  
		Boolean result  = false;
	 
		if( second!=null)
			
		{
		  if( second.is_live==true )
		  {	
			
			  CGPoint first_point = CGPoint.make(second.getPosition().x + second.attack_coord.height,second.getPosition().y);
			  CGRect object = CGRect.make(first_point,CGSize.make(second.getContentSize().width-second.attack_coord.width-second.attack_coord.height, second.getContentSize().height));
			  if(object.contains(this.getPosition().x, this.getPosition().y))
						result=true;
		  }
		}
		return result;
	}
	public void  set_demage_enimy_list(Integer demage[])
	{
		for(int i=0;i<demage.length; i++)
		{
			demage_enimy[i]=demage[i];
		}
	}
	
	public Action_Activity get_animation_element()
	{
		return animation;
	}
	public Integer[] get_demage_list()
	{
		return demage_enimy;
	} 
	public void set_demage_enimy(int pers_tag, int value)
	{
		demage_enimy[pers_tag]=value;
	}
	public int get_demage_enimy(int pers_tag)
	{
		return demage_enimy[pers_tag];
	}
	public void add_defence_imunity(int pers_tag)
	{
		Imunity_defence.add(pers_tag);
	}
	
	public void set_Refrence_location(float x_coord, float y_coord)
	{
		float x,y;
		x= x_coord*general_scale_factor/local_scale_factor.width;
		y= y_coord*general_scale_factor/local_scale_factor.height;
		rf_location.set(x, y);
	}
	public CGPoint get_Refrence_location()
	{
		return rf_location;
	}
	public CGPoint get_unscaled_Refrence_Location()
	{
		float x,y;
		CGPoint result = CGPoint.make(0, 0);
		x= rf_location.x*local_scale_factor.width/general_scale_factor;
		y = rf_location.y*local_scale_factor.height/general_scale_factor;
		result.set(x, y);
		return result;
	}
	public ArrayList<Integer>get_imunity_list ()
	{
		return Imunity_defence;
	}
	public Boolean has_imunity(int pers_tag)
	{
		Boolean result=false;
		for(int i=0;i<Imunity_defence.size();i++)
			if(Imunity_defence.get(i)==pers_tag)
			{
				result=true;
				return result;
			}
		return result;
	}
	private Boolean intersect_base(Main_Base base)
	{
		Boolean result = false;
		
		if(base!=null)
		{
			
		
			CGRect object2 = CGRect.make(base.getPosition(), base.getContentSize());
			if(object2.contains(this.getPosition().x, this.getPosition().y))
			{
				result=true;
			}
		}
		return result;
	}
	
	public void Destructor()
	{
		this.is_who = null;
		this.general_scale_factor=0;
		this.local_scale_factor.set(0, 0);
		this.local_scale_factor=null;
		this.setContentSize(0,0);
		reference_size.set(0, 0);
		reference_size=null;
		animation.Destroy();
		animation=null;
		main_position = CGPoint.make(0, 0);
		main_position=null;
		enimy_position= CGPoint.make(0, 0);
		enimy_position=null;
		elispe_element = CGSize.make(0, 0);
		elispe_element=null;
		rf_location= CGPoint.make(0, 0);
		rf_location= null;
		elipse_axis = CGSize.make(0, 0);
		elipse_axis=null;
		elipse_center_calc= CGPoint.make(0, 0);
		elipse_center_calc=null;
		_soldier=CGPoint.make(0, 0);
		_soldier=null;
		this.set_demage_enimy_list(new Integer[]{0,0,0,0,0,0,0,0,0});
		demage_enimy=null;
		Imunity_defence.clear();
		Imunity_defence=null;
		arena.removeChild(this, true);
		arena=null;
	}
	
}
