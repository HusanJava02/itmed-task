package com.exmple.it_med_task.controller;

import com.exmple.it_med_task.controllers.AppointmentController;
import com.exmple.it_med_task.entites.Appointment;
import com.exmple.it_med_task.models.AppointmentModel;
import com.exmple.it_med_task.service.AppointmentService;
import com.exmple.it_med_task.utils.AppointModelUtils;
import com.exmple.it_med_task.utils.AppointmentUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {AppointmentController.class})
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AppointmentService appointmentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_save_appointment_success() throws Exception {
        AppointmentModel appointmentModel = AppointModelUtils.create();
        Appointment appointment = AppointmentUtils.create();
        when(appointmentService.save(appointmentModel)).thenReturn(appointmentModel);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/appointments")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentModel)))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
        assertEquals(HttpStatus.CREATED.value(), status);
    }

    @Test
    public void test_get_appointment_success() throws Exception {
        AppointmentModel appointmentModel = AppointModelUtils.create();
        Appointment appointment = AppointmentUtils.create();
        when(appointmentService.findById(1L)).thenReturn(appointment);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/appointments/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
        assertEquals(200, status);
    }

    @Test
    public void test_update_appointment_success() throws Exception {
        Appointment appointment = AppointmentUtils.create();
        AppointmentModel appointmentModel = AppointModelUtils.create();
        when(appointmentService.update(appointmentModel, appointmentModel.getId())).thenReturn(appointmentModel);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appointmentModel)))
                .andReturn();

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(200, status);
        assertNotNull(content);


    }


}
