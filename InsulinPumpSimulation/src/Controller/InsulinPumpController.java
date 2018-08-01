package Controller;

import View.InsulinFrame;
import Model.InsulinReservoir;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Monalisa
 */
public class InsulinPumpController {
   
    InsulinReservoir insulinReserviour;
    private Timer theChronometer;
    Thread thread;
    Simulator simulator;
   
    public InsulinPumpController()
    {
       theChronometer = new Timer(250, new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        
        }
         });
    }
    public void StartSimulation()
    {        
        simulator = new Simulator();
        thread = new Thread(simulator);
        simulator.addObserver(InsulinFrame.getInstance());
        theChronometer.start();
	thread.start();
    }
   public void StopSimulation()
   {
       try {

			simulator.stop();
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} catch (Exception ex) {

		}

   }
   
}
