package com.aquent.crudapp.entity;

import java.util.Date;

interface Auditable {
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getLastModifiedDate();
    void setLastModifiedDate(Date lastModifiedDate);
}
