package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    private static final String NAME = "Joe";
    private static final long ID = 1L;
    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {

        //Given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //When
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //Then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
