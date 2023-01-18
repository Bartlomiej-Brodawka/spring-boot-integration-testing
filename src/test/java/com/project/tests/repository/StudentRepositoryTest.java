package com.project.tests.repository;

import com.project.tests.AbstractContainerBaseTest;
import com.project.tests.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void givenStudentObject_whenSave_thenReturnSavedStudent() {
        //given
        Student student = Student.builder().firstName("bartek").lastName("tort").email("bartek@gmail.com").build();

        //when
        Student savedStudent =studentRepository.save(student);

        //then
        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getId());
    }

    @Test
    public void givenStudentObject_whenFindById_thenReturnSavedStudent() {
        //given
        Student student = Student.builder().firstName("bartek").lastName("tort").email("bartek@gmail.com").build();
        Student savedStudent =studentRepository.save(student);

        //when
        Student student1 = studentRepository.findById(student.getId()).get();

        //then
        assertNotNull(student1);
    }
}