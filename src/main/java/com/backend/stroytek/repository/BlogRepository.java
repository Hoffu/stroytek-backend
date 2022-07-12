package com.backend.stroytek.repository;

import com.backend.stroytek.model.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
}
