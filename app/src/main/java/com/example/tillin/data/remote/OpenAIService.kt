package com.example.tillin.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface OpenAIService {
    @POST("v1/chat/completions")
    suspend fun analyzeTil(
        @Header("Authorization") token: String,
        @Body request: OpenAIRequest
    ): OpenAIResponse
}

data class OpenAIRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<Message>,
    val response_format: ResponseFormat = ResponseFormat()
)
data class Message(val role: String, val content: String)
data class ResponseFormat(val type: String = "json_object")

data class OpenAIResponse(val choices: List<Choice>)
data class Choice(val message: Message)

data class TilAnalysisResult(
    val emotion: String,
    val emotionScore: Int,
    val difficultyLevel: String,
    val comment: String
)