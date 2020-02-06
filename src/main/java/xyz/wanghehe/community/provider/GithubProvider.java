package xyz.wanghehe.community.provider;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.io.IOException;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import xyz.wanghehe.community.dto.AccessTokenDTO;
import xyz.wanghehe.community.dto.GithubUser;
import xyz.wanghehe.community.utils.Jacksons;

/**
 * @author Frog
 */


@Component
@ConfigurationProperties(prefix = "github.api")
public class GithubProvider {

    @Setter private String accessTokenApi;
    @Setter private String getUserApi;

    private final OkHttpClient client;

    public GithubProvider(OkHttpClient client) {
        this.client = client;
    }

    /**
     * 通过 Github 提供的 code 获取 access_token
     */
    public String getAccessToken(AccessTokenDTO dto) {
        MediaType jsonMediaType = MediaType.get("application/json; charset=utf-8");
        Jacksons jacksons = Jacksons.me();
        jacksons.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        String accessToken = null;
        try {
            String accessTokenDtoJson = jacksons.readAsString(dto);
            //发送post请求
            RequestBody body = RequestBody.create(accessTokenDtoJson, jsonMediaType);
            Request request = new Request.Builder()
                .url(this.accessTokenApi)
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                String string = Objects.requireNonNull(response.body()).string();
                accessToken = string.split("&")[0].split("=")[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public GithubUser getUser(String accessToken) {
        Jacksons jacksons = Jacksons.me();
        jacksons.getObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        GithubUser user = null;
        //构建url
        HttpUrl url = Objects.requireNonNull(HttpUrl.parse(getUserApi))
            .newBuilder()
            .addEncodedQueryParameter("access_token", accessToken)
            .build();
        //构建request
        Request request = new Request.Builder()
            .url(url)
            .build();
        try (Response response = client.newCall(request).execute()) {
            String userJson = Objects.requireNonNull(response.body()).string();
            user = jacksons.json2Obj(userJson, GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}
