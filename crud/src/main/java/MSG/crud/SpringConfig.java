package MSG.crud;

import MSG.crud.repository.JDBCPostingRepository;
import MSG.crud.repository.PostingRepository;
import MSG.crud.service.PostingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PostingService postingService() {
        return new PostingService(postingRepository());
    }

    @Bean
    public PostingRepository postingRepository() {
        return new JDBCPostingRepository(dataSource);
    }

}
