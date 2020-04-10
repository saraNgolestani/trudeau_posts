package io.github.sarangolestani.domain.srv;

public class TwitterSearchResult {
    private String min_position;
    private String items_html;


    public String getMin_position() {
        return min_position;
    }

    public void setMin_position(String min_position) {
        this.min_position = min_position;
    }

    public String getItems_html() {
        return items_html;
    }

    public void setItems_html(String items_html) {
        this.items_html = items_html;
    }
}
