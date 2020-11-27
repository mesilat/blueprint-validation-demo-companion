package com.mesilat.cube.product;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.mesilat.cube.AbstractDataService;
import com.mesilat.cube.Util;
import javax.inject.Inject;
import net.java.ao.DBParam;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl extends AbstractDataService<Product> implements ProductService {
    @Override
    public void save(ObjectNode product) {
        ao.executeInTransaction(() -> {
            Long id = Util.getLong(product, "id");
            Product p = ao.get(Product.class, id);
            if (p == null) {
                p = ao.create(Product.class, new DBParam("ID", id));
            }
            p.setTitle(Util.getText(product, "title"));
            p.setCategory(Util.getText(product, "category"));
            p.setManager(Util.getText(product, "manager", "fullName"));
            p.save();
            return null;
        });
    }

    @Override
    public ObjectNode toObjectNode(Product product) {
        ObjectNode node = mapper.createObjectNode();
        node.put("id", product.getID());
        node.put("title", product.getTitle());
        node.put("category", product.getCategory());
        ObjectNode manager = mapper.createObjectNode();
        manager.put("fullName", product.getManager());
        node.put("manager", manager);
        return node;
    }

    @Inject
    public ProductServiceImpl(@JiraImport ActiveObjects ao) {
        super(ao, new ObjectMapper(), Product.class);
    }
}
