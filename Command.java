package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {// интерфей который обьединяет все команды

    public abstract void Execute() throws Exception;//метод для вызова команды

    public String getName(){
        return name;
    }

    private String name;

    public List<String> args = new ArrayList<>();

    public Command(){
        name = getClass().getSimpleName();
    }

    public void setName(String name) {
        this.name = name;
    }
}
