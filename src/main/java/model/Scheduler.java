package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List <Server> servers;

    private int maxNoServers;
    private int maxClientsPerServer;
    private int serviceTotal;

    public Scheduler(int maxNoServers,int maxClientsPerServer) {


       this.maxNoServers=maxNoServers;
       this.maxClientsPerServer=maxClientsPerServer;
        System.out.println(maxClientsPerServer);
        servers=new ArrayList <Server>(maxNoServers);
        for(int i=0;i<maxNoServers;i++) {
            Server s=new Server(maxClientsPerServer);
            servers.add(s);
            Thread t=new Thread(s);
            t.start();
        }
    }

    public void dispatchClient(Client cl) {
        int minTime=Integer.MAX_VALUE;
        int minIndex = 0;

        int i;
        for(i=0;i<servers.size();i++) {
            if(servers.get(i).getWaitingPeriod()<minTime) {
                minTime= servers.get(i).getWaitingPeriod();
                minIndex=i;
            }

        }

        serviceTotal=serviceTotal+cl.gettService();

        servers.get(minIndex).addTask(cl);
    }


    public List<Server> getServers(){
        return servers;
    }

    public int getServiceTotal() {
        return serviceTotal;
    }


}

