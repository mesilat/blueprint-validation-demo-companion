package com.mesilat.cube.contract;

import java.util.Date;
import net.java.ao.Preload;
import net.java.ao.RawEntity;
import net.java.ao.schema.Indexed;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.PrimaryKey;
import net.java.ao.schema.StringLength;

@Preload
public interface Contract extends RawEntity<Long> {
    public static String TABLE = "AO_2CAE32_CONTRACT";

    @NotNull
    @PrimaryKey(value = "ID")
    Long getID();

    @StringLength(64)
    String getContractNumber();
    void setContractNumber(String contractNumber);

    Date getContractDate();
    void setContractDate(Date contractDate);

    Date getEndDate();
    void setEndDate(Date endDate);

    @Indexed
    @StringLength(255)
    String getBusinessUnit();
    void setBusinessUnit(String businessUnit);

    Double getAmount();
    void setAmount(Double amount);

    Double getVAT();
    void setVAT(Double vat);

    @Indexed
    @StringLength(255)
    String getCustomer();
    void setCustomer(String customer);
}