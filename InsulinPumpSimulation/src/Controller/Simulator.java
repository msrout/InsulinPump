package Controller;

import Model.Battery;
import Model.GlucagonReservoir;
import View.InsulinFrame;
import Model.InsulinReservoir;
import Utility.SimulatorUtility;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Monalisa
 */
public class Simulator  extends Observable implements Runnable{ 

   public Map<Integer,Integer> map = new HashMap<Integer,Integer>();
   private volatile boolean running = true;
   
   


   public void prepareBloodSugar()
    {
        
      double bg_level = InsulinFrame.getInstance().getBloodSugar();
      double Insulin =  InsulinFrame.getInstance().getInsulin();
      double glucagon = InsulinFrame.getInstance().getGlucagon();
        //double normal_bg = val;
        for (int i = 0; i <= 1440;) {
	 //get random int value
          if (running == false) 
          {
	       break;
	  }
           int increased_unit = 0;
         
        /*
         * If you are giving in factor then consider recalculation.
         */
          
        double insulinFactor =  (14.3*bg_level-76*Insulin - 729.3)/150;
        
         if ( bg_level > 120 && Insulin > 0)
         {
             bg_level = bg_level - insulinFactor;
             InsulinFrame.getInstance().setJprogressValInsuline((Insulin/10000));
         }
         
         // for glucagon
         if( bg_level <= 90 )
         {
             bg_level = bg_level + glucagon;
             InsulinFrame.getInstance().setJprogressValGlucagon(glucagon/1000);
         }
         
       //Set blood sugar level
       InsulinFrame.getInstance().setBloodSugarLevel(bg_level);
       InsulinFrame.getInstance().chart.
               changechartLine(0, (double)i, 0, (double)(bg_level + increased_unit)); 
         i = i+10;
         /** Set the percentage of Battery and, Insulin and glucagon
          */
         //.getInstance().setInsulin(Insulin);
         //GlucagonReservoir.getInstance().setGlucagon(glucagon);
         //Battery.getInstance().Battery(i);
         
         
         InsulinFrame.getInstance().setJprogressBattery(i);
          try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
          // Infinite for loop
            if (i == 1440) {
                i = 0;
            }
        }

       
         notifyObservers(map);
    } 


    public void run() {
        prepareBloodSugar();
        //Give to chart to plot    
    } 
    
    public void stop()
    {
        running = false;
    }
}
