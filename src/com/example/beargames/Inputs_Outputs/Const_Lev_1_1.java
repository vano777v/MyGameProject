package com.example.beargames.Inputs_Outputs;

public class Const_Lev_1_1 extends Constants_Game 
{
   public static int unit_limit          =         15;
   
   
   public Const_Lev_1_1()
   {
	   this.paralax_child=4;
	   this.parlax_value= new Float[][]{{0f,250f, 0f,0f},{0f, 0f, 0.2f, 0f},{0f, 70f, 0.3f, 0f},{0f, 60f, 0.1f, 0f}};
       this.paralax_path = new String[] {"/paralax/noise.png","/paralax/tback.png", "/paralax/sback.png", "/paralax/mback.png"}; 
   }
      
}
