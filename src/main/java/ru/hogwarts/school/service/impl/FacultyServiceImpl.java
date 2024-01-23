package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> listMap = new HashMap<>();
    private long count = 0;

    @Override
    public Faculty add(Faculty faculty) {

        count = count+1;
        listMap.put(count,faculty);
        return faculty;
    }

    @Override
    public Faculty read(Long id) {
        return listMap.get(id);
    }

    @Override
    public Faculty set(Faculty faculty) {
        if (!listMap.containsKey(faculty.getId())) {
            return null;
        }
        listMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty remove(Long id) {
        Faculty faculty = listMap.get(id);
        listMap.remove(id);
        return faculty;
    }
    public Collection<Faculty> filter(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : listMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }


}
