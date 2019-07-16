package com.aquent.crudapp.entity;

import java.util.Date;

/**
 * Covers fields common to entities.
 *
 * In this case, audit fields.
 * These are not actively used in the application,
 * however they're always useful to add from the inception
 * of the project.
 */
interface BaseEntity {
    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getLastModifiedDate();
    void setLastModifiedDate(Date lastModifiedDate);
}
