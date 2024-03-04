package com.example.kfd_hw.model.exception
import org.springframework.http.HttpStatus

class UnauthorizeException  : AbstractApiException() {
    override val message: String
        get() = "unauthorized"
    override val status = HttpStatus.UNAUTHORIZED
}