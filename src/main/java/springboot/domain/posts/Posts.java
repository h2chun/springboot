package springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //Getter 메소드 자동 생성
@NoArgsConstructor  //기본 생성자 추가. public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스임을 나타낸다.
public class Posts {

    @Id //PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성규칙
    private Long id;

    /*
    * @Column
    *   - 테이블 칼럼을 나타내며 굳이 선언하지 않아도 해당 클래스 필드는 모두 칼럼이 됨
    *   - 변경이 필요한 옵션이 있으면 사용함.
    *   - 예) 문자열 VARCHAR(255)가 기본인데 500으로 늘리던가 타입을 변경하는 등에 경우 사용함.
    * */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    //빌드 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
