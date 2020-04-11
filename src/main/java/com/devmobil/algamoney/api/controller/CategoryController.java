package com.devmobil.algamoney.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.algamoney.api.model.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category> {


}
