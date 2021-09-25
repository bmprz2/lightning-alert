package com.dtnse.util;

import com.dtnse.model.Asset;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlertPrinter
{
    private static final Set<String> alertedQuadKeys = new HashSet<>();

    public static boolean print(Asset asset)
    {
        boolean success = false;
        if (!alertedQuadKeys.contains(asset.getQuadKey()))
        {
            System.out.println(MessageFormat.format("lightning alert for {0}:{1}", asset.getAssetOwner(), asset.getAssetName()));
            alertedQuadKeys.add(asset.getQuadKey());
            success = true;
        }
        return success;
    }

    public static boolean isPrinted(String key)
    {
        return alertedQuadKeys.contains(key);
    }

}