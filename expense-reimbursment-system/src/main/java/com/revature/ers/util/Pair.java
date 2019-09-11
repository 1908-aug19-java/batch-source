package com.revature.ers.util;

public class Pair
{
    private String key;
    private String value;

    public Pair(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getValue()
    {
        return this.value;
    }

    public String setKey(String key)
    {
        return this.key = key;
    }

    public String setValue(String value)
    {
        return this.value = value;
    }
}