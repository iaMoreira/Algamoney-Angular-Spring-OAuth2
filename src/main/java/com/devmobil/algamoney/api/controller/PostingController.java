package com.devmobil.algamoney.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmobil.algamoney.api.model.Posting;

@RestController
@RequestMapping("/postings")
public class PostingController extends  BaseController<Posting>{

}
