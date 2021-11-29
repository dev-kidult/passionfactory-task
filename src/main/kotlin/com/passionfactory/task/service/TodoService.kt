package com.passionfactory.task.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.passionfactory.task.domain.Todo
import com.passionfactory.task.dto.TodoDto
import com.passionfactory.task.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.OffsetDateTime

@Service
class TodoService(private val todoRepository: TodoRepository, private val objectMapper: ObjectMapper) {
    @Transactional(readOnly = true)
    fun getTodo(id: Int): Todo = todoRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found") }

    @Transactional(readOnly = true)
    fun getTodos(): List<TodoDto.Response> = todoRepository.findAll().map { TodoDto.Response(id = it.id, name = it.name, completed = it.completed) }.toList()

    @Transactional
    fun createTodo(request: TodoDto.Request): ResponseEntity<Todo> =
        ResponseEntity<Todo>(todoRepository.save(objectMapper.convertValue(request, Todo::class.java)), HttpStatus.CREATED)

    @Transactional
    fun updateTodo(id: Int, request: TodoDto.Request) =
        getTodo(id).apply {
            name = request.name
            completed = request.completed
            request.completed?.let {
                if (it) {
                    completedAt = OffsetDateTime.now()
                }
            }
            updatedAt = OffsetDateTime.now()
        }

    @Transactional
    fun deleteTodo(id: Int): ResponseEntity<Any> {
        todoRepository.delete(getTodo(id))
        return ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }
}