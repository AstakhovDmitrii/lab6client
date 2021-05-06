package com.company.Models;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Printer {
    private static PrintStream writer;
    private static Scanner scanner;

    private static Printer instance;

    private Printer(InputStream reader, PrintStream Writer) {
        writer = Writer;
        scanner = new Scanner(reader, StandardCharsets.UTF_8);
    }

    public static Printer getInstance() {
        if(instance == null){
            instance = new Printer(System.in, System.out);
        }
        return instance;
    }
    public static void Init(InputStream reader, PrintStream Writer){
        instance = new Printer(reader, Writer);
    }
    public String ReadLine(){
        return scanner.nextLine();
    }
    public void WriteLine(Object str){
        try {
            byte[] buffer = (str + "\r\n").getBytes(StandardCharsets.UTF_8);
            writer.write(buffer);
        }
        catch (IOException e){
            System.out.println("вывод прерван. Сообщение выведенно в стандартный поток\r\n");
        }
    }
}
