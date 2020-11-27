package com.mesilat.cube.company;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.mesilat.cube.BaseResource;
import javax.inject.Inject;
import javax.ws.rs.*;

@Scanned
@Path("/company")
public class CompanyResource extends BaseResource {
    @Inject
    public CompanyResource(CompanyService service) {
        super(service);
    }
}
