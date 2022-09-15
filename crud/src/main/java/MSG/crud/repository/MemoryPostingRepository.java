package MSG.crud.repository;


import MSG.crud.domain.Posting;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MemoryPostingRepository implements PostingRepository {
    private static Map<Long, Posting> store = new HashMap<>();
    private static long sequence = 0L;

    public MemoryPostingRepository() {
    }
    public Posting save(Posting posting) {
        posting.setPostId(++sequence);
        store.put(posting.getPostId(), posting);
        return posting;
    }

    @Override
    public Optional<Posting> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Posting> findByUser(String user) {
        return new ArrayList<>(store.values().stream().filter(posting -> posting.getName().equals(user))).findAny();//리스트로 변경
    }

    @Override
    public Optional<Posting> findByPostId(Long id) {
        return Optional.ofNullable(store.get(id)); //단일 게시물
    }

    @Override
    public List<Posting> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
