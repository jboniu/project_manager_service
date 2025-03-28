package hut.zj.common.query;

import java.io.Serializable;

public class Sort implements Serializable {

    public enum Dir{
        desc("desc"),

        asc("asc");

        private String dir;

        Dir(String dir) {
            this.dir = dir;
        }
    }

    private String field;

    private Dir dir;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
