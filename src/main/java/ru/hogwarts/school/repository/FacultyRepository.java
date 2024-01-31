package ru.hogwarts.school.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findByColor(String color);
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String color, String name);


}
