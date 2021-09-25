package com.dtnse.repository;

import com.dtnse.model.Asset;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AssetRepository implements Repository<Asset, String>
{
    private Map<String, Asset> assets = new HashMap<>();

    private void loadAll()
    {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("reference/assets.json");
        if (stream != null)
        {
            JsonReader reader = new JsonReader(new InputStreamReader(stream));
            Type assetCollectionType = new TypeToken<Collection<Asset>>(){}.getType();
            List<Asset> assetList = new Gson().fromJson(reader, assetCollectionType);
            assets = assetList.stream().collect(Collectors.toMap(Asset::getQuadKey, Function.identity()));
        }
    }

    @Override
    public Asset getById(String id)
    {
        if (assets.isEmpty())
        {
            loadAll();
        }

        return assets.get(id);
    }
}
