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


    @GetMapping
    public List<StudyTable> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/room/{roomNumber}")
    public List<StudyTable> getByRoom(@PathVariable String roomNumber) {
        return tableService.getTablesByRoom(roomNumber);
    }

    @GetMapping("/available")
    public List<StudyTable> getAvailableTables() {
        return tableService.getAvailableTables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyTable> getById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudyTable addTable(@RequestBody StudyTable table) {
        return tableService.saveTable(table);
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<StudyTable> assignStudent(@PathVariable Long id,
                                                    @RequestParam String studentName) {
        StudyTable updated = tableService.assignStudent(id, studentName);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/free")
    public ResponseEntity<String> freeTable(@PathVariable Long id) {
        boolean success = tableService.freeTable(id);
        return success
                ? ResponseEntity.ok("Table freed successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Table not found.");
    }

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return tableService.getSummary();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<StudyTable> updateTable(@PathVariable Long id,
                                                  @RequestBody StudyTable table) {
        StudyTable updated = tableService.updateTable(id, table);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

   
    @PostMapping("/bulk")
    public List<StudyTable> addTables(@RequestBody List<StudyTable> tables) {
        return tableService.saveTables(tables);
    }

 
    @PutMapping("/free-bulk")
    public ResponseEntity<String> freeTables(@RequestBody List<Long> ids) {
        int freed = tableService.freeTables(ids);
        return ResponseEntity.ok(freed + " table(s) freed.");
    }


    @GetMapping("/status/{status}")
    public List<StudyTable> getTablesByStatus(@PathVariable String status) {
        return tableService.getTablesByStatus(status);
    }


    @GetMapping("/student/{studentName}")
    public List<StudyTable> getTablesByStudent(@PathVariable String studentName) {
        return tableService.getTablesByStudent(studentName);
    }
}
