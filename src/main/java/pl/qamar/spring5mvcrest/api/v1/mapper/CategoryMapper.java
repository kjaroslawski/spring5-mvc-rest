package pl.qamar.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapping;
import pl.qamar.spring5mvcrest.api.v1.model.CategoryDTO;
import pl.qamar.spring5mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
