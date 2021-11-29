package com.passionfactory.task.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.passionfactory.task.dto.TodoDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(TodoController::class)
internal class TodoControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun createTodoFailNotAuthorization() {
        mvc.perform(post("/todos"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    fun createTodoFailBadRequest() {
        mvc.perform(post("/todos?apikey=123"))
            .andExpect(status().isBadRequest)
    }


    @Test
    fun createTodoSuccess() {
        val content = TodoDto.Request(name = "test", completed = false)
        val objectMapper = ObjectMapper()

        mvc.perform(
            post("/todos?apikey=123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content))
        ).andExpect(status().isCreated)
    }
}