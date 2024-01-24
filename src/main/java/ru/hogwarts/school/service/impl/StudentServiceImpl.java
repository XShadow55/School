package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }





    @Override
    public Student add(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student read(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student set(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {


        studentRepository.deleteById(id);
    }
    public Collection<Student> filter(int age) {
        return studentRepository.findByAge(age);
    }
}
