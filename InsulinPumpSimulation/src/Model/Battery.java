/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author Monalisa
 */
public class Battery  extends Observable{
    
    public double available_amount = 100;
    private static Battery battery = null;
    public void refill() 
    {
      available_amount = 100; 

     }
    public void Battery(double Amount )
    {
       this.available_amount = Amount;
       
       setChanged();
       notifyObservers(battery);
    }
    public double availableamount()
    {
        return available_amount;
    }
    public static Battery getInstance(){
	if(battery !=null){
	return battery;
	}else{
	battery = new Battery();
	return battery;
	}
    }

}
