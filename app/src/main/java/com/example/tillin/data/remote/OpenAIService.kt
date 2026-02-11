package com.example.tillin.data.remote

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/chat/completions")
    suspend fun analyzeTil(
        @Header("Authorization") auth: String,
        @Header("HTTP-Referer") referer: String = "https://tillin.app",
        @Header("X-Title") title: String = "tillin",
        @Body request: ChatRequest
    ): ChatResponse
}

data class ChatRequest(
    val model: String = "mistralai/mistral-7b-instruct",
    val messages: List<Message>,
    val temperature: Double = 0.7
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)