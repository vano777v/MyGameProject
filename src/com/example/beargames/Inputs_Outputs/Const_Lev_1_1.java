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
       this.bases_pos = new Float[]{16.66f,0f,(float)Constants_Game.base_dim/this.arena_area_coff,0f};
       this.bases_config = new String[][]
    		   {
    		      {"level_1_3", "b","1.2","118;948","0;0","150;0","930;570","600;600","400;610"},
    		      {"level_1_3", "v", "1.2","118;948", "1260;0", "0;0","-15;420","600;600","350;820"}
    		   
    		   };
       this.base_act_elem = 2;
      this.action_base_config = new Integer[][]
    		  { {2,1},
    		    {2,1} 
    		  };
      this.action_base_list= new String[][]
    		  {
    		     {"castle/personage/animation/b/","bossm_", "smoking_first","0.4", "9", "11", "false"},
    		     {"castle/personage/animation/b/","bossm_", "smoking_loop", "0.5","1", "9", "true"},
    		     {"castle/flag/", "flagm_", "falg_movie", "0.1", "1", "9", "true"},
    		     {"castle/personage/animation/v/","bossm_", "first_step", "0.3", "1","3","false"},
    		     {"castle/personage/animation/v/","bossm_", "first_step", "0.4", "4","7","true"},
    		     {"castle/flag/", "flagm_", "falg_movie", "0.1", "1", "9", "true"}
    		  };
   }
      
}
