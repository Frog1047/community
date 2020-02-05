package xyz.wanghehe.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.provider.GithubProvider;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    GithubProvider githubProvider;

    @Test
    void contextLoads() {

    }

}
