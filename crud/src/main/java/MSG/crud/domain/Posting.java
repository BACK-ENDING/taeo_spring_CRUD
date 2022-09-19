package MSG.crud.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Setter
public class Posting {
    private String title;

    private Long postid;

    private String content;

    private String name;

}
