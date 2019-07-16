package com.aquent.crudapp.mapping.entity;

/**
 * Handles the mapping between data transfer objects and entities
 * Useful for eliminating code duplication and
 * ensuring that data mapping is separated from
 * service code.
 *
 * @param <Entity> is the type of entity
 * @param <DataTransferObject> is the type of data transfer object
 */
interface EntityMapping<Entity, DataTransferObject> {

    /**
     * Converts a Data Transfer Object into an Entity
     *
     * @param dto is the Data Transfer Object
     * @param entity is the Entity instance to map into
     *
     * @return the updated entity associated with the Data Transfer Object
     */
    Entity toEntity(DataTransferObject dto, Entity entity);

    /**
     * Loads Entity data into a Data Transfer Object
     *
     * @param entity is the source Entity
     * @return a Data Transfer Object containing relevant data from the Entity
     */
    DataTransferObject fromEntity(Entity entity);

}

