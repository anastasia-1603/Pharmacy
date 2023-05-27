package com.example.task12.repository;

import java.util.List;

public interface RepositoryKeyObject<T, K> extends IRepository<T>
{
    List<T> getAllBy(K k);
}
