package hut.zj.common.exception;

public class CopyFailedException extends RuntimeException{
    public CopyFailedException(String msg){
        super("对象拷贝失败!" + msg);
    }
}
