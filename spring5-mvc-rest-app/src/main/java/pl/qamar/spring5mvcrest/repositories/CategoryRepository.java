package pl.qamar.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.qamar.spring5mvcrest.domain.Category;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
