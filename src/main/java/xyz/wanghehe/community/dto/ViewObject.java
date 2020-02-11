package xyz.wanghehe.community.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frog
 */
public class ViewObject {

    private Map<String, Object> objs = new HashMap<>();

    public void set(String key, Object obj) {
        objs.put(key, obj);
    }

    public Object get(String key) {
        return objs.get(key);
    }

}
