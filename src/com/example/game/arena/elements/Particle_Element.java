package com.example.game.arena.elements;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccGridSize;

import com.example.engine.beargames.Action_Activity;

public class Particle_Element extends CCSprite
{
	private String is_who= null; 
	private CGSize local_scale_factor = null;
	private float general_scale_factor = 0;
	private CGSize reference_size=null;
	private CGSize main_size=null;
	private Action_Activity animation =null;
	private CGPoint main_position=null;
	private CGPoint enimy_position = null;
	private float step_move=0;
	private Boolean is_bear=false;
	private CGSize elispe_element=null;
	private float orbit_counter=0;
	private CGSize elipse_axis=null;
	private float height_attack=0;
	private CGPoint elipse_center_calc =null;
	private int total_time_fly=0;
	private float angle=0;
	private float default_angle;
	public Particle_Element(String path, String is_who, CGSize size_request, CGSize local_factor, float general_factor) 
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
		elipse_axis = CGSize.make(0, 0);
		elipse_center_calc= CGPoint.make(0, 0);
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
	
	public Action_Activity get_animationRefrence()
	{
		return animation;
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
	public void start_move_elipse(float height_atac_orbit, CGPoint soldier_pos, float start_attact_point,CGPoint enimy_pos, Boolean is_who)
	{
		float half_perimeter=0, time_step=0;
	
		float y;
		height_attack = height_atac_orbit;
	    this.enimy_position.set(enimy_pos);
	    is_bear = is_who;
	    orbit_counter=0;
	   if(is_who)
	   {	
		     
		     
		     elipse_axis.set(lenght_segm_calulation(soldier_pos, enimy_pos),height_attack*2);
		     elipse_center_calc.set(calc_midle_point(soldier_pos, enimy_position));
		     angle=max_covert_detect(soldier_pos.x, enimy_pos.x,step_move, height_attack);
		     y=calc_elispe_orbit(elipse_axis, elipse_center_calc, this.height_attack,start_attact_point, orbit_counter);
		     default_angle=get_default_angle(soldier_pos, enimy_pos);
		     //rotation = 360*(y/angle);
		    // this.setRotation(360-rotation+default_angle);
		     half_perimeter = this.half_elipse_perimeter(this.height_attack,lenght_segm_calulation(soldier_pos, enimy_pos));
		     
		     time_step = total_time_fly/(half_perimeter/step_move);
		    
		     this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(soldier_pos.x+orbit_counter, y)), CCCallFunc.action(this, "Move_Control_elipse")));
		     System.out.println("rotation "+ rotation);
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
		if(is_bear)
		   {	
			  
			
		     orbit_counter+=step_move;
		     if(orbit_counter+250 >=enimy_position.x) orbit_counter=0;
		    
		     y=calc_elispe_orbit(elipse_axis, elipse_center_calc,this.height_attack, 250, orbit_counter);
		     
		   //  rotation = 360*(y/angle);
		     //if(orbit_counter+250 >= elipse_center_calc.x)
		    //	 this.setRotation(360-rotation+default_angle);
		     //else this.setRotation(rotation-default_angle);
             half_perimeter = this.half_elipse_perimeter(this.height_attack,elipse_axis.width);
		     time_step = total_time_fly/(half_perimeter/step_move);
		     System.out.println("rotation "+ rotation);
		     this.runAction(CCSequence.actions(CCMoveTo.action(time_step, CGPoint.make(250f+orbit_counter, y)), CCCallFunc.action(this, "Move_Control_elipse")));
		     System.out.println("elispe "+orbit_counter+" "+y);
		    
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
        delta = square_b-4*a*c;
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
	
	private float max_covert_detect( float start_point, float finish_point,float step, float height)
	{
		float y_max =-1, a, b, c,n,m, x_mod, square_x_mod, square_m, square_n, square_yc;
		float covert=0;
		CGSize rez=null;
		a=1;
		b= 2*elipse_center_calc.y*(-1);
		n=elipse_axis.height/2;
		m= elipse_axis.width/2;
		square_m=m*m;
		square_n=n*n;
		square_yc = elipse_center_calc.y*elipse_center_calc.y;
		float i = 0;
		while(i<finish_point)
		{
			x_mod = start_point+i-elipse_center_calc.x;
			square_x_mod = x_mod*x_mod;
			c= square_yc-((1-(square_x_mod/square_m))*square_n);
		    rez=clac_ecuation(a, b, c);
		    System.out.println("detect_y "+c);
		    if(rez.width>=rez.height) y_max = rez.width;
		    else y_max= rez.height;
		    if(lenght_segm_calulation(elipse_center_calc,CGPoint.make(start_point+i, y_max))+0.1>=height)
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
		System.out.println("info_ecu "+elipse_center+" "+elipse_axis+" "+attack_height_orbit);
		m = elipse_axis.width/2;
		n= elipse_axis.height/2;
		square_m = m*m;
		square_n = n*n;
		square_yc = elipse_center.y *elipse_center.y; 
		c=square_yc-((1-(square_x_mod/square_m))*square_n);
		b= 2*elipse_center.y*(-1);
		a=1;
		System.out.println("coficineti ecu "+a+" "+b+" "+c);
		 result= clac_ecuation(a, b, c);
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
		NumberFormat nf = NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
		trans= nf.format(arctan);
		result=Float.parseFloat(trans);
		return result;
	}
	public void set_time_of_fly(int value)
	{
		total_time_fly=value;
	}
	public int get_time_of_fly()
	{
		return total_time_fly;
	}
}
