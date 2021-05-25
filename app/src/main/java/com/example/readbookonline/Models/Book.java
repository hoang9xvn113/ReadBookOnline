package com.example.readbookonline.Models;

public class Book {
    private int Id;
    private String Name;
    private String Author;
    private String Status;
    private String Amount;
    private int Img;

    public Book(int id, String amount) {
        Id = id;
        Amount = amount;
    }

    public Book(int id, String name, int img) {
        Id = id;
        Name = name;
        Img = img;
    }

    public Book(int id, String name, String author, String status, String amount, int img) {
        Id = id;
        Name = name;
        Author = author;
        Status = status;
        Amount = amount;
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
