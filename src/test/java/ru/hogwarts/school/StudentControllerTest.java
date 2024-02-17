package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.impl.AvatarServiceImpl;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;
    @MockBean
    private AvatarServiceImpl avatarService;
    @MockBean
    private FacultyServiceImpl facultyService;
    @MockBean
    private StudentRepository studentRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();




    @Test
    public void add() throws Exception{
        String name = "asdf";
        int age = 10;
        Long id = 1L;
        Student studentObject = new Student(name,age);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentService.add(studentObject.getName(),studentObject.getAge())).thenReturn(student);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student.toString())
                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));

    }


    @Test
    void read() throws Exception {
        final String name = "asdf";
        final int age = 10;
        final long id = 1;


        Student student = new Student();

        student.setName(name);
        student.setAge(age);

        when(studentService.read(id)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andDo(print());

    }

    @Test
    void set() throws Exception {
        final String name = "asdf";
        final int age = 10;
        final Long id = 1L;

        Student student = new Student(name,age);
        Student studentTest = new Student("as",30);

        when(studentService.set(student)).thenReturn(studentTest);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.name").value(studentTest.getName()))
                .andExpect(jsonPath("$.age").value(studentTest.getAge()));
    }

    @Test
    void remove() throws Exception {
        final String name = "asdf";
        final int age = 10;
        final Long id = 1L;

        Student student = new Student(name,age);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }


    @Test
    void getFaculty() throws Exception {
        final String name = "asdf";
        final int age = 10;
        final Long id = 1L;

        Student student = new Student();
        student.getFaculty().setId(id);

        when(studentService.getFaculty(id)).thenReturn(student.getFaculty());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}/faculty",id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.faculty_id").value(id));


    }
}