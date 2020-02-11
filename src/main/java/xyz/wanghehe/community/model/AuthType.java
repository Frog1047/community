package xyz.wanghehe.community.model;

public enum AuthType {

    /**
     * 类型
     */
    GITHUB("github");

    private String value;

    AuthType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
