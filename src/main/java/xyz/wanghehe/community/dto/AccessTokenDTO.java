package xyz.wanghehe.community.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Frog
 */
@Component
@ConfigurationProperties(prefix = "github.access-token-param")
public class AccessTokenDTO {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String code;
    private String state;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
            "clientId='" + clientId + '\'' +
            ", clientSecret='" + clientSecret + '\'' +
            ", redirectUri='" + redirectUri + '\'' +
            ", code='" + code + '\'' +
            ", state='" + state + '\'' +
            '}';
    }
}
