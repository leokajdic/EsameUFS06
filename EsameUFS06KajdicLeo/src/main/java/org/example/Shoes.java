package org.example;

public class Shoes {
int id;
String name;
double price;
String genre;

    public Shoes(int id, String name, double price, String genre) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }
}
