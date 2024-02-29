package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }





    @Override
    public Student add(String name, Integer age) {
        logger.info("Was invoked method for create student");
        Student student = new Student(name,age);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student read(Long id) {
        logger.info("The method for reading students was called");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student set(Student student) {
        logger.info("A method was called to change the student");
        return studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        logger.info("A method was called to remove a student");
        studentRepository.deleteById(id);
    }
    public Collection<Student> filter(int age) {
        logger.info("A method was called to filter students");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> filterByAgeBetween(int min, int max) {
        logger.info("A method was called to filter students");
        return studentRepository.findByAgeBetween(min,max);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("A method was called to filter by student age");
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }


    @Override
    public Integer countStudent() {
        logger.info("A method was called to count the number of students");
        return studentRepository.countStudent();
    }
    @Override
    public Integer averageAge() {
        logger.info("A method was called to find the average age of students");
        return studentRepository.averageAge();
    }
    @Override
    public List<Student> lastedStudent() {
        logger.info("A method was called to call the last students");
        return studentRepository.lastedStudent();
    }

    @Override
    public List<Student> listStudent() {
        return studentRepository.findAll().stream().filter(student -> student.getName().toLowerCase().toCharArray()[0]=='a' || student.getName().toLowerCase().toCharArray()[0]=='а' ).toList();
    }

    @Override
    public Double averageAgeStudent() {
        return studentRepository.findAll().stream().mapToInt(Student::getAge).average().orElse(0);
    }
}
