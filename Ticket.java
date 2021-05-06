package com.company.Models;

import com.company.Main;

import java.time.ZonedDateTime;

public class Ticket implements Comparable<Ticket>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    public Ticket() {
        creationDate = ZonedDateTime.now();

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "\n\tid=" + id +
                ",\n\tname='" + name + '\'' +
                ",\n\tcoordinates=" + coordinates +
                ",\n\tcreationDate=" + creationDate +
                ",\n\tprice=" + price +
                ",\n\ttype=" + type +
                ",\n\tperson=" + person +
                "\n}";
    }


    public Ticket(Integer id, String name, Coordinates coordinates, float price, TicketType type, Person person) {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = ZonedDateTime.now();
        setPrice(price);
        setType(type);
        setPerson(person);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            Printer.getInstance().WriteLine(Main.error + "name");
            name = "name";
        }
        if(name.equals("")){
            Printer.getInstance().WriteLine(Main.error + "name");
            name = "name";
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if(coordinates == null){
            Printer.getInstance().WriteLine(Main.error + "new");
            coordinates = new Coordinates();
        }

        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if(creationDate == null){
            Printer.getInstance().WriteLine(Main.error + "new");
            creationDate = ZonedDateTime.now();
        }
        this.creationDate = creationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if(price <= 0){
            Printer.getInstance().WriteLine(Main.error + "011");
            price = 0;
        }
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if(person == null){
            Printer.getInstance().WriteLine(Main.error + "01");
            person = new Person();
        }
        this.person = person;
    }

    @Override
    public int compareTo(Ticket ticket) {
        return ticket.getName().length() - name.length();
    }
}