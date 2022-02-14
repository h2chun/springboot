# SpringBoot 연습
스프링 부트와 AWS로 혼자 구현하는 웹서비스 책과 구글검색으로 스트링부트 연습 진행.

## 목차
- [01장 인텔리제이 사용하기](#01장-인텔리제이-사용하기)
- [02장 TDD 및 테스트코드 작성](#02장-TDD-및-테스트코드-작성)
- [03장 스프링 부트에서 JPA로 데이터베이스 다뤄보자](#03장-스프링-부트에서-JPA로-데이터베이스-다뤄보자)

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
