package com.fedorizvekov.http.server.micronaut;

import io.micronaut.runtime.Micronaut;

public class HttpServerMicronaut {

    public static void main(String[] args) {
        Micronaut.run(HttpServerMicronaut.class, args);
    }

}
