package ru.hogwarts.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public Faculty add(Long id, String name, String color) {
        return service.add(id, name, color);
    }
    @GetMapping("/read")
    public Map<Long, Faculty> read() {
        return service.read();
    }
    @PutMapping("{facultyId}")
    public Faculty set(@PathVariable Long facultyId,@RequestBody Long id,@RequestBody String name,@RequestBody String color) {
        return service.set(facultyId, id, name, color);
    }
    @DeleteMapping("{id}")
    public Faculty remove(@PathVariable Long id) {
        return service.remove(id);
    }
}
