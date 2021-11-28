package com.passionfactory.task.dto

import javax.validation.constraints.NotNull

class TodoDto {
    data class Request(
        @field:NotNull
        val name: String,
        val completed: Boolean? = null
    )

    data class Response(
        val id: Int,
        val name: String,
        val completed: Boolean
    )
}
