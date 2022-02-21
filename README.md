# SpringBoot 연습
스프링 부트와 AWS로 혼자 구현하는 웹서비스 책과 구글검색으로 스트링부트 연습 진행.

## 목차
- [01장 인텔리제이 사용하기](#01장-인텔리제이-사용하기)
- [02장 TDD 및 테스트코드 작성](#02장-TDD-및-테스트코드-작성)
- [03장 스프링 부트에서 JPA로 데이터베이스 다뤄보자](#03장-스프링-부트에서-JPA로-데이터베이스-다뤄보자)
- [04장 머스테치로 화면 구성하기](#04장-머스테치로-화면-구성하기)

---

### 01장 인텔리제이 사용하기
1. 인텔리제이 설치 - 책 및 검색 활용
2. 프로젝트 생성 및 깃/깃허브 연결 - 검색 활용
  
### 02장 TDD 및 테스트코드 작성
1. Controller, Dto 테스트 작성
2. 롬복 사용

### 03장 스프링 부트에서 JPA로 데이터베이스 다뤄보자
1. Entity, Repository 생성
    - Entity에 Setter를 만들지 않고 @Builder를 사용
    - Repository는 Dao와 같은 의미로 DB Layer 접근자.
    - Entity와 Repository는 함께 위치해야 함.
    - 도메인패키지에 관리
2. Controller, Dto, Service 생성
    - 패키지로 구분하고 각 패키지 안에 클래스파일 생성
    - Entity클래스를 Requst/Response 클래스로 사용하지 말고 Dto를 사용
    - @RequuiredArgsConstrucor 사용 권장
    - Dto, Service, Controller 순서로 작업하는게 바른 순서일텐데 책에서는 그렇지 않아서 아쉽.
3. 게시판 글 등록/수정/조회 서비스
    - @RequiredArgsConstructor 사용 추천
    - JPA의 영속성 컨텍스트. 터티체킹
4. JPA Auditing 사용.
    - insert문에 sysdate를 넣는 것과 같은 효과
    - 관계형 데이터베이스를 객체지향적으로 관리하기 위해 나온 방법인듯 함.

### 04장 머스테치로 화면 구성하기
1. 서버 템플릿 엔진과 머스테치 소개
    - 서버 템플릿 엔진과 클라이언트 템플릿 엔진에 차이점.
    - 심플한 문법으로 크라이언트/서버 템플릿 모두 사용가능.
2. 기본 페이지 만들기
    - 기본적인 파일위치 -> src/main/resources/templates
    - 컨트롤러에서 문자열 반환 시 앞의 경로와 뒤의 파일 확장자는 자동 지정됨.
3. 게시글 등록 화면 만들기
    - 레이아웃방식 : 공통 영역의 별도의 파일로 분리하여 필요 한 것에서 가져다 쓰는 방식
    - header.mustache에는 css를 footer.mustache에는 js 파일을 외부CDN으로 호출함.
    - 버튼 기능은 index.js파일을 따로 만들었음.
    - index.js에 main 객체를 만들고 그 안에 function 선언 -> 해당 js에 scope를 만들어 중복된 함수명을 피함.
    - 파일 위치 절대 경로-> src/main/resources/static
4. 전체 조회 화면 만들기
    - 규모에 따라 조회요 프레임워크를 추가하기도 함. 예)querydsl, joop, Mybatis
    - 등록/수정/삭제 등은 SpringDataJpa를 통해 진행.
    - @Transactional(readOnly=true)를 사용하면 조회 속도가 개선됨.
5. 게시글 수정, 삭제 화면 만들기
    - 특별한 내용없음. 그냥 수정, 삭제 버튼 만들고 서비스 만들고 동작기능 만들고 끝.
