package MSG.crud.repository;

import MSG.crud.domain.Posting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostingRepository {
        Posting save(Posting posting);

        Optional<Posting> findByTitle(String title);

        List<Posting> findByUser(String user);

        Optional<Posting> findByPostId(Long id);

        List<Posting> findAll();


}
