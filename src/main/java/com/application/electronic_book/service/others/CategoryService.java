package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Category;
import com.application.electronic_book.model.others.CategoryModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface CategoryService extends
        Create<CategoryModel, CategoryModel>,
        Read<CategoryModel, Category>,
        Delete,
        Update<CategoryModel>,
        ToModelConverter<CategoryModel, Category>
{
}
