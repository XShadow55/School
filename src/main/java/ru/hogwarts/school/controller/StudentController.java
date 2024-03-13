package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    // Создание студента
    @PostMapping
    public Student add(@RequestParam String name,@RequestParam Integer age) {
        return service.add( name,  age);
    }
    // Получение студента
    @GetMapping("{id}")
    public ResponseEntity<Student> read(@PathVariable Long id) {
        Student student = service.read(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    // Изменение данных по студенту
    @PutMapping
    public ResponseEntity<Student>  set(@RequestBody Student student) {
        Student foundStudent = service.set(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    // Удаление студента
    @DeleteMapping("{id}")
    public  ResponseEntity<Object> remove(@PathVariable Long  id) {
        service.remove(id);
        return ResponseEntity.ok().build();
    }
    // Применение фильтра для студентов
    @GetMapping
    public ResponseEntity<Collection<Student>> filterStudents(@RequestParam int age) {
        if (age > 0) {
            return ResponseEntity.ok(service.filter(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    // Получение студентов по заданному диапазону возроста
    @GetMapping("find")
    public  Collection<Student> findByAgeBetween(@RequestParam int min,@RequestParam int max){
        return service.filterByAgeBetween(min,max);
    }
    // Получение факультета на котором учиться студент
    @GetMapping("{id}/faculty")
    public Faculty getFaculty(@PathVariable Long  id) {
        return service.getFaculty(id);
    }
    // Средний возрост
    @GetMapping("avarage")
    public Integer averageAge() {
        return service.averageAge();
    }
    // Количество студентов
    @GetMapping("countStudent")
    public Integer countStudent() {
        return service.countStudent();
    }
    // Получение 5 последних студентов
    @GetMapping("lastedStudent")
    public List<Student> lastedStudent() {
        return service.lastedStudent();
    }
    // Получение списка студентов сортированных по первой букве имени как русских так и английских
    @GetMapping("findFilerStudent")
    public  Collection<Student> findByListStudent(){
        return service.listStudent();
    }
    @GetMapping("avarage_age")
    public Double averageAgeStudent() {
        return service.averageAgeStudent();
    }
    // Использование паралельных стримов
    @GetMapping("print-parallel")
    public  void potok(){
        List<Student> main = service.allStudent();
        System.out.println(main.get(0).getName());
        System.out.println(main.get(1).getName());
        
        new Thread(() -> {
            System.out.println(main.get(2).getName());
            System.out.println(main.get(3).getName());
        }).start();
        
        new Thread(() -> {
            System.out.println(main.get(4).getName());
            System.out.println(main.get(5).getName());
        }).start();
    }
    // Использование синхронизированных паралельных стримов
    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        List<Student> students = service.allStudent();
        synchronizedPrint(students.get(0).getName());
        synchronizedPrint(students.get(1).getName());

        new Thread(() -> {
            synchronizedPrint(students.get(2).getName());
            synchronizedPrint(students.get(3).getName());
        }).start();

        new Thread(() -> {
            synchronizedPrint(students.get(4).getName());
            synchronizedPrint(students.get(5).getName());
        }).start();
    }
// Синхронизированный вывод сообщения
    private synchronized void synchronizedPrint(String message) {
        System.out.println(message);
    }
}


