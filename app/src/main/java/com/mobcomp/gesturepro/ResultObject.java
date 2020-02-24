package com.mobcomp.gesturepro;

import com.google.gson.annotations.SerializedName;

public class ResultObject {

    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    public String getMessage(){
        return message;
    }

    public boolean getSuccess(){
        return success;
    }
}
