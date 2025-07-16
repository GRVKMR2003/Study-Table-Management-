package com.library.study.service;

import com.library.study.model.StudyTable;
import com.library.study.repository.StudyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Transactional
@Service
public class StudyTableService {

    @Autowired
    private StudyTableRepository tableRepository;

    // 1. Get all tables
    public List<StudyTable> getAllTables() {
        return tableRepository.findAll();
    }

    // 2. Get table by ID
    public Optional<StudyTable> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    // 3. Get tables by room number
    public List<StudyTable> getTablesByRoom(String roomNumber) {
        return tableRepository.findByRoomNumber(roomNumber);
    }

    // 4. Get only available tables
    public List<StudyTable> getAvailableTables() {
        return tableRepository.findByIsAvailableTrue();
    }

    // 5. Save a new or updated table
    public StudyTable saveTable(StudyTable table) {
        if (table.getIsAvailable() == null) {
            table.setIsAvailable(true); // default availability if not set
        }
        return tableRepository.save(table);
    }

    // 6. Assign student to a table
    public StudyTable assignStudent(Long id, String studentName) {
        Optional<StudyTable> optionalTable = tableRepository.findById(id);
        if (optionalTable.isPresent()) {
            StudyTable table = optionalTable.get();
    
            // Prevent assigning if already occupied
            if (!table.getIsAvailable()) {
                System.out.println("Table " + id + " is already occupied.");
                return null;
            }
    
            table.setAssignedStudent(studentName);
            table.setIsAvailable(false);  // Mark as unavailable
            table.setOccupied(true);      // Mark as occupied ✅
    
            return tableRepository.save(table);
        }
        return null;
    }
    
 // 7. Free a table
public boolean freeTable(Long id) {
    Optional<StudyTable> optionalTable = tableRepository.findById(id);
    if (optionalTable.isPresent()) {
        StudyTable table = optionalTable.get();
        table.setAssignedStudent(null);
        table.setIsAvailable(true); // mark table as available again
        tableRepository.save(table);
        return true;
    }
    return false;
}

// 8. Delete a table
public void deleteTable(Long id) {
    tableRepository.deleteById(id);
}


    // 9. Summary: total, available, occupied
    public Map<String, Long> getSummary() {
        List<StudyTable> all = tableRepository.findAll();
        long total = all.size();
        long available = all.stream().filter(StudyTable::getIsAvailable).count();
        long occupied = total - available;

        Map<String, Long> summary = new HashMap<>();
        summary.put("total", total);
        summary.put("available", available);
        summary.put("occupied", occupied);

        return summary;
    }
}
