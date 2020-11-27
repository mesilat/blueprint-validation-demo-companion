package com.mesilat.cube.company;

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
public class CompanyServiceImpl extends AbstractDataService<Company> implements CompanyService {
    @Override
    public void save(ObjectNode company) {
        ao.executeInTransaction(() -> {
            Long id = Util.getLong(company, "id");
            Company c = ao.get(Company.class, id);
            if (c == null) {
                c = ao.create(Company.class, new DBParam("ID", id));
            }
            c.setTitle(Util.getText(company, "title"));
            c.setCountry(Util.getText(company, "country"));
            c.setManager(Util.getText(company, "manager", "fullName"));
            c.save();
            return null;
        });
    }

    @Override
    public ObjectNode toObjectNode(Company company) {
        ObjectNode node = mapper.createObjectNode();
        node.put("id", company.getID());
        node.put("title", company.getTitle());
        node.put("country", company.getCountry());
        ObjectNode manager = mapper.createObjectNode();
        manager.put("fullName", company.getManager());
        node.put("manager", manager);
        return node;
    }

    @Inject
    public CompanyServiceImpl(@JiraImport ActiveObjects ao) {
        super(ao, new ObjectMapper(), Company.class);
    }
}
