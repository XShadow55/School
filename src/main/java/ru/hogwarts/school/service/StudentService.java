package ru.hogwarts.school.service;

import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StudentService {
    Student add(String name, Integer age);
    Student read(Long id);
    Student set(Student student);
    void remove(Long id);
    Collection<Student> filter(int age);
    Collection<Student> filterByAgeBetween(int min, int max);
    Faculty getFaculty(Long id);

    Integer countStudent();
    Integer averageAge();
    List<Student> lastedStudent();
}
