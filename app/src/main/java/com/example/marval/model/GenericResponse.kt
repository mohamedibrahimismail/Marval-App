package com.example.marval.model

import java.io.Serializable

data class GenericResponse<T>(
    val message: String?,
    val data: T?,
    val errors: Map<String, ArrayList<String>>?,
    val code: Int? = 0,
    val meta : PaginationModel
) : Serializable