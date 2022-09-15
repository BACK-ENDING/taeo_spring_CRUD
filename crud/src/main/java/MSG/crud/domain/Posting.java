package MSG.crud.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posting {
    private String title;

    private Long postId;

    private String content;

    private String name;
}
