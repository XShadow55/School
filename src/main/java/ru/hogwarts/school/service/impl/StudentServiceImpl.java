package ru.hogwarts.school.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }





    @Override
    public Student add(String name, Integer age) {
        Student student = new Student(name,age);
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

    @Override
    public Collection<Student> filterByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min,max);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }


    @Override
    public Integer countStudent() {
        return studentRepository.countStudent();
    }
    @Override
    public Integer averageAge() {
        return studentRepository.averageAge();
    }
    @Override
    public List<Student> lastedStudent() {
        return studentRepository.lastedStudent();
    }
}
