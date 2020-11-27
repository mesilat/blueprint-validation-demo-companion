package com.mesilat.cube.contract;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.mesilat.cube.AbstractDataService;
import com.mesilat.cube.Util;
import java.text.ParseException;
import javax.inject.Inject;
import net.java.ao.DBParam;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class ContractServiceImpl extends AbstractDataService<Contract> implements ContractService {
    @Override
    public void save(ObjectNode contract) {
        ao.executeInTransaction(() -> {
            Long id = Util.getLong(contract, "id");
            Contract c = ao.get(Contract.class, id);
            if (c == null) {
                c = ao.create(Contract.class, new DBParam("ID", id));
            }
            c.setContractNumber(Util.getText(contract, "number"));
            try {
                c.setContractDate(Util.getDate(Util.YMD, contract, "contract-date"));
            } catch (ParseException ex) {
                // c.setContractDate(null);
            }
            try {
                c.setEndDate(Util.getDate(Util.YMD, contract, "contract-end-date"));
            } catch (ParseException ex) {
                // c.setContractDate(null);
            }
            c.setBusinessUnit(Util.getText(contract, "business-unit"));
            c.setAmount(Util.getDouble(contract, "amount"));
            c.setVAT(Util.getDouble(contract, "vat"));
            c.setCustomer(Util.getText(contract, "customer"));
            c.save();
            return null;
        });
    }

    @Override
    public ObjectNode toObjectNode(Contract contract) {
        ObjectNode node = mapper.createObjectNode();
        node.put("id", contract.getID());
        node.put("number", contract.getContractNumber());
        node.put("contract-date", Util.toObjectNode(mapper, contract.getContractDate()));
        node.put("contract-end-date", Util.toObjectNode(mapper, contract.getEndDate()));
        node.put("business-unit", contract.getBusinessUnit());
        node.put("amount", contract.getAmount());
        node.put("vat", contract.getVAT());
        node.put("customer", contract.getCustomer());
        return node;
    }

    @Inject
    public ContractServiceImpl(@JiraImport ActiveObjects ao) {
        super(ao, new ObjectMapper(), Contract.class);
    }
}
