package cn.structure.starter.redis.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *  Redis锁注解类 - 可在业务方法上通过 spel 表达式 获取redis锁的key值
 *  举列: @RedisLock(value="#user.id")   user为当前代理方法的入参
 *  <p/>
 * @author chuck
 * @date 2020-12-17
 * @version v.0.1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /**
     * 锁的资源，redis的key
     */
    String value() default "default";

    /**
     * 持锁时间,单位毫秒
     */
    long keepMills() default 30000;

    /**
     * 当获取失败时候动作
     */
    LockFailAction  action() default LockFailAction.CONTINUE;

    enum LockFailAction{
        /** 
         * 放弃
         */
        GIVEUP,
        /** 
         * 继续
         */
        CONTINUE;
    }

    /**
     * 重试的间隔时间,设置GIVEUP忽略此项
     */
    long sleepMills() default 200;

    /** 
     * 重试次数
     */
    int retryTimes() default 5;

}
