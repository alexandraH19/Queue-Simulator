package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private int maxCapacity;

    public Server(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        clients=new ArrayBlockingQueue<>(maxCapacity);
        waitingPeriod=new AtomicInteger(0);
    }

    public void addTask(Client newClient) {
        clients.add(newClient);
        waitingPeriod.getAndAdd(newClient.gettService());

    }

    @Override
    public void run() {
        while(true) {

            if(!clients.isEmpty()) {
                Client cl1=clients.peek();
                try {
                    Thread.sleep(1000);
                    waitingPeriod.getAndDecrement();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cl1.settService(cl1.gettService()-1);
                if(cl1.gettService()==0){
                    clients.remove();
                }
            }
        }
    }



    public int getWaitingPeriod() {
        return waitingPeriod.get();
    }


    public Client[] getClients() {
        Client [] cl= new Client[maxCapacity];
        int j=0;
        for(Client i: clients) {
            cl[j]=i;
            j=j+1;
        }
        return cl;
    }

}

