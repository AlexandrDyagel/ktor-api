package com.example.domain.model.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Long,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String?,
    @SerialName("username") val username: String?,
    @SerialName("language_code") val languageCode: String,
    //@SerialName("allows_write_to_pm") val allowsWriteToPM: Boolean
)
