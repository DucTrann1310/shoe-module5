package com.cg.service.categoryService;

import com.cg.model.Category;
import com.cg.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category, Long> {

    Category findCategoryByName(String name);
}
