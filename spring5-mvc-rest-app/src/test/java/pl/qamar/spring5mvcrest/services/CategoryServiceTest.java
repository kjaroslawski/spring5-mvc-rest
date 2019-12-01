package pl.qamar.spring5mvcrest.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.qamar.spring5mvcrest.api.v1.mapper.CategoryMapper;
import pl.qamar.spring5mvcrest.api.v1.model.CategoryDTO;
import pl.qamar.spring5mvcrest.domain.Category;
import pl.qamar.spring5mvcrest.repositories.CategoryRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private static final Long ID = 2L;
    private static final String NAME = "Jimmy";
    private CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() {

        //Given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //When
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //Then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() {

        //Given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //When
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //Then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
