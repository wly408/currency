package com.currency.common.util;

import com.currency.common.bean.SessionBean;

public class SessionUtil {


    public static SessionBean getSessionBean(){
        SessionBean sessionBean = new SessionBean();
        sessionBean.setTenantId("1");
        sessionBean.setUserId("1");
        return sessionBean;
    }
}
