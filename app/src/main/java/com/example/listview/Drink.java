package com.example.listview;
public class Drink {
    private String name;
    private String description;
    //drinks is an array of Drinks
    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots with steamed milk"),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam"),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh")    };
    //Each Drink has a name, description, and an image resource
    private Drink(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return this.name;
    }
}
