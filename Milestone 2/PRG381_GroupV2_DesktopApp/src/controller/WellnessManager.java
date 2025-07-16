package controller;

import model.Appointment;
import model.Counselor;
import model.Feedback;
import java.util.*;
import java.util.stream.Collectors;

// Singleton pattern and collections management
public class WellnessManager 
{
    private static WellnessManager instance;
    
    // Collections for managing data
    private List<Appointment> appointments;
    private List<Counselor> counselors;
    private List<Feedback> feedbackList;
    private Map<String, List<Appointment>> appointmentsByStudent;
    private Set<String> availableCounselors;
    
    private WellnessManager() 
    {
        appointments = new ArrayList<>();
        counselors = new ArrayList<>();
        feedbackList = new ArrayList<>();
        appointmentsByStudent = new HashMap<>();
        availableCounselors = new HashSet<>();
    }
    
    // Singleton pattern
    public static WellnessManager getInstance() 
    {
        if (instance == null) 
        {
            instance = new WellnessManager();
        }
        return instance;
    }
    
    // Collections management methods
    public void addAppointment(Appointment appointment) 
    {
        appointments.add(appointment);
        
        // Update appointments by student map
        String student = appointment.getStudent();
        appointmentsByStudent.computeIfAbsent(student, k -> new ArrayList<>()).add(appointment);
    }
    
    public List<Appointment> getUpcomingAppointments() 
    {
        return appointments.stream()
                .filter(Appointment::isUpcoming)
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());
    }
    
    public List<Appointment> getAppointmentsByStudent(String student) 
    {
        return appointmentsByStudent.getOrDefault(student, new ArrayList<>());
    }
    
    public void addCounselor(Counselor counselor) 
    {
        counselors.add(counselor);
        if (counselor.isAvailable()) 
        {
            availableCounselors.add(counselor.getName());
        }
    }
    
    public Set<String> getAvailableCounselors() 
    {
        return new HashSet<>(availableCounselors);
    }
    
    public void addFeedback(Feedback feedback) 
    {
        feedbackList.add(feedback);
    }
    
    public List<Feedback> getPositiveFeedback() 
    {
        return feedbackList.stream()
                .filter(Feedback::isPositive)
                .collect(Collectors.toList());
    }
    
    public double getAverageRating() 
    {
        return feedbackList.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);
    }
    
    // Getters for collections
    public List<Appointment> getAllAppointments() { return new ArrayList<>(appointments); }
    public List<Counselor> getAllCounselors() { return new ArrayList<>(counselors); }
    public List<Feedback> getAllFeedback() { return new ArrayList<>(feedbackList); }
}