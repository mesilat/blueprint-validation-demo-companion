package com.mesilat.cube;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public interface BaseDataService {
    ArrayNode list();
    ObjectNode get(Long id);
    void save(ObjectNode product);
    void delete(Long id);
}
