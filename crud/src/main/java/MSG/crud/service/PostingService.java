package MSG.crud.service;

import MSG.crud.domain.Posting;
import MSG.crud.repository.PostingRepository;

import java.util.Optional;
import java.util.List;

public class PostingService {

    private final PostingRepository postingRepository;


    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    public long join(Posting posting) {
        // 같은 이름이 있는 중복 제목은 X
        validateDuplicatePosting(posting); // 중복 제목 검증
        postingRepository.save(posting);
        return posting.getPostid();
    }

    private void validateDuplicatePosting(Posting posting) {
        postingRepository.findByTitle(posting.getTitle())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 글제목입니다");
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Posting> findPosting() {
        return postingRepository.findAll();

    }
    public Optional<Posting> findOne(Long postId) {
        return postingRepository.findByPostid(postId);
    }
}
