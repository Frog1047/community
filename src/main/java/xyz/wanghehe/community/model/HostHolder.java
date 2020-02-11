package xyz.wanghehe.community.model;

import org.springframework.stereotype.Component;

/**
 * @author Frog
 */
@Component
public class HostHolder {

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public void setUser(User user) {
        userThreadLocal.set(user);
    }

    public User getUser() {
        return userThreadLocal.get();
    }

    public void clear() {
        userThreadLocal.remove();
    }
}
