package com.medcoanalytics.lemobileyeservice.shared.audit;



import jakarta.validation.Valid;

import java.util.List;

public class ValidateRequestBodyList<T> {


    @Valid
    private List<T> requestBody;

    public List<T> getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(List<T> requestBody) {
        this.requestBody = requestBody;
    }

    public ValidateRequestBodyList(List<T> requestBody) {
        this.requestBody = requestBody;
    }

    public ValidateRequestBodyList() {


    }
}
