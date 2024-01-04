package com.example.kfd_hw.model.exception

import org.springframework.http.HttpStatus

class ApiException(
    override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String,
) : AbstractApiException()