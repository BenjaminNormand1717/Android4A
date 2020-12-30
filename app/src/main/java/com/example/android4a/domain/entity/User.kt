package com.example.android4a.domain.entity

import java.io.Serializable

data class User(
    val email: String?,
    val password: String,
    val nom: String,
    val prenom: String

) : Serializable

