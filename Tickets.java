package com.company.Models;

import org.simpleframework.xml.ElementMap;

import java.util.TreeMap;

public class Tickets {
    @ElementMap(entry="tickets", key="key", attribute=true, inline=true)
    private TreeMap<String,Ticket> tickets;// массив у которого элемент получаем через ключ

    public TreeMap<String,Ticket>  getTickets() {
        return tickets;
    }

    public void setTickets(TreeMap<String,Ticket> tickets1 ) {
        tickets = tickets1;
    }

    public Tickets() {
        tickets  = new TreeMap<>();
    }
}
