package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static javax.management.Query.in;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return service.add(faculty);
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> read(@PathVariable Long id) {
        Faculty faculty = service.read(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> set(@RequestBody Faculty faculty) {
        Faculty foundFaculty = service.set(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> filterFaculty(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(service.filter(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping("get")
    public Collection<Faculty> getByNameIgnoreCaseOrColorIgnoreCase(@RequestParam String color,@RequestParam String name) {
        return service.findByNameIgnoreCaseOrColorIgnoreCase(color,name);
    }
    @GetMapping("{id}/faculty")
    public Collection<Student> getStudents(@PathVariable Long id){
        return service.getStudents(id);
    }
}
