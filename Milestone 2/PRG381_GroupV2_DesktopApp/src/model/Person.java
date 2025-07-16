package model;

public abstract class Person {
    // Common fields
    protected int id;
    protected String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // For User (where id is String student number), add an overloaded constructor
    public Person(String id, String name) {
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            this.id = -1; // fallback if id is not integer
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    public String getDisplayInfo() {
        return "ID: " + id + ", Name: " + name;
    }
}