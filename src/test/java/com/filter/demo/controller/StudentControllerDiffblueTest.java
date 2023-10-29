package com.filter.demo.controller;

import static org.mockito.Mockito.when;

import com.filter.demo.model.Student;
import com.filter.demo.repo.StudentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerDiffblueTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentRepo studentRepo;

    /**
     * Method under test: {@link StudentController#addStudent(Student)}
     */
    @Test
    void testAddStudent() throws Exception {
        Student student = new Student();
        student.setAddress("42 Main St");
        student.setHobby("Hobby");
        student.setId(1L);
        student.setName("Name");
        when(studentRepo.save(Mockito.<Student>any())).thenReturn(student);

        Student student2 = new Student();
        student2.setAddress("42 Main St");
        student2.setHobby("Hobby");
        student2.setId(1L);
        student2.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(student2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\",\"hobby\":\"Hobby\"}"));
    }
}

