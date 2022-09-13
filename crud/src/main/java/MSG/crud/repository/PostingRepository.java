package MSG.crud.repository;

import MSG.crud.domain.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingRepository {
        Posting save(Posting posting);

        Optional<Posting> findByTitle(String title);

        Optional<Posting> findByContent(String content);

        Optional<Posting> findByUser(String user);

        Optional<Posting> findByPostId(Integer id);

        List<Posting> findAll();


}
