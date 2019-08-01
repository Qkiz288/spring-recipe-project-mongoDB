package com.kkukielka.springrecipeproject.repositories;

import com.kkukielka.springrecipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
