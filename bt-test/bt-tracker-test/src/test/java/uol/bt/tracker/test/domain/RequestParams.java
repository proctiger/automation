package uol.bt.tracker.test.domain;

/**
 *
 * @author dvrocha
 */
public class RequestParams {

    private String userAgent;
    private String referer;
    private CookieContent bttrk;
    private String dnt;

    public String getDnt() {
        return dnt;
    }

    public void setDnt(String dnt) {
        this.dnt = dnt;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public CookieContent getBttrk() {
        return bttrk;
    }

    public void setBttrk(CookieContent bttrk) {
        this.bttrk = bttrk;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}