package model;

import java.util.Comparator;

public class ClientCompare implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {

        return Integer.valueOf(o1.gettArrival()).compareTo(o2.gettArrival());
    }

}
