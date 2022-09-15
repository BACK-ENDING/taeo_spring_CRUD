package MSG.crud.controller;

import MSG.crud.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostingController {
    private final PostingService postingService;

    @Autowired
    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }
}
