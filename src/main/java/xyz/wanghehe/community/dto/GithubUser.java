package xyz.wanghehe.community.dto;

/**
 * @author Frog
 */
public class GithubUser {
    private Long id;
    private String login;
    private String bio;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", bio='" + bio + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

    public boolean isEmpty() {
        return id == null;
    }

    public String getNameWithoutNull() {
        return name == null ? login : name;
    }
}
