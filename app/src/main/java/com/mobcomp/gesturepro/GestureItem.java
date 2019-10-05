package com.mobcomp.gesturepro;

public class GestureItem {
    private String gestureName;
    private String url;

    public String getGestureName() {
        return gestureName;
    }

    public void setGestureName(String gestureName) {
        this.gestureName = gestureName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GestureItem(String gestureName, String url) {
        this.gestureName = gestureName;
        this.url = url;
    }
}
