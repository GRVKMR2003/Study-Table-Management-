package com.library.study.service;

import com.library.study.model.StudyTable;
import com.library.study.repository.StudyTableRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudyTableServiceImpl implements StudyTableService {

    @Autowired
    private StudyTableRepository repo;

    // 1. Get all study tables
    @Override
    public List<StudyTable> getAllTables() {
        return repo.findAll();
    }

    // 2. Get tables by room number
    @Override
    public List<StudyTable> getTablesByRoom(String roomNumber) {
        return repo.findByRoomNumber(roomNumber);
    }

    // 3. Get available tables
    @Override
    public List<StudyTable> getAvailableTables() {
        return repo.findByStatus("AVAILABLE");
    }

    // 4. Get table by ID
    @Override
    public Optional<StudyTable> getTableById(Long id) {
        return repo.findById(id);
    }

    // 5. Save new table
    @Override
    public StudyTable saveTable(StudyTable table) {
        if (table.getStatus() == null) table.setStatus("AVAILABLE");
        return repo.save(table);
    }

    // 6. Delete table
    @Override
    public void deleteTable(Long id) {
        repo.deleteById(id);
    }

    // 7. Assign student to table
    @Override
    public StudyTable assignStudent(Long id, String studentName) {
        return repo.findById(id).map(table -> {
            table.setAssignedStudent(studentName);
            table.setStatus("OCCUPIED");
            return table;
        }).orElse(null);
    }

    // 8. Free a table
    @Override
    public boolean freeTable(Long id) {
        return repo.findById(id).map(table -> {
            table.setAssignedStudent(null);
            table.setStatus("AVAILABLE");
            return true;
        }).orElse(false);
    }

    // 9. Summary
    @Override
    public Map<String, Long> getSummary() {
        long total = repo.count();
        long available = repo.countByStatus("AVAILABLE");
        long occupied = total - available;
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("TOTAL", total);
        summary.put("AVAILABLE", available);
        summary.put("OCCUPIED", occupied);
        return summary;
    }

    // 10. Update a table
    @Override
    public StudyTable updateTable(Long id, StudyTable incoming) {
        return repo.findById(id).map(existing -> {
            copyNonNullProperties(incoming, existing);
            return existing;
        }).orElse(null);
    }

    // 11. Bulk insert tables
    @Override
    public List<StudyTable> saveTables(List<StudyTable> tables) {
        tables.forEach(t -> {
            if (t.getStatus() == null) t.setStatus("AVAILABLE");
        });
        return repo.saveAll(tables);
    }

    // 12. Bulk free tables
    @Override
    public int freeTables(List<Long> ids) {
        List<StudyTable> tables = repo.findAllById(ids);
        tables.forEach(t -> {
            t.setAssignedStudent(null);
            t.setStatus("AVAILABLE");
        });
        return tables.size();
    }

    // 13. Get tables by status
    @Override
    public List<StudyTable> getTablesByStatus(String status) {
        return repo.findByStatus(status.toUpperCase());
    }

    // 14. Get tables by student name
    @Override
    public List<StudyTable> getTablesByStudent(String studentName) {
        return repo.findByAssignedStudent(studentName);
    }















































































































































































































































































































































































































































































































































































































































































































































































































































































































































    
    // Utility to ignore null properties during copy
    private void copyNonNullProperties(Object src, Object target) {
        BeanWrapperImpl srcWrapper = new BeanWrapperImpl(src);
        BeanWrapperImpl trgWrapper = new BeanWrapperImpl(target);

        for (var pd : srcWrapper.getPropertyDescriptors()) {
            String propName = pd.getName();
            Object value = srcWrapper.getPropertyValue(propName);
            if (value != null && trgWrapper.isWritableProperty(propName)) {
                trgWrapper.setPropertyValue(propName, value);
            }
        }
    }
}
