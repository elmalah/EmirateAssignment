package sample.com.emiratesauctionassignment.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elmalah on 3/5/2017.
 */

public class BaseModel {
    public Message messages;

    public class Message {
        @SerializedName("code")
        public int code;
        @SerializedName("message")
        public String message;
    }}
