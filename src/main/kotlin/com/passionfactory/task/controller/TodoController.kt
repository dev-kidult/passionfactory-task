package com.passionfactory.task.controller

import com.passionfactory.task.dto.TodoDto
import com.passionfactory.task.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(private val todoService: TodoService) {

    @GetMapping("/todos/{id}")
    fun getTodo(@PathVariable id: Int) = todoService.getTodo(id)

    @GetMapping("/todos")
    fun getTodos() = todoService.getTodos()

    @PostMapping("/todos")
    fun createTodo(@RequestBody request: TodoDto.Request) = todoService.createTodo(request)

    @PutMapping("/todos/{id}")
    fun updateTodo(@PathVariable id: Int, @RequestBody request: TodoDto.Request) = todoService.updateTodo(id, request)

    @DeleteMapping("/todos/{id}")
    fun deleteTodo(@PathVariable id: Int) = todoService.deleteTodo(id)

}