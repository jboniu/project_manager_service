package hut.zj.common.constant;

public enum UserType {

    /**
     * 超级管理员
     */
    SUPER_ADMIN("admin"),

    /**
     * 普通用户
     */
    USER("user");

    UserType(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static boolean isAdmin(String code){
        if(valueOf("SUPER_ADMIN").getCode().equals(code)) {
            return true;
        }
        return  false;
    }

}
