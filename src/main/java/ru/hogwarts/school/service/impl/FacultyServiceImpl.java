package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> listMap = new HashMap<>();
    private Long count = 0l;

    @Override
    public Faculty add(Long id, String name, String color) {
        Faculty faculty = new Faculty(id,name,color);
        count = count+1;
        listMap.put(count,faculty);
        return faculty;
    }

    @Override
    public Map<Long, Faculty> read() {
        return listMap;
    }

    @Override
    public Faculty set(Long facultyId ,Long id, String name, String color) {
        if (id != null) {
            listMap.get(facultyId).setId(id);
        }
        if (name != null) {
            listMap.get(facultyId).setName(name);
        }
        if (color != null) {
            listMap.get(facultyId).setColor(color);
        }
        return listMap.get(facultyId);
    }

    @Override
    public Faculty remove(Long id) {
        Faculty faculty = listMap.get(id);
        listMap.remove(id);
        return faculty;
    }
}
