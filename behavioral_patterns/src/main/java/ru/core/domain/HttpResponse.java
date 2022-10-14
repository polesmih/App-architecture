package ru.core.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int statusCode;
    private String statusCodeName;
    private Map<String, String> headers = new HashMap<>();
    private String body;


    public HttpResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeName() {
        return statusCodeName;
    }

    public void setStatusCodeName(String statusCodeName) {
        this.statusCodeName = statusCodeName;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final HttpResponse httpResponse;

        public Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode) {
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withStatusCodeName(String statusCodeName) {
            this.httpResponse.statusCodeName = statusCodeName;
            return this;
        }

        public Builder withHeaders(String key, String value) {
            this.httpResponse.getHeaders().put(key, value);
            return this;
        }

        public Builder withBody(String body) {
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build() {
            return httpResponse;
        }

    }

}
