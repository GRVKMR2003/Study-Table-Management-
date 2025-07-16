package com.library.study.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudyTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private int tableNumber;

    @Column(nullable = false)
    private boolean isAvailable;

    private boolean occupied;

    private String assignedStudent;

    // Constructors
    public StudyTable() {}

    public StudyTable(String roomNumber, int tableNumber, boolean isAvailable, String assignedStudent) {
        this.roomNumber = roomNumber;
        this.tableNumber = tableNumber;
        this.isAvailable = isAvailable;
        this.assignedStudent = assignedStudent;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getAssignedStudent() {
        return assignedStudent;
    }

    public void setAssignedStudent(String assignedStudent) {
        this.assignedStudent = assignedStudent;
    }
}
