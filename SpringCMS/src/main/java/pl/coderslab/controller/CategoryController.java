package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.CategoryDao;
import pl.coderslab.model.Category;

@Controller
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/saveCategory/{name}/{description}")
    @ResponseBody
    public String saveCategory(@PathVariable String name, @PathVariable String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        categoryDao.saveCategory(category);
        return "Kategoria: " + category.toString() + " dodana do bazy danych.";
    }

    @RequestMapping("/editCategory/{id}/{name}/{description}")
    @ResponseBody
    public String editCategory(@PathVariable int id, @PathVariable String name, @PathVariable String description) {
        Category temp = categoryDao.findById(id);
        temp.setName(name);
        temp.setDescription(description);
        categoryDao.update(temp);
        return "Kategoria: " + temp.toString() + " pomyślnie zaktualizowana.";
    }

    @RequestMapping("/findCategory/{id}")
    @ResponseBody
    public String findCategory(@PathVariable int id) {
        Category temp = categoryDao.findById(id);
        return "Wyszukana kategoria: " + temp.toString();
    }

    @ResponseBody
    @RequestMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        Category temp = categoryDao.findById(id);
        categoryDao.delete(temp);
        return "Kategoria: " + temp.toString() + " pomyślnie usunięta z bazy danych.";
    }

}
