package com.adrian.backend.services;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class BaseService {

    protected String mAPIKey;

    public BaseService(String apiKey){
        this.mAPIKey = apiKey;
    }

    public interface IBaseServiceResponse<T> {
        void onCompleted(T result);
        void onError(ErrorManager errorManager);
    }
}
