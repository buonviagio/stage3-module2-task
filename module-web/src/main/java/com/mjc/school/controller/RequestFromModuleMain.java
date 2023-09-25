package com.mjc.school.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestFromModuleMain {
    private final Map<String, String> params;

    public RequestFromModuleMain() {
        params = new HashMap<>();
    }

    public RequestFromModuleMain(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }
}

