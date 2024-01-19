package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Map;

public interface FacultyService {
    Faculty add(Long id,String name,String color);
    Map<Long,Faculty> read();
    Faculty set(Long facultyId,Long id,String name,String color);
    Faculty remove(Long id);

}
