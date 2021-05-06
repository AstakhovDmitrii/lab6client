package com.company.Commands;

import com.company.Command;
import com.company.Converter;
import com.company.Main;

public class Insert extends Command {
    @Override
    public void Execute(){
        args.add(Converter.getInstance().Write(Main.Set_Fields()));
    }
}