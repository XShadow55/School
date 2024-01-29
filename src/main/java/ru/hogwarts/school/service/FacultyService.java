package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty read(Long id);
    Faculty set(Faculty faculty);
    void remove(Long id);
    Collection<Faculty> filter(String color);
    Collection<Faculty> findByNameOrColorIgnoreCase(String color, String name);
    List<Student> findStudents(int id);
}
