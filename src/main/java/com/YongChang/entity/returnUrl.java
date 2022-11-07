package com.YongChang.entity;

import org.springframework.context.annotation.Bean;

public class returnUrl {
    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getOut_Id() {
        return Out_Id;
    }

    public void setOut_Id(String out_Id) {
        Out_Id = out_Id;
    }

    private String customer_Id;
    private String Out_Id;

}
