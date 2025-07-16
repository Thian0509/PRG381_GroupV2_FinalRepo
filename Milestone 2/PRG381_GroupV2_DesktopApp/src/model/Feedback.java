package model;

import java.time.LocalDateTime;

public class Feedback {
    private int id;
    private String student;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public Feedback() {
        this.createdAt = LocalDateTime.now();
    }

    public Feedback(String student, int rating, String comment) {
        this();
        this.student = student;
        this.rating = rating;
        this.comment = comment;
    }

    public Feedback(int id, String student, int rating, String comment) {
        this(student, rating, comment);
        this.id = id;
    }

    // Business logic methods
    public boolean isPositive() {
        return rating >= 4;
    }
    
    public String getRatingDescription() {
        switch (rating) {
            case 1: return "Very Poor";
            case 2: return "Poor";
            case 3: return "Average";
            case 4: return "Good";
            case 5: return "Excellent";
            default: return "Unknown";
        }
    }

    // Encapsulation - getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    // Add this method to match GUI usage
    public String getComments() { return comment; }
    public void setComments(String comment) { this.comment = comment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}