package com.project.tests;

import com.project.tests.entity.Student;
import com.project.tests.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestsApplicationTests extends AbstractContainerBaseTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
		//given
		List<Student> students = List.of(
				Student.builder().firstName("bartek").lastName("tort").email("barte@gmail.com").build(),
				Student.builder().firstName("wojtek").lastName("teraz").email("wojtek@gmail.com").build()
		);

		studentRepository.saveAll(students);

		//when
		ResultActions resultActions = mockMvc.perform(get("/api/students"));

		//then
		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()",
						is(students.size())));
	}

}
