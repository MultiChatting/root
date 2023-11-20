# root
[처음이야?] 자바 소켓 프로그래밍: 함께 만들어가는 채팅 시스템

# 멀티채팅 프로그램 구현

## 프로젝트 개요
- **프로젝트명**: 멀티채팅 프로그램 구현
- **팀명**: 자바톡톡
- **팀장**: 김지창
- **팀원 및 역할**:
  - 김강민: 클라이언트 입장창 GUI 설계
  - 최원일: 채팅창 GUI 설계
- **기간**: 2023.07.12 - 2023.07.21
- **사용 언어**: JAVA 1.8.0_144
- **통합개발도구**: IntelliJ IDEA 2023.1.2

## 프로젝트 설명
이 프로젝트는 Java를 사용한 멀티채팅 프로그램을 구현하는 것을 목표로 합니다. 
주요 기술은 Java, WindowBuilder, 소켓통신, 스레드, 동기화입니다. 
이 프로젝트는 익명 채팅방 기능을 구현하며, 서버와 클라이언트로 구성됩니다.

### 클라이언트 기능
- 입장창 GUI
- 채팅창 GUI
- 서버로부터 메시지 수신 및 채팅 처리

### 서버 기능
- 클라이언트 연결 대기
- 닉네임 처리
- 채팅 처리
- 클라이언트 로그인 및 로그아웃 처리
- 연결 끊어짐 오류 처리
- 서버 GUI 설계 및 구현

## 문제점 및 해결 방안
### 김강민
- **EnterGui.java**:
  - 닉네임 처리 시 소켓 통신으로 전달 후 소켓을 닫게 되어 클라이언트의 입장이 끊기는 문제 발생.
- **해결 방안**: 추후 논의 예정.

### 최원일
- **ChatGui.java**:
  - 이벤트 처리 중 소켓 통신이 자주 닫히는 문제 발생.
- **해결 방안**: 이벤트 처리 코드 밖에서 소켓 통신 코드를 별도로 작성.

### 김지창
- **GIT 사용**:
  - 코드 공유를 위해 Git 사용 결정.
  - 이클립스에서 IntelliJ IDEA로 IDE 변경 (Git 연동 용이).
- **클라이언트-서버 상태 공유 문제**:
  - 클라이언트가 종료 버튼으로 접속 종료 시 서버에 종료 메시지를 보내지 않는 문제 발생.
  - **해결 방안**: 클라이언트와 서버 사이에 상태 확인 메시지를 주기적으로 주고받아 예외 처리.

## 참고 자료
- Git 사용법: [Git 사용 가이드](https://prickly-holiday-b16.notion.site/git-d3739bbc476b4635ab5b938ddd4d4865)
- Thread와 관련된 정보: [Java Thread 문서](https://wikidocs.net/230)
- BufferedReader와 Scanner 비교: [BufferedReader vs Scanner](https://snupi.tistory.com/48)

---
