package com.bieggerm.feedservice.app.ports.outgoing;

public interface MessageBroker {
    void sendMessage(String message, String topic);
}
