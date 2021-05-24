package com.b2w.starwarsapi.endpoints.exceptions;


public class StandardError {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMsg;

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getDeveloperMsg() {
        return developerMsg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDeveloperMsg(String developerMsg) {
        this.developerMsg = developerMsg;
    }

    public StandardError(String title, int status, String detail, long timeStamp, String developerMsg) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMsg = developerMsg;
    }


}
