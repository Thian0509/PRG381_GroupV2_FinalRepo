package model;

public class User extends Person {
    private String studentNumber; // maps to student_number
    private String surname;
    private String email;
    private String phone;
    private String password;

    public User() {
        super(-1, ""); // default values
    }

    public User(String studentNumber, String name, String surname, String email, String phone, String password) {
        super(studentNumber, name); // calls String id constructor in Person
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String getDisplayInfo() {
        return "Student Number: " + studentNumber + ", Name: " + name + " " + surname +
                ", Email: " + email + ", Phone: " + phone;
    }

    // If you need to access studentNumber as ID, override getId:
    @Override
    public int getId() {
        try {
            return Integer.parseInt(studentNumber);
        } catch (NumberFormatException e) {
            return -1; // or use a different logic if needed
        }
    }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}