package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

        @Test
        void contextLoads() {
                System.out.println("TEST LÄUFT: alles gut");
                assertTrue(true);
        }

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void resetState() throws Exception {
        // optional: leere Liste durch Entfernen aller Tasks
        mockMvc.perform(post("/reset"));
    }

    @Test
    void testAddAndGetTasks() throws Exception {
        // Task anlegen
        String json = objectMapper.writeValueAsString(new Task() {{
            setTaskdescription("Beispielaufgabe");
        }});

        mockMvc.perform(post("/tasks")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());

        // Tasks abrufen und prüfen
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"taskdescription\":\"Beispielaufgabe\"}]"));
    }

}
