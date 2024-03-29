package com.example.demo.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    /**
     * コントローラーのログ出力。
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("bean(*Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("メソッド開始: " + jp.getSignature());

        try {
            Object result = jp.proceed();
            System.out.println("メソッド終了: " + jp.getSignature());

            return result;

        } catch (Exception e) {
            System.out.println("メソッド異常終了: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * UserDaoクラス(実装クラスも)のログ出力。
     * @param jp
     * @return
     * @throws Throwable
     */
    @Around("execution(* *..*.*UserDao*.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {

        System.out.println("Daoクラスのメソッド開始: " + jp.getSignature());

        try {
            Object result = jp.proceed();
            System.out.println("Daoクラスのメソッド終了: " + jp.getSignature());

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Daoクラスの異常終了: " + jp.getSignature());
            throw e;
        }
    }
}
