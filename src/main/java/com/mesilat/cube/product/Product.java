package com.mesilat.cube.product;

import net.java.ao.Preload;
import net.java.ao.RawEntity;
import net.java.ao.schema.Indexed;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.PrimaryKey;
import net.java.ao.schema.StringLength;

@Preload
public interface Product extends RawEntity<Long> {
    public static String TABLE = "AO_2CAE32_PRODUCT";

    @NotNull
    @PrimaryKey(value = "ID")
    Long getID();

    @StringLength(255)
    String getTitle();
    void setTitle(String title);

    @Indexed
    @StringLength(255)
    String getCategory();
    void setCategory(String category);

    @Indexed
    @StringLength(255)
    String getManager();
    void setManager(String category);
}