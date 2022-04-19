package model;

import controller.Controller;
import view.View;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class SimulationManager implements Runnable {

    public int maxClientsPerQueue =50;
    public int maxProcessingTime=10;
    public int minProcessingTime=2;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers=3;
    public int numberOfClients=100;
    public int simInterval;
    public float averageWaitingTime=0;
    public float averageServiceTime=0;
    public Scheduler scheduler;
    int nrMaxClientsInQueue=0;
    private ArrayList<Client> generatedClients=new ArrayList<Client>();
    static View v;
    private FileWriter fr ;
    private PrintWriter pr;
    private String outputF="C:\\Users\\hagym\\Desktop\\outFile.txt";

    public SimulationManager() {
        v=new View();
        Controller c=new Controller(this,v);
    }



    public void generateNRandomClients() {
        Client cl;
        ClientCompare compare=new ClientCompare();
        Random r=new Random();
        for(int i=0;i<numberOfClients;i++) {
            int difProcessingTime=maxProcessingTime-minProcessingTime;
            int difArrivalTime=maxArrivalTime-minArrivalTime;
            int processingTime=minProcessingTime+r.nextInt(difProcessingTime);
            int arrivalTime=minArrivalTime+r.nextInt(difArrivalTime);
            System.out.println(i + 1 + " " +  arrivalTime + "  " +  processingTime);
            cl=new Client(i+1,arrivalTime,processingTime);
            generatedClients.add(cl);
            Collections.sort(generatedClients,compare);

        }
    }

    @Override
    public void run() {
        int currentTime=0;
        try {
            fr=new FileWriter(outputF);
            pr= new PrintWriter(fr);

            while(currentTime<simInterval) {

                for(int i=0;i<generatedClients.size();i++) {

                    this.averageServiceTime=(float)(this.averageServiceTime+this.generatedClients.get(i).gettService());

                    if(generatedClients.get(i).gettArrival()==currentTime) {
                        scheduler.dispatchClient(generatedClients.get(i));
                        generatedClients.remove(i);
                        i--;

                    }

                }


                System.out.println("Time:"+currentTime);
                pr.println("\nTime:"+currentTime);
                pr.print("Waiting clients:");
                for(int i=0;i<generatedClients.size();i++)
                    pr.print(generatedClients.get(i) + " ");
                for(int i = 0; i < scheduler.getServers().size();i++) {



                    int clientsPerCoada=scheduler.getServers().get(i).getClients().length;
                    if(clientsPerCoada>nrMaxClientsInQueue) {
                        nrMaxClientsInQueue=clientsPerCoada;

                    }


                    pr.print("\nQueue " + i + ": ");
                    if(scheduler.getServers().get(i).getClients().length == 0) {
                        pr.print("closed\n");
                    }
                    else {
                        for(int j = 0; j < scheduler.getServers().get(i).getClients().length;j++) {
                            pr.print( " " + scheduler.getServers().get(i).getClients()[j]);
                        }

                    }
                }


                averageWaitingTime=scheduler.getServiceTotal();

                pr.flush();


                currentTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        this.averageServiceTime=(float)this.averageServiceTime/numberOfClients;
        this.averageWaitingTime=(float)this.averageWaitingTime/numberOfClients;
        pr.println("\nAverage Service Time:" + averageServiceTime);
        pr.println("\nAverage Waiting Time:" +averageWaitingTime);
        pr.println("\nPeak hour:"+ nrMaxClientsInQueue);
        pr.flush();
        pr.close();

    }
    public static void main(String[] args) {

        SimulationManager m = new SimulationManager();

    }




}

