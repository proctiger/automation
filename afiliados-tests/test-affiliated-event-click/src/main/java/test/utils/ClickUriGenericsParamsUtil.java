package test.utils;

import org.springframework.stereotype.Component;

@Component
class ClickUriGenericsParamsUtil {

    public static String genericRedirUrl;

    public static String genericType;

    public static String genericClickDomain;

    @SuppressWarnings("static-access")
    public void setGenericRedirUrl(String genericRedirUrl) {
        this.genericRedirUrl = genericRedirUrl;
    }

    @SuppressWarnings("static-access")
    public void setGenericType(String gerericType) {
        this.genericType = gerericType;
    }

    @SuppressWarnings("static-access")
    public void setGenericClickDomain(String genericClickDomain) {
        this.genericClickDomain = genericClickDomain;
    }
}
