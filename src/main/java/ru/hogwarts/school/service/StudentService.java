package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Map;

public interface StudentService {
    Student add(Long id, String name, int age);
    Student read(Long id);
    Student set(Student student);
    Student remove(Long id);
    Map<Long,Student> filter(int age);
}
