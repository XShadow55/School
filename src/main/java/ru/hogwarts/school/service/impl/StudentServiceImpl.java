package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private  Long count = 0L;



    @Override
    public Student add(Long id, String name, int age) {
        Student student = new Student(id,name,age);
        count = count+1;
        studentMap.put(count,student);
        return student;
    }

    @Override
    public Student read(Long id) {
        return studentMap.get(id);
    }

    @Override
    public Student set(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            return null;
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student remove(Long id) {
        Student student = studentMap.get(id);
        studentMap.remove(id);
        return student;
    }
    @Override
    public Map<Long, Student> filter(int age) {
        return (Map<Long, Student>) studentMap.values().stream().filter(student -> student.getAge() == age);
    }
}
