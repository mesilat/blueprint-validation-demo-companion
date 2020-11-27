package com.mesilat.cube;

import com.atlassian.activeobjects.external.ActiveObjects;
import net.java.ao.RawEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

public abstract class AbstractDataService<T extends RawEntity<Long>> implements BaseDataService {
    protected final ActiveObjects ao;
    protected final ObjectMapper mapper;
    protected final Class clazz;

    @Override
    public ArrayNode list() {
        ArrayNode arr = mapper.createArrayNode();
        for (RawEntity rec: ao.find(clazz)) {
            arr.add(toObjectNode((T)rec));
        }
        return arr;
    }
    @Override
    public ObjectNode get(Long id) {
        T rec = (T)ao.get(clazz, id);
        if (rec == null) {
            return null;
        }
        return toObjectNode(rec);
    }
    @Override
    public void delete(Long id) {
        ao.deleteWithSQL(clazz, "ID = ?", id);
    }

    public AbstractDataService(ActiveObjects ao, ObjectMapper mapper, Class clazz) {
        this.ao = ao;
        this.mapper = mapper;
        this.clazz = clazz;
    }
    
    public abstract ObjectNode toObjectNode(T object);
}
