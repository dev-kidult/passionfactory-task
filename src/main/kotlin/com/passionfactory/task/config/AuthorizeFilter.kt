package com.passionfactory.task.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

class AuthorizeFilter : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        // TODO 인증방식에 따라 개발을 해보자
        val apikey = request.getParameter("apikey")
        if (apikey != "123") {
            response as HttpServletResponse
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = Charsets.UTF_8.name()
            val body = jacksonObjectMapper().createObjectNode()
            body.put("status", HttpStatus.UNAUTHORIZED.value().toString())
            body.put("error", "Not Authorized")
            jacksonObjectMapper().writeValue(response.writer, body)
        }
        chain.doFilter(request, response)
    }
}