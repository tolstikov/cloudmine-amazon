package com.cloudaware.cloudmine.amazon;

public interface AmazonClientCreate<T> {
    ClientWrapper<T> create();
}
