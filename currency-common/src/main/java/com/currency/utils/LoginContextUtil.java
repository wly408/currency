package com.currency.utils;

import com.currency.bean.LoginContext;

public final class LoginContextUtil {

    private static ThreadLocal<LoginContext> threadLocal = new ThreadLocal();


    public final static void setLoginContext(LoginContext loginContext){
        threadLocal.set(loginContext);
    }
    public final static void clearLoginContext(){
        threadLocal.remove();
    }
    public final static String getUserId(){
        LoginContext loginContext = threadLocal.get();
        if(loginContext!=null){
            return loginContext.getUserId();
        }
        return null;

    }
    public final static String getUserType(){
        LoginContext loginContext = threadLocal.get();
        if(loginContext!=null){
            return loginContext.getUserType();
        }
        return null;
    }
    public final static String getUserCode(){
        LoginContext loginContext = threadLocal.get();
        if(loginContext!=null){
            return loginContext.getUserCode();
        }
        return null;
    }
    public final static String getUserName(){
        LoginContext loginContext = threadLocal.get();
        if(loginContext!=null){
            return loginContext.getUserName();
        }
        return null;
    }
    public final static String getTenantId(){
        LoginContext loginContext = threadLocal.get();
        if(loginContext!=null){
            return loginContext.getTenantId();
        }
        return null;
    }

}
