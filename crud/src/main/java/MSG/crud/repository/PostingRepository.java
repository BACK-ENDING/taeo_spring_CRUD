package MSG.crud.repository;

import MSG.crud.domain.Posting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostingRepository {
        Posting save(Posting posting);

        Optional<Posting> findByTitle(String title);

        Optional<Posting> findByName(String name);

        Optional<Posting> findByPostId(Long postid);

        List<Posting> findAll();

        Optional<Posting> updateByAll(String title, String content, String name);

        Optional<Posting> deleteByAll(String title, Long post_id, String content, String name);


}
