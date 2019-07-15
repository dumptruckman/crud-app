package com.aquent.crudapp.mapping.entity;

interface EntityMapping<Entity, DataTransferObject> {

    Entity toEntity(DataTransferObject dto, Entity entity);
    DataTransferObject fromEntity(Entity entity);

}

