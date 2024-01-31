package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    private long count = 0;

    @Override
    public Faculty add(Faculty faculty) {
            return facultyRepository.save(faculty);
    }

    @Override
    public Faculty read(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty set(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> filter(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String color, String name) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(color,name);
    }

    @Override
    public List<Student> getStudents(Long id) {
        return facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
    }


}
