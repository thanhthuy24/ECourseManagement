/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.CategoryDTO;
import com.htt.pojo.Category;
import com.htt.service.CategoryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCategoryController {

    @Autowired
    private CategoryService cateSer;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> list() {
        List<Category> categories = this.cateSer.getCates();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
