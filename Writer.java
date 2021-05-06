package com.company.Models;

import java.util.ArrayList;

public class Writer {
    public ArrayList<String> responces;

    public ArrayList<String> getResponces() {
        return responces;
    }

    public void setResponces(ArrayList<String> responces) {
        this.responces = responces;
    }
    public Writer(){
        responces = new ArrayList<>();
    }

    public Writer(ArrayList<String> strings){
        this.responces = strings;
    }
}
