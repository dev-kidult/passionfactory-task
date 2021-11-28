package com.passionfactory.task.domain

import java.time.OffsetDateTime
import javax.persistence.*
import javax.validation.constraints.Size

@Table
@Entity
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 0, max = 1000000)
    val id: Int,
    @Column(nullable = false)
    var name: String,
    @Column
    var completed: Boolean? = null,
    @Column
    var completedAt: OffsetDateTime? = null,
    @Column(nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
)
