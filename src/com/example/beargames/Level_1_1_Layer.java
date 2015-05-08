package com.example.beargames;

import javax.microedition.khronos.opengles.GL10;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;
import org.cocos2d.types.ccPointSprite;

import android.view.MotionEvent;

;
public class Level_1_1_Layer extends CCColorLayer
{
	private CCSprite mback = new CCSprite("campaign_1/level_1/paralax/mback.png");
	private CCSprite sback = new CCSprite("campaign_1/level_1/paralax/sback.png");
	private CCSprite tback = new CCSprite("campaign_1/level_1/paralax/tback.png");
	private CCSprite top_back = new CCSprite("campaign_1/level_1/paralax/noise.png");
	private int off =0;
	private int dtp =1;
	private CGPoint old_touch;
	private CGPoint start_touch;
	float pp =0;
	CGSize winSize = CCDirector.sharedDirector().displaySize();
	protected Level_1_1_Layer(ccColor4B color, float perc) {
		super(color);
		// TODO Auto-generated constructor stub
		this.setContentSize(CGSize.make(winSize.width*3, winSize.height));			
		this.setPosition(CGPoint.ccp(0, 0));
		this.setIsTouchEnabled(true);
		 pp = perc;
		//tback.setContentSize(2560f*pp,2560*pp);
		
		    mback.setTextureRect(0, 0, winSize.width*4, winSize.height*(35f/winSize.height), false);
			mback.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
			
			sback.setScale(0.8f);
			sback.setTextureRect(0, 0,  winSize.width*4, winSize.height*(2560f/winSize.height), false);
			sback.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
			
			tback.setTextureRect(0, 0,  winSize.width*5, winSize.height*(2048/winSize.height), false);
			tback.getTexture().setTexParameters(GL10.GL_LINEAR, GL10.GL_LINEAR, GL10.GL_REPEAT, GL10.GL_REPEAT);
			
			mback.setAnchorPoint(CGPoint.make(0,0));
			sback.setAnchorPoint(CGPoint.make(0,0));
			tback.setAnchorPoint(CGPoint.make(0,0));
		
		CCParallaxNode voidNode = CCParallaxNode.node();
		voidNode.addChild(top_back, 0, 0, 0, 0, 0);    	
    	voidNode.addChild(tback, 1, 0.3f, 0.6f, 0, -700);
    	voidNode.addChild(sback, 2, 0.15f, 0.4f, 0, -100);
    	voidNode.addChild(mback, 3, 0.1f, 0.2f, 0, 250f*pp);
    	addChild(voidNode, 0, 6);
    	//this.schedule("update", 1);
	}
	public boolean ccTouchesBegan(MotionEvent event)
	{
		
		start_touch = CGPoint.ccp(event.getX(), event.getY());
		return true;
	}
	public boolean ccTouchesMoved(MotionEvent event)
	{
		CGPoint touchLocation, translation;
		touchLocation = CGPoint.ccp(event.getX(), event.getY());
		translation= CGPoint.ccpSub(start_touch,touchLocation);
		
	
		   float lim=this.getPosition().x+(translation.x)*(-1);
			System.out.println("Leap leap "+lim);
		   if(lim<0 )
		     this.setPosition(lim, 250f*pp);
		   old_touch=touchLocation;
		return true;
	}
}
