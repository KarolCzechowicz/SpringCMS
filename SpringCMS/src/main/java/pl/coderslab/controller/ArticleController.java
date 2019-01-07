package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.ArticleDao;
import pl.coderslab.model.Article;
import pl.coderslab.model.Author;
import pl.coderslab.model.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @RequestMapping("/saveArticle/{title}/{authorFirstName}/{authorLastName}/{content}")
    @ResponseBody
    public String saveArticle(@PathVariable String title, @PathVariable String authorFirstName, @PathVariable String authorLastName, @PathVariable String content) {
        Article article = new Article();
        article.setTitle(title);

        Author author = new Author();
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);
        article.setAuthor(author);

        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setName("Kategoria");
        category.setDescription("opis kategorii");
        categories.add(category);
        article.setCategories(categories);

        article.setContent(content);

        Date date = java.util.Calendar.getInstance().getTime();
        article.setCreated(date);
        articleDao.saveArticle(article);
        return "Artykuł: " + article.toString() + " dodany do bazy danych.";
    }

    @RequestMapping("/editArticle/{id}/{title}/{authorFirstName}/{authorLastName}/{content}")
    @ResponseBody
    public String editArticle(@PathVariable int id, @PathVariable String title, @PathVariable String authorFirstName, @PathVariable String authorLastName, @PathVariable String content) {
        Article temp = articleDao.findById(id);
        temp.setTitle(title);

        Author author = new Author();
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);
        temp.setAuthor(author);

        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setName("Kategoria");
        category.setDescription("opis kategorii");
        categories.add(category);
        temp.setCategories(categories);

        temp.setContent(content);

        Date date = java.util.Calendar.getInstance().getTime();
        temp.setUpdated(date);
        articleDao.update(temp);
        return "Artykuł: " + temp.toString() + " pomyślnie zaktualizowany.";
    }

    @RequestMapping("/findArticle/{id}")
    @ResponseBody
    public String findArticle(@PathVariable int id) {
        Article temp = articleDao.findById(id);
        return "Wyszukany artykuł: " + temp.toString();
    }

    @ResponseBody
    @RequestMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable int id) {
        Article temp = articleDao.findById(id);
        articleDao.delete(temp);
        return "Artykuł: " + temp.toString() + " pomyślnie usunięty z bazy danych.";
    }
}
