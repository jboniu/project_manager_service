package hut.zj.common.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoErrorhandler {

}
