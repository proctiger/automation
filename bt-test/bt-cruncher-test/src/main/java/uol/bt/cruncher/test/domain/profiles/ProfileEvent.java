package uol.bt.cruncher.test.domain.profiles;


import uol.bt.cruncher.test.domain.BtEvent;

import com.google.gson.annotations.SerializedName;

public class ProfileEvent extends BtEvent {

    @SerializedName("cookie_value")
    private String cookieValue;

    public ProfileEvent() {
        super("uol_session");
    }

    public String getCookieValue() {
        return cookieValue;
    }
    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
