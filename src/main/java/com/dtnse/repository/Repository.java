package com.dtnse.repository;

import java.util.List;

public interface Repository<T, K>
{
    T getById(K id);
}
