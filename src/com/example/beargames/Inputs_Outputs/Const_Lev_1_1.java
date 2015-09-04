package com.example.beargames.Inputs_Outputs;

public class Const_Lev_1_1 extends Constants_Game 
{
   public static int unit_limit          =         15;
   
   
   public Const_Lev_1_1()
   {
	   this.paralax_child=5;
	   this.parlax_value= new Float[][]{{0f,250f, 0f,0f},{0f, 0f, 0.2f, 0f},{0f, 70f, 0.3f, 0f},{0f, 60f, 0.1f, 0f},{0f, 60f, 0.2f, 0f}};
       this.paralax_path = new String[] {"/paralax/noise.png","/paralax/tback.png", "/paralax/sback.png", "/paralax/mback.png","/paralax/fback.png"};
       this.paralax_anim = new Boolean[]{false, false, true, false, true};
       this.pralax_conf = new String[][]
    		   {{"campaign_1/level_1/paralax/","sback", "sb_fire","0.1","1","7"}, 
    		    {"campaign_1/level_1/paralax/","fback","fb_fire","0.1","1","7"}
    		   };
   }
      
}
