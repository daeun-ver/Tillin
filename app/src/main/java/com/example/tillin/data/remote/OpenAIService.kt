package com.example.tillin.data.remote

import com.example.tillin.BuildConfig
import com.example.tillin.data.local.entity.TilEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
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

//TIL 개별 분석
suspend fun analyzeTil(
    apiService: OpenAIService,
    til: TilEntity
): TilEntity = withContext(Dispatchers.IO) {

    val prompt = """
        당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용을 분석해 JSON으로 응답해 주세요.

        [TIL 내용]
        제목: ${til.title}
        오늘 배운 것: ${til.learned}
        어려웠던 점: ${til.difficulty ?: "없음"}
        내일 할 일: ${til.tomorrow ?: "없음"}

        [분석 요청]
        {
          "emotion": "성취감/만족/평범/어려움/좌절 중 하나",
          "emotionScore": 1-5 사이 정수,
          "difficultyLevel": "쉬움/보통/어려움/매우 어려움 중 하나",
          "comment": "격려나 조언 한 문장 (20자 이내)"
        }
        """.trimIndent()

    val request = ChatRequest(messages = listOf(Message("user", prompt)))
    val response =
        apiService.analyzeTil(auth = "Bearer ${BuildConfig.OPENAI_API_KEY}", request = request)

    val content = response.choices[0].message.content
    val startIndex = content.indexOf("{")
    val endIndex = content.lastIndexOf("}")

    val clean = if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
        content.substring(startIndex, endIndex + 1)
    } else {
        content.replace("```json", "").replace("```", "").trim()
    }

    val json = JSONObject(clean)

    til.copy(
        emotion = json.getString("emotion"),
        emotionScore = json.getInt("emotionScore"),
        difficultyLevel = json.getString("difficultyLevel"),
        comment = json.getString("comment")
    )
}

//주간 통계
suspend fun analyzeWeeklyStats(
    apiService: OpenAIService,
    tils: List<TilEntity>
): WeeklyResult = withContext(Dispatchers.IO) {
    val weeklyContent = tils.joinToString("\n")
    { "- 제목: ${it.title}\n  배운 점: ${it.learned}\n  어려웠던 점: ${it.difficulty ?: "없음"}" }

    val prompt = """
        당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용들을 분석해 JSON으로 응답해 주세요.

        [TIL 내용들]
        $weeklyContent

        [분석 요청]
        {
          "weeklySummary": "이번 주 TIL 전체 요약과 격려 (30자 이내)",
          "weeklyKeywords": "이번 주 가장 많이 학습한 키워드 3개",
        }
        """.trimIndent()

    val request = ChatRequest(messages = listOf(Message("user", prompt)))
    val response = apiService.analyzeTil(auth = "Bearer ${BuildConfig.OPENAI_API_KEY}", request = request)

    val content = response.choices[0].message.content
    val startIndex = content.indexOf("{")
    val endIndex = content.lastIndexOf("}")

    val clean = if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
        content.substring(startIndex, endIndex + 1)
    } else {
        content.replace("```json", "").replace("```", "").trim()
    }

    val json = JSONObject(clean)

    WeeklyResult(
        weeklySummary = json.getString("weeklySummary"),
        weeklyKeywords = json.getString("weeklyKeywords")
    )
}

data class WeeklyResult(
    val weeklySummary: String,
    val weeklyKeywords: String
)


//월간 통계
suspend fun analyzeMonthlyStats(
    apiService: OpenAIService,
    tils: List<TilEntity>
): MonthlyResult = withContext(Dispatchers.IO) {
    val monthlyContent = tils.joinToString("\n")
    { "- 제목: ${it.title}\n  배운 점: ${it.learned}\n  어려웠던 점: ${it.difficulty ?: "없음"}" }

    val prompt = """
        당신은 개발자 학습 코치입니다. 아래 TIL(Today I Learned) 내용들을 분석해 JSON으로 응답해 주세요.

        [TIL 내용들]
        $monthlyContent

        [분석 요청]
        {
          "monthlySummary": "이번 달 TIL 전체를 요약하는 따뜻한 메시지 (50자 이내)",
          "monthlyKeywords": "이번 달 가장 많이 학습한 키워드 3개",
          "growth": "지난달보다 성장한 점이나 인상 깊은 변화 (20자 이내)",
          "advice": "다음 달을 위한 짧은 조언 (20자 이내)",
          "averageDifficulty": "전체적인 난이도 (쉬움/보통/어려움/매우 어려움 중 하나)"
        }
        """.trimIndent()

    val request = ChatRequest(messages = listOf(Message("user", prompt)))
    val response = apiService.analyzeTil(auth = "Bearer ${BuildConfig.OPENAI_API_KEY}", request = request)

    val content = response.choices[0].message.content
    val startIndex = content.indexOf("{")
    val endIndex = content.lastIndexOf("}")

    val clean = if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
        content.substring(startIndex, endIndex + 1)
    } else {
        content.replace("```json", "").replace("```", "").trim()
    }

    val json = JSONObject(clean)

    MonthlyResult(
        monthlySummary = json.getString("monthlySummary"),
        monthlyKeywords = json.getString("monthlyKeywords"),
        growth = json.getString("growth"),
        advice = json.getString("advice"),
        averageDifficulty = json.getString("averageDifficulty")
    )
}

data class MonthlyResult(
    val monthlySummary: String,
    val monthlyKeywords: String,
    val growth: String,
    val advice: String,
    val averageDifficulty: String
)