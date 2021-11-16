package com.paydi.config.multitenancy;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (StringUtils.isBlank(TenantStorage.getCurrentTenant())) {
            TenantStorage.setCurrentTenant("default");
        }
        return TenantStorage.getCurrentTenant();
    }

}