package xyz.wanghehe.community.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Frog
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "github.access-token-param")
public class AccessTokenDTO {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private String state;

}
