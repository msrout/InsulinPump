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
public class InsulinReservoir extends Observable{
    
    public double available_amount = 100;
    private static InsulinReservoir basalReservoir = null;
    public void refill() 
    {
      available_amount = 100; 

     }
    public void setInsulin(double insulinAmount )
    {
       this.available_amount = insulinAmount;
       if ( available_amount < 10)
       {
           JOptionPane.showMessageDialog(null," Insulin amount is low!!");
       }
       setChanged();
       notifyObservers(basalReservoir);
    }
    public double availableamount()
    {
        return available_amount;
    }
    public static InsulinReservoir getInstance(){
	if(basalReservoir !=null){
	return basalReservoir;
	}else{
	basalReservoir = new InsulinReservoir();
	return basalReservoir;
	}
    }
}
