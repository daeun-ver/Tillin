# 🔖 Tillin - AI TIL 분석 기록 앱

> Tillin은 개발자의 꾸준한 성장을 돕는 TIL(Today I Learned) 기록 앱입니다.<br>
> TIL을 기록하면 인공지능(AI)을 통해 그날의 학습 감정과 난이도를 분석하고 통계를 시각화하여 제공합니다.

## 프로젝트 개요
- 기간 : 2026.02.02 ~ 2026.02.27
- 개요 : 당일의 학습 내용을 기록하고 AI를 통해 감정 및 난이도를 분석하는 안드로이드 애플리케이션

  
## 주요 기능
- **TIL 기록** : 학습한 내용, 어려웠던 점, 내일의 계획을 체계적으로 기록

- **AI 감정 분석** : OpenRouter(Gemini 2.0 Flash)를 연동하여 작성된 TIL 내용을 바탕으로 학습 감정과 난이도를 자동 분석

- **감정 차트** : Vico Chart 라이브러리를 활용하여 주간/월간 감정을 이모지와 그래프로 한눈에 파악

- **월간 회고**: 학습 요약, 감정 분석, 성장 포인트, 다음 달 조언을 제공

- **위젯** : Jetpack Compose Glance 기반의 앱 위젯으로 홈 화면에서 오늘의 TIL 기록 확인

- **다크 모드** : 다크모드 테마를 제공하여 야간 학습 기록 시 눈의 피로를 최소화


## 기술 스택

| 분류        | 사용 기술 / 도구                                   |
| --------- | -------------------------------------------- |
| **개발 언어** | Kotlin                                   |
| **프레임워크** | Android (Jetpack Compose)                    |
| **상태 관리** | Coroutine, Flow, ViewModel                   |
| **DI**    | Hilt                                         |
| **스토리지**  | Room DB                |
| **네트워크**    | Retrofit2 & OkHttp3 |
| **AI 연동**   | OpenRouter (Gemini 2.0 Flash)         |
| **시각화/위젯** | Vico Chart, Glance (Jetpack Compose App Widget)     |
| **구조** | Feature-based (MVVM + Clean Architecture 기반) |


## 스크린샷
<div style="display: flex; gap: 30px;">
<img width="30%" alt="Image" src="https://github.com/user-attachments/assets/970e4b80-a42c-43fd-abe4-648f0f83a2a5" /> 
<img width="30%" alt="Image" src="https://github.com/user-attachments/assets/6128670c-d59a-43f7-be28-e7d1d54fd1be" /> 
<img width="30%" alt="Image" src="https://github.com/user-attachments/assets/7113deef-3050-4084-bce4-9f4a4ab5627e" />

</div>

