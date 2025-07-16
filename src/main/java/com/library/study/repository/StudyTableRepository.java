package com.library.study.repository;

import com.library.study.model.StudyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudyTableRepository extends JpaRepository<StudyTable, Long> {
    List<StudyTable> findByRoomNumber(String roomNumber);
    List<StudyTable> findByIsAvailableTrue(); // relies on getIsAvailable()
}
