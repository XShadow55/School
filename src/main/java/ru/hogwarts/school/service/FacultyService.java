package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Map;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty read(Long id);
    Faculty set(Faculty faculty);
    void remove(Long id);
    Collection<Faculty> filter(String color);

}
