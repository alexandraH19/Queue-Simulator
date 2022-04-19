package model;


public class Client {
    private int ID;
    private int tArrival;
    private int tService;

    public Client(int ID, int tArrival, int tService) {
        this.ID=ID;
        this.tArrival=tArrival;
        this.tService=tService;

    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }

    @Override
    public String toString() {
        return "(" + ID + ";" + tArrival + ";" + tService + ")";
    }
}

