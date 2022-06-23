package com.ironhack.lab42.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.model.Patient;
import com.ironhack.lab42.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerImplTest {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Patient patient1, patient2;

    private Employee employee1, employee2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        patient1 = new Patient("Rocio Arellano", new Date(1994/12/18),employee1);
        patient2 = new Patient("Sofia de la Fuente", new Date(1998/11/1), employee2);
        patientRepository.saveAll(List.of(patient1,patient2));
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    void findAll_NoParams_AllPatients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rocio Arellano"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sofia de la Fuente"));
    }

    @Test
    void findById_Patients() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/patients/"+patient1.getDateOfBirth()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
    }



    @Test
    void store_Patients() throws Exception {
        Patient patient3 = new Patient("Carlos Hervas", new Date(1996/12/9),employee1);
        String body = objectMapper.writeValueAsString(patient3);

        MvcResult mvcResult = mockMvc.perform(
                        post("/patients")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Carlos Hervas"));

    }

    @Test
    void renovate_Patients() throws Exception {

        patient1.setName("Ana Pozo");
        String body = objectMapper.writeValueAsString(patient1);

        MvcResult mvcResult = mockMvc.perform(
                        put("/patients/" + patient1.getId())
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Patient> optionalPatient = patientRepository.findById(patient1.getId());
        assertTrue(optionalPatient.isPresent());
        assertEquals("Ana Pozo",optionalPatient.get().getName());


    }
}