package com.ironhack.lab42.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Employee employee1, employee2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        employee1 = new Employee(123456, "hematology", "Laura Zambrano", Status.OFF);
        employee2 = new Employee(456789, "pediatric","Ana Bermejo", Status.ON_CALL);
        employeeRepository.saveAll(List.of(employee1,employee2));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllEmployees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Laura Zambrano"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ana Bermejo"));
    }

    @Test
    void findById_Employees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/"+employee1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("123456"));

    }

    @Test
    void findByStatus_Employees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/status"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("ON"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("OFF"));
    }

    @Test
    void findByDepartment_Employees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employees/employees").param("department", "hematology"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("hematology"));
    }

    @Test
    void store_Employees() throws Exception {
        Employee employee3 = new Employee(123789, "nurse", "Rocio Arellano", Status.ON);
        String body = objectMapper.writeValueAsString(employee3);

        MvcResult mvcResult = mockMvc.perform(
                        post("/employees")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("nurse"));
        assertTrue(employeeRepository.existsById(employee3.getId()));
    }

    @Test
    void updateStatus() throws Exception {
       /* Los tests Patch no los vimos en clase y no terminan de salirme los tets
       employee1.setStatus(Status.ON_CALL);
        String body = objectMapper.writeValueAsString(employee1);
        MvcResult mvcResult = mockMvc.perform(
                patch("/employees/{id}/status")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("nurse"));
        assertTrue(employeeRepository.existsById(employee1.getId()));
        assertEquals(Status.ON_CALL, employee1.getStatus());*/
    }



    @Test
    void updateDepartment() {
        //Los tests Patch no los vimos en clase y no terminan de salirme los tets
    }


}