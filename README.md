![제목을-입력해주세요_-003](https://user-images.githubusercontent.com/62979330/222135282-49dd0b22-0bf5-40d8-a6ae-3ca5ce01a83b.png)
<div align="center">
  <h1>미세먼지 알리미</h1>
  <h4>[🔸 MINI PROJECT 🔸]</h1>
</div>
<div align="center">
  <h4>지역별 미세먼지 경보/주의보 발생 시 Notification으로 알림을 주는 안드로이드 앱</h1>
</div>

## Development Environment
- Android Studio Electric Eel
- Kotlin 1.7.20

## Application Version
+ minSdkVersion 26
+ targetSdkVersion 33

## Libraries
- **Jetpack**
    - Compose, WorkManager, DataStore
- **Asynchronous programming**
    - Coroutine
- **Network**
    - Retrofit

## Open API
[**한국환경공단_에어코리아_미세먼지 경보 발령 현황**](https://www.data.go.kr/data/15073885/openapi.do)

## Functions
__1. 지역명 및 권역명 입력__
+ 입력한 지역명과 권역명으로 오늘 날짜의 미세먼지 경보/주의보 발생 유무 확인
+ 입력한 지역명과 권역명은 DataStore를 사용하여 저장
+ 사용자가 설정한 지역명과 권역명은 실시간으로 UI에 표시되며, 변경 시에도 즉시 반영됨

__2. 미세먼지 알림__
+ Retrofit을 사용하여 서버와 통신
+ 미세먼지 경보/주의보는 매일 발생하는 것이 아니며, 따라서 서버 측에서도 특정 날짜에 미세먼지 경보/주의보가 발생할 때만 데이터가 생성됨
+ 사용자가 설정한 지역에서 오늘 미세먼지 경보/주의보가 발생했는지 확인하는 작업은 1시간 주기로 수행되고, 이 작업은 WorkManager를 사용하여 수행
+ 오늘 날짜에 사용자가 설정한 지역에서 미세먼지 경보/주의보가 발생했다면 Notification을 통해 사용자에게 알림
+ Notification을 보내고, 서버로부터 데이터를 가져오는 작업은 WorkUtils 클래스로 분리


