package controller;

import model.Scheduler;
import model.SimulationManager;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    SimulationManager m;
    View v;
    public Controller(SimulationManager m, View v) {
        this.m=m;
        this.v=v;
        v.addListener(new AddListener());
    }


    class AddListener implements ActionListener{
        public void SimulationDate() {
            try {
                m.numberOfClients=Integer.parseInt(v.getNrClients());
                m.numberOfServers=Integer.parseInt(v.getNrQueues());
                m.minArrivalTime=Integer.parseInt(v.getMinArrival());
                m.maxArrivalTime=Integer.parseInt(v.getMaxArrival());
                m.minProcessingTime=Integer.parseInt(v.getMinService());
                m.maxProcessingTime=Integer.parseInt(v.getMaxService());
                m.simInterval=Integer.parseInt(v.getInterval());}
            catch(NumberFormatException e) {
                m.numberOfClients=0;
                m.numberOfServers=0;
                m.minArrivalTime=0;
                m.maxArrivalTime=0;
                m.minProcessingTime=0;
                m.maxProcessingTime=0;
                m.simInterval=0;

            }

            m.generateNRandomClients();

        }
        @Override
        public void actionPerformed(ActionEvent e) {

            SimulationDate();

            m.scheduler=new Scheduler(m.numberOfServers,m.maxClientsPerQueue);
            Thread t=new Thread(m);
            t.start();

        }

    }
}

