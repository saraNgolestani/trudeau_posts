package io.github.sarangolestani.domain.srv;

import java.util.Date;
import java.util.List;

public class CNNSearchResult {

    private List<Result> result;
    private Meta meta;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Meta{
        private int start;
        private int total;
        private int end;


        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static class Result{
        private String headline;
        private Date lastPublishDate;
        private String thumbnail;
        private String body;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public Date getLastPublishDate() {
            return lastPublishDate;
        }

        public void setLastPublishDate(Date lastPublishDate) {
            this.lastPublishDate = lastPublishDate;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
