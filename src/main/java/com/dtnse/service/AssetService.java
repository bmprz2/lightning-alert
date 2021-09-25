package com.dtnse.service;

import com.dtnse.model.Asset;
import com.dtnse.repository.AssetRepository;
import com.dtnse.repository.Repository;

public class AssetService
{
    private final Repository<Asset, String> repository = new AssetRepository();

    public Asset get(String id)
    {
        return repository.getById(id);
    }
}
