package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Category;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.CategoryModel;
import com.application.electronic_book.repository.CategoryRepository;
import com.application.electronic_book.service.others.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryModel create(CategoryModel categoryModel) {
        Category category = new Category(categoryModel.getName());

        categoryRepository.save(category);

        categoryModel.setId(category.getId());
        return categoryModel;
    }

    @Override
    public String delete(Long id) {
        Category category = getEntityById(id);

        categoryRepository.delete(category);

        return "Category with id:" + id + " was deleted";
    }

    @Override
    public Category getEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EBookException("Category with id:" + id + " was not found"));
    }

    @Override
    public CategoryModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<CategoryModel> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> toModel(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryModel toModel(Category category) {
        return new CategoryModel(category.getId(), category.getName());
    }

    @Override
    public CategoryModel update(CategoryModel categoryModel) {
        Category category = getEntityById(categoryModel.getId());

        String name = category.getName();

        if (name != null){
            category.setName(name);
        }

        categoryRepository.save(category);
        return categoryModel;
    }
}
