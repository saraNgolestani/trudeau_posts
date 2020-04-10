package io.github.sarangolestani.services;

import java.util.List;

public abstract class AbstractApiServices<T> {

    public abstract List<T> fetchContent(String query, int maxPosition) throws Exception;


}
