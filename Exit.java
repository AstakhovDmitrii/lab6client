package com.company.Commands;

import com.company.Command;

public class Exit extends Command {
    @Override
    public void Execute() throws Exception {
        System.exit(0);
    }
}
