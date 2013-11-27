package test.utils;

import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public String getCookieUOLAFWithSource(String source,
                                           String cookieValue) {
        return cookieWithSource(cookieValue, source);
    }

    private String cookieWithSource(String cookieValue,
                                    String source) {
        return String.format("%s:%s", cookieValue, source);
    }
}
