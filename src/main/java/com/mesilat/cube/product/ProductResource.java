package com.mesilat.cube.product;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.mesilat.cube.BaseResource;
import javax.inject.Inject;
import javax.ws.rs.*;


@Scanned
@Path("/product")
public class ProductResource extends BaseResource {
    @Inject
    public ProductResource(ProductService service) {
        super(service);
    }
}
