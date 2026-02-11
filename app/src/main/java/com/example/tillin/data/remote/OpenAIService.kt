package com.example.tillin.data.remote

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/responses")
    suspend fun analyzeTil(
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: OpenAIRequest
    ): OpenAIResponse
}

data class OpenAIRequest(
    val model: String = "gpt-4.1-mini",
    val input: List<InputMessage>
)

data class InputMessage(
    val role: String,
    val content: List<InputContent>
)

data class InputContent(
    val type: String = "input_text",
    val text: String
)

data class OpenAIResponse(
    val output: List<OutputItem>
)

data class OutputItem(
    val content: List<OutputContent>
)

data class OutputContent(
    val type: String,
    val text: String
)