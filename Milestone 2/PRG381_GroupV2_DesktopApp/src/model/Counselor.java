package model;

public class Counselor extends Person {
    private String specialization;
    private boolean available;

    public Counselor(int id, String name, String specialization, boolean available) {
        super(id, name);
        this.specialization = specialization;
        this.available = available;
    }
    
    public Counselor(String name, String specialization, boolean available) {
    super(-1, name); 
    this.specialization = specialization;
    this.available = available;
}

    public String getSpecialization() {
        return specialization;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getRole() {
        return "Counselor";
    }

    @Override
    public String getDisplayInfo() {
        return super.getDisplayInfo() + ", Specialization: " + specialization +
                ", Available: " + (available ? "Yes" : "No");
    }
}