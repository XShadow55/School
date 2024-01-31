package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Map;

public interface StudentService {
    Student add(Student student);
    Student read(Long id);
    Student set(Student student);
    void remove(Long id);
    Collection<Student> filter(int age);
    Collection<Student> filterByAgeBetween(int min, int max);
    Faculty getFaculty(Long id);
}
