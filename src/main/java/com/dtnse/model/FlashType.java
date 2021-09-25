package com.dtnse.model;

import com.google.gson.annotations.SerializedName;

public enum FlashType
{
    @SerializedName("0") CLOUD_TO_GROUND(0),
    @SerializedName("1") CLOUD_TO_CLOUD(1),
    @SerializedName("9") HEARTBEAT(9);

    private final int code;

    FlashType(int code)
    {
        this.code = code;
    }

    public int code()
    {
        return code;
    }
}
