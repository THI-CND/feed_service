package com.bieggerm.feedservice.app.ports.outgoing;

import java.util.Map;

public interface RemoteLogger {

    void init();

    void log(String tag, String key, Object value);
    void log(String tag, Map<String, Object> data);
}
