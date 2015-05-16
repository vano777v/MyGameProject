package com.example.game.arena.elements;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class Base_Element extends CCSprite 
{
	private int life_base = 0;
	private int total_life = 0;
	private int money = 0;
	private int mana = 0;
	private int unitlimit = 0;
	private int count_unit = 0;
	private CGSize atac_area=null;
	private float scale_factor=0;
	private CCSprite img=null;
 // The sprite instance.

  public Base_Element (String path, float  scale_factor)
    {
    	//img= new CCSprite(path);
    	//float coff = 1500f/this.getContentSize().height; 
    	//img.setContentSize(coff*scale_factor*img.getContentSize().width, coff*scale_factor*img.getContentSize().height);
	  super(path);
	  //System.out.println("sssssssss"+path);
    	
    	//img.setScale(coff*scale_factor);
    	this.setAnchorPoint(0, 0);
    	atac_area = CGSize.make(0, 0);
    	this.scale_factor=scale_factor;
    	//addChild(img);
    }
	
	
	protected void setTotalBaseLife(int BaseLife)
	{
		this.total_life = BaseLife;
	}
	
	protected int getTotalBaseLife()
	{
		return this.total_life;
	}
	
	protected void setBaseLife(int BaseLife)
	{
		this.life_base = BaseLife;
	}
	
	protected int getBaseLife()
	{
		return this.life_base;
	}
	
	protected void setMoney(int m)
	{
		this.money = m;
	}
	
	protected int getMoney()
	{
		return this.money;
	}
	
	protected void setMana(int m)
	{
		this.mana = m;
	}
	
	protected int getMana()
	{
		return this.mana;
	}
	
	protected void setUnitLimit(int m)
	{
		this.unitlimit = m;
	}
	protected int getUnitLimit()
	{
		return this.unitlimit;
	}
	protected void setAtacArea(CGSize area )
	{
		atac_area.set(area);
	}
	protected CGSize getAtacArea()
	{
		
		return atac_area;
	}
	protected void setCountUnit(int units)
	{
		this.count_unit=units;
	}
	protected int getCountUnit()
	{
		return this.count_unit;
	}
	public void Base_Element_Destructor()
	{
		life_base = 0;
		total_life = 0;
		money = 0;
		mana = 0;
		unitlimit = 0;
		count_unit = 0;
		atac_area.set(0, 0);
		atac_area=null;
		this.removeFromParentAndCleanup(true);
	}
	
	public void setPositionBaseElement(CGPoint position)
	{
		this.setPosition(position.x*scale_factor, position.y*scale_factor);
	}
	public void setSize(CGSize new_size )
	{
		float coff = new_size.width/this.getContentSize().width;
		this.setContentSize(new_size.width*coff*scale_factor,new_size.height*coff*scale_factor );
		this.setScale(coff*scale_factor);
	}
	
}
