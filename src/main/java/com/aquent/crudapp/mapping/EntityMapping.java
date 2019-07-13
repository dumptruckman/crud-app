package com.aquent.crudapp.mapping;

interface EntityMapping<Entity, DataTransferObject> {

    Entity toEntity(DataTransferObject dto, Entity entity);
    DataTransferObject fromEntity(Entity entity);

}

