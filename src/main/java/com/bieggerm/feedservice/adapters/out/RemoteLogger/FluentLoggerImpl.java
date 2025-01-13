package com.bieggerm.feedservice.adapters.out.RemoteLogger;

import com.bieggerm.feedservice.app.ports.outgoing.RemoteLogger;
import jakarta.annotation.PostConstruct;
import org.fluentd.logger.FluentLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FluentLoggerImpl implements RemoteLogger {

    @Value("${fluentd.host}")
    private String host;
    @Value("${fluentd.port}")
    private int port;

    private FluentLogger LOG;


    @PostConstruct
    public void init() {
        LOG = FluentLogger.getLogger("feed-service", host, port);
    }

    @Override
    public void log(String tag, String key, Object value) {
        LOG.log(tag, Map.of(key, value));
    }

    @Override
    public void log(String tag, Map<String, Object> data) {
        LOG.log(tag, data);
    }
}
