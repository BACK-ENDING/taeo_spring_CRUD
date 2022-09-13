package MSG.crud.repository;


import MSG.crud.domain.Posting;

import java.util.*;



public class MemoryPostingRepository implements PostingRepository{
    private static Map<Integer, Posting> store = new HashMap<>();

    private static Integer sequence = 0;

    @Override
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
    public Optional<Posting> findByContent(String content) {
        return Optional.empty(); //선택
    }

    @Override
    public Optional<Posting> findByUser(String user) {
        return Optional.empty(); //리스트로 변경
    }

    @Override
    public Optional<Posting> findByPostId(Integer id) {
        return Optional.ofNullable(store.get(id)); //단일 게시물
    }

    @Override
    public List<Posting> findAll() {
        return new ArrayList<>(store.values());
}
