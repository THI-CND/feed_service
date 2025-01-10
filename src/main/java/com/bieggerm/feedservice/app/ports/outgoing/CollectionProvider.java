package com.bieggerm.feedservice.app.ports.outgoing;

import com.bieggerm.feedservice.domain.model.CollectionElement;

public interface CollectionProvider {

    public CollectionElement[] getCollections();
}
