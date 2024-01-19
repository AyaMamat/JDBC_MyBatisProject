package com.laba.solvd.pharmacy.parsers.parsers;

public interface Parser<T> {
    public T parse(String path);
}
