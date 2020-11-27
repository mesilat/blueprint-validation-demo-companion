package com.mesilat.cube.contract;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.mesilat.cube.BaseResource;
import javax.inject.Inject;
import javax.ws.rs.*;

@Scanned
@Path("/contract")
public class ContractResource extends BaseResource {
    @Inject
    public ContractResource(ContractService service) {
        super(service);
    }
}