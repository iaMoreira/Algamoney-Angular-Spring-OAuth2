package com.devmobil.algamoney.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.algamoney.api.model.Person;

@RestController
@RequestMapping("/persons")
public class PersonController extends BaseController<Person> {


}
