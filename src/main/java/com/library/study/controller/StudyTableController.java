package com.library.study.controller;

import com.library.study.model.StudyTable;
import com.library.study.service.StudyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tables")
public class StudyTableController {

    @Autowired
    private StudyTableService tableService;

    // 1. Get all study tables
    @GetMapping
    public List<StudyTable> getAllTables() {
        return tableService.getAllTables();
    }

    // 2. Get tables by room number
    @GetMapping("/room/{roomNumber}")
    public List<StudyTable> getByRoom(@PathVariable String roomNumber) {
        return tableService.getTablesByRoom(roomNumber);
    }

    // 3. Get available (unoccupied) tables
    @GetMapping("/available")
    public List<StudyTable> getAvailableTables() {
        return tableService.getAvailableTables();
    }

    // 4. Get table by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudyTable> getById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Add new table
    @PostMapping
    public StudyTable addTable(@RequestBody StudyTable table) {
        return tableService.saveTable(table);
    }

    // 6. Assign student to a table
    @PutMapping("/{id}/assign")
    public ResponseEntity<StudyTable> assignStudent(@PathVariable Long id, @RequestParam String studentName) {
        StudyTable updated = tableService.assignStudent(id, studentName);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // 7. Free a table
    @PutMapping("/{id}/free")
    public ResponseEntity<String> freeTable(@PathVariable Long id) {
        boolean success = tableService.freeTable(id);
        if (success) {
            return ResponseEntity.ok("Table freed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found.");
        }
    }

    // 8. Get summary of tables
    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return tableService.getSummary();
    }

    // 9. Delete a table
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }


    
}
