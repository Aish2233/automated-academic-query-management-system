package com.academic.querysystem;

public class UserQuery {
    private String queryText;
    private String response;

    public UserQuery(String queryText) {
        this.queryText = queryText;
    }

    public String getQueryText() {  // Ensure this method exists
        return queryText;
    }

    public String getResponse() {  // Ensure this method exists
        return response;
    }

    public void setResponse(String response) {  // Ensure this method exists
        this.response = response;
    }
}
