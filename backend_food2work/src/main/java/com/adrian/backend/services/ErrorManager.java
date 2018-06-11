package com.adrian.backend.services;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by adrianjez on 06.12.2017.
 */

public class ErrorManager {

    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static final String NETWORK_ERROR_MESSAGE = "No Internet Connection!";


    private Throwable error;
    private Response response;


    public ErrorManager(Throwable e) {
        e.printStackTrace();
        this.error = e;
    }

    public ErrorManager(Response response){
        this.response = response;
    }


    public String getAppErrorMessage() {
        if(error != null) {
            if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
            //TODO Other cases
        }
        if(response != null){
            return "Response code: " + response.code() + " " + response.message();
            //TODO Other cases for codes
        }

        return DEFAULT_ERROR_MESSAGE;
    }

}
