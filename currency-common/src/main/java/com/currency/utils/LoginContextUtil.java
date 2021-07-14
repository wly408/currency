package com.currency.utils;

import com.currency.bean.LoginContext;
import com.currency.constrants.CommonConstrants;
import com.currency.enums.CommonEnum;
import com.currency.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public final class LoginContextUtil {

    private static ThreadLocal<LoginContext> threadLocal = new ThreadLocal();


    public final static void setLoginContext(LoginContext loginContext) {
        threadLocal.set(loginContext);
    }

    public final static void clearLoginContext() {
        threadLocal.remove();
    }

    public final static String getUserId() {
        LoginContext loginContext = threadLocal.get();
        if (loginContext != null) {
            return loginContext.getUserId();
        }
        return null;

    }

    public final static String getUserType() {
        LoginContext loginContext = threadLocal.get();
        if (loginContext != null) {
            return loginContext.getUserType();
        }
        return null;
    }

    public final static String getUserCode() {
        LoginContext loginContext = threadLocal.get();
        if (loginContext != null) {
            return loginContext.getUserCode();
        }
        return null;
    }

    public final static String getUserName() {
        LoginContext loginContext = threadLocal.get();
        if (loginContext != null) {
            return loginContext.getUserName();
        }
        return null;
    }

    public final static String getTenantId() {

        LoginContext loginContext = threadLocal.get();
        if (loginContext != null) {
            return loginContext.getTenantId();
        }
        return null;
    }

    public final static boolean isRole(String roleCode) {
        LoginContext loginContext = threadLocal.get();
        List<String> roleList = loginContext.getRoleList();

        if (!CollectionUtils.isEmpty(roleList)) {
            for (String s : roleList) {
                if (s.equals(roleCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否超级管理员
     *
     * @return
     */
    public final static boolean isSuperAdmin() {
        return isRole(CommonEnum.SYS_ROLE_SUPER_ADMIN.getValue());
    }

    public final static void setQueryTenantId(Object object) {
        if (object != null) {
            String tenantId = null;
            if (!LoginContextUtil.isSuperAdmin()) {
                tenantId = LoginContextUtil.getTenantId();
            }
            ObjectUtil.invokeSimple(object, "setTenantId", String.class, tenantId);
        }
    }

    public final static void hasDelAuthority(Object delVale) {
        if (delVale != null) {
            boolean isDel = false;
            if (LoginContextUtil.isSuperAdmin()) {
                isDel = true;
            }
            String tenantId = LoginContextUtil.getTenantId();
            String delTenantId = (String) ObjectUtil.invokeSimple(delVale, "getTenantId", null, null);
            if (StringUtils.isEmpty(delTenantId)) {
                isDel = true;
            } else {
                if (delTenantId.equals(tenantId)) {
                    isDel = true;
                }
            }
            if (!isDel) {
                throw new BusinessException("您无权限删除该数据");
            }
            ObjectUtil.invokeSimple(delVale, "setStatusCd", String.class, CommonConstrants.COMMON_NO);

        }
    }

    /**
     * 判断是否管理员
     *
     * @return
     */
    public final static boolean isAdmin() {
        return isRole(CommonEnum.SYS_ROLE_ADMIN.getValue());
    }

}
