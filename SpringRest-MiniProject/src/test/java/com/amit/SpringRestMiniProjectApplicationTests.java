package com.amit;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class SpringRestMiniProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("EMPLOYEE SAVE TEST")
	@Order(1)
	public void testCreateEmployee() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/employee/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"empName\" : \"amityadav\", \"empSal\": 2000.0, \"empDept\" : \"DEV\" }");

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		if (!response.getContentAsString().contains("Created!")) {
			fail("Save is not working properly.");
		}
	}

	@Test
	@DisplayName("EMPLOYEE ALL TEST")
	@Order(2)
	public void testGetAllEmployees() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/all");

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

	}

	@Test
	@DisplayName("DELETE ONE EMPLOYEE")
	@Order(6)
	@Disabled
	public void testDeleteOneEmployee() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/employee/remove/{id}", 22);

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("Deleted")) {
			fail("Record id not deleted!.");
		}

	}

	@Test
	@DisplayName("EMPLOYEE DELETE NOT FOUND TEST")
	@Order(5)
	public void testEmployeeDeleteNotFound() throws Exception {

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/employee/remove/{id}", 100);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

	}

	@Test
	@DisplayName("Test EMPLOYEE GET ONE")
	@Order(3)
	public void testEmployeesGetOne() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/one/{id}", 20);

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	@DisplayName("Test EMPLOYEE GET ONE NOT FOUND")
	@Order(4)
	public void testEmployeesGetOneNotFound() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/employee/one/{id}", 200);

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());

	}
	
	@Test
	@DisplayName("EMPLOYEE UPDATE TEST")
	@Order(7)
	public void testEmployeeUpdate() throws Exception {

		// 1.Creating HttpRequest Using Mock..

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/employee/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"empId\":23,\"empName\" : \"amityadavUpdate\", \"empSal\": 1000.0, \"empDept\" : \"Devupdate\" }");

		// 2.Excecute request and Read Result
		MvcResult result = mockMvc.perform(request).andReturn();

		// 3.Read Response From Result
		MockHttpServletResponse response = result.getResponse();

		// 4.validate Response
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if (!response.getContentAsString().contains("Updated")) {
			fail("Update is not working properly.");
		}
	}

}
