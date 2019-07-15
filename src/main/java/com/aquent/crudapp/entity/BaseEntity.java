package com.aquent.crudapp.entity;

import java.util.Date;

interface BaseEntity {
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getLastModifiedDate();
    void setLastModifiedDate(Date lastModifiedDate);
}
