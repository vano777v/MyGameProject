package com.example.beargames.Inputs_Outputs;

import java.util.ArrayList;

import org.cocos2d.types.CGSize;

import com.example.game.arena.elements.Main_Personage;

public abstract class Constants_Game
{
   
    public  int base_act_elem     =0;
   
    public static String bear_box_engine_path     =          "Personages/b/b_box/";   
    public static String bear_mace_engine_path    =          "Personages/b/b_mace/";  
    public static int    base_dim                 =          1500;
    public static int    base_pers_dim            =          512;
    public static int    pers_dim                 =          614;
    public static int    main_menu_dim            =          250;
    public static int    arena_area_coff          =          3;
    public int paralax_child=0;
    public static Float[][] parlax_value=null;
    public String [] paralax_path=null;
    public Boolean[] paralax_anim=null;
    public String[][] pralax_conf;
    public String[][] bases_config;
    public Float[] bases_pos= null;
    public Integer[][] action_base_config = null; 
    public String[][] action_base_list= null;
    public ArrayList<Main_Personage> bear_team_fight = null;
    abstract  public void start_flags_movie();
    abstract public void start_bear_base_default_movie();
    abstract public void start_vimpire_base_default_movie();
    abstract public Main_Personage box_bear_init( String update_path);
    abstract public void init_animation_bear_box_engine(String update_path, Main_Personage pers);
    abstract public Main_Personage mace_bear_init( String update_path);
    abstract public void init_animation_bear_mace_engine(String update_path, Main_Personage pers);
    abstract public Main_Personage get_bear(int index);
}
