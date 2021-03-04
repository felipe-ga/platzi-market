package com.platzi.platzimarket.persitence.mapper;

import com.platzi.platzimarket.domain.Category;
import com.platzi.platzimarket.persitence.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @Mapping(target = "productos",ignore = true)
    Categoria toCategoria(Category category);
}
