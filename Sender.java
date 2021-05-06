package com.company;

import com.company.Models.Printer;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sender {

    private static Sender instance;
    public static DatagramChannel socket;
    public static InetAddress address_server;
    public static int port_server;

    private Sender(InetAddress address, int port) {
        try {
            address_server = address;
            socket = DatagramChannel.open();
            port_server = port;
            socket.socket().bind(new InetSocketAddress(port));
        }
        catch (Exception ignored){
            System.out.println("1");
        }
    }

    public static Sender getInstance() {
        if(instance == null){
            try { Init(InetAddress.getByName("192.168.5.1"), 1111); }
            catch(UnknownHostException ignored){} catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public static void Init(InetAddress address_server, int port_server) throws IOException {
        instance = new Sender(address_server ,port_server);
    }
    public String Recieve(){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            socket.receive(buffer);
            return new String(buffer.array(), StandardCharsets.UTF_16);
        }
        catch (IOException ignored){
            Printer.getInstance().WriteLine("ошибка принятия сообщения" + ignored.getMessage());
            return "";
        }
    }

    public void Send(String send_string) throws IOException {
        byte[] buff = send_string.getBytes(StandardCharsets.UTF_16);
        ByteBuffer buffer = ByteBuffer.wrap(buff);
        socket.send(buffer, new InetSocketAddress(address_server, port_server + 1));
    }

}
