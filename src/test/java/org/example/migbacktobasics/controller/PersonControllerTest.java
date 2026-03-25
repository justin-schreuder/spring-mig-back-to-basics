package org.example.migbacktobasics.controller;

import org.example.migbacktobasics.dto.PersonRequest;
import org.example.migbacktobasics.dto.PersonResponse;
import org.example.migbacktobasics.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockitoBean
    private PersonService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(new PersonResponse(1L, "Jane", "Jackson")));

        mockMvc.perform(get("/api/all"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Jackson\"}]"));
    }

    @Test
    @WithMockUser
    void testCreate() throws Exception {
        doNothing().when(service).create(any(PersonRequest.class));

        mockMvc.perform(post("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Jane\",\"lastName\":\"Jackson\"}")
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(content().string(""));
    }

    @Test
    @WithMockUser
    void testDelete() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/api/1")
                .with(csrf()))
            .andExpect(status().isNoContent())
            .andExpect(content().string(""));
    }

}