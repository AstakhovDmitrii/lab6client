package com.company;

import com.company.Commands.Exist;
import com.company.Models.*;
import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static ArrayList<Command> commands = new ArrayList<>();
    public static String error = "поле введено неверно. Заменено на ";


    public static ArrayList<Pole> get_fields(Object object) {
        ArrayList<Pole> poles = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(!field.getName().equals("creationDate") && !field.getName().equals("id")) {
                    if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType() == Integer.class ||
                            field.getType() == String.class || field.getType() == Float.class || field.getType() == Long.class ||
                            field.getType() == Double.class) {
                        poles.add(new Pole(field, object));
                    } else {
                        poles.addAll(get_fields(field.get(object)));
                    }
                }
            }
        }
        catch (Exception ignored){

        }
        return poles;
    }

    public static Ticket Set_Fields() {
        Ticket product = new Ticket();//создаем перемнные
        product.setCoordinates(new Coordinates());
        product.setPerson(new Person());
        try {
            ArrayList<Pole> poles = get_fields(product);
            for (Pole pole : poles) {
                pole.getField().setAccessible(true);
                System.out.println("введите поле " + pole.getField().getName());
                while (true) {//вводим поле. Если оно одно из типов, приводим его к этому типу
                    try {
                        if (pole.getField().getType() == int.class || pole.getField().getType() == Integer.class)
                            pole.getField().set(pole.getMain(), Integer.parseInt(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == double.class || pole.getField().getType() == Double.class)
                            pole.getField().set(pole.getMain(), Double.parseDouble(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == long.class)
                            pole.getField().set(pole.getMain(), Long.parseLong(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == TicketType.class) {
                            for (TicketType TicketType : TicketType.values())
                                System.out.print("\t\t\t\t" + TicketType);
                            System.out.println();
                            pole.getField().set(pole.getMain(), TicketType.valueOf(Printer.getInstance().ReadLine()));
                        }
                        else if(pole.getField().getType() == Float.class || pole.getField().getType() == float.class)
                            pole.getField().set(pole.getMain(), Float.parseFloat(Printer.getInstance().ReadLine()));
                        else
                            pole.getField().set(pole.getMain(), pole.getField().getType().getDeclaredMethod("valueOf", Object.class)
                                    .invoke(pole.getMain(), Printer.getInstance().ReadLine()));
                        break;
                    } catch (IllegalArgumentException a) {
                        Printer.getInstance().WriteLine("введите поле " + pole.getField().getName() + " еще раз");
                    }
                }
            }
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            Printer.getInstance().WriteLine("что то поломалось " + e.getMessage());
        }
        return product;
    }


    public static void main(String[] args) throws Exception {

        String ip = "";
        int host;
        while (true){
            try {
                Printer.getInstance().WriteLine("введите ip");
                ip = Printer.getInstance().ReadLine();
                InetAddress.getByName(ip);
                Printer.getInstance().WriteLine("введите host");
                host = Integer.parseInt(Printer.getInstance().ReadLine());
                break;
            }
            catch (Exception e){
                Printer.getInstance().WriteLine("неверно ввведены значения");
            }
        }


        try {
            for (Class<? extends Command> class1 : (new Reflections("com.company.Commands")).getSubTypesOf(Command.class)) {// получаем все классы наследуемые от command
                if(class1 != Exist.class) {
                    commands.add(class1.getConstructor().newInstance());// добавляем
                }
            }
        }
        catch (Exception ignored){

        }
        Sender.Init(InetAddress.getByName(ip), host);

        while (true){
                String next = Printer.getInstance().ReadLine().trim();
                Command server_send = null;
                for (Command command : commands) {
                    if (next.startsWith(command.getName()) || next.startsWith(command.getName().toLowerCase(Locale.ROOT))) {
                        command.args = new ArrayList<>(Arrays.asList(next.split(",")));
                        command.args.remove(0);
                        command.Execute();

                        server_send = new Exist();
                        server_send.setName(command.getName());
                        server_send.args = command.args;
                    }
                }
                if(server_send == null){
                    server_send = new Exist();
                    server_send.setName(next.split(",")[0]);
                    server_send.args = new ArrayList<>(Arrays.asList(next.split(",")));
                    server_send.args.remove(0);
                }

                String send_str = Converter.getInstance().Write(server_send);
                Sender.getInstance().Send(send_str);


                String a = Sender.getInstance().Recieve();
                Writer writer = Converter.getInstance().Read(Writer.class,a);
                for (String str : writer.getResponces()) {
                    Printer.getInstance().WriteLine(str);
                }
        }
    }
}
