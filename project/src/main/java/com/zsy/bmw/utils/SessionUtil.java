package com.zsy.bmw.utils;

import com.zsy.bmw.model.SessionInfo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ZSY on 25/04/2017.
 */
public class SessionUtil {

    private static HttpSession getHttpSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getSession();
    }

    public static SessionInfo getSessionInfo() {
        HttpSession httpSession = getHttpSession();
        return (SessionInfo) httpSession.getAttribute("sessionInfo");
    }

    public static void setSessionInfo(SessionInfo sessionInfo) {
        HttpSession httpSession = getHttpSession();
        httpSession.setAttribute("sessionInfo", sessionInfo);
    }
}
