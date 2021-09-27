package com.dtnse.model;

public class Asset
{
    private String assetName;
    private String quadKey;
    private String assetOwner;

    public Asset(String assetName, String quadKey, String assetOwner)
    {
        this.assetName = assetName;
        this.quadKey = quadKey;
        this.assetOwner = assetOwner;
    }

    public String getAssetName()
    {
        return assetName;
    }

    public String getQuadKey()
    {
        return quadKey;
    }

    public String getAssetOwner()
    {
        return assetOwner;
    }
}
