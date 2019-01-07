package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;

@Controller
public class AuthorController {

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping("/saveAuthor/{firstName}/{lastName}")
    @ResponseBody
    public String saveAuthor(@PathVariable String firstName, @PathVariable String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.saveAuthor(author);
        return "Autor: " + author.toString() + " dodany do bazy danych.";
    }

    @RequestMapping("/editAuthor/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String editAuthor(@PathVariable int id, @PathVariable String firstName, @PathVariable String lastName) {
        Author temp = authorDao.findById(id);
        temp.setFirstName(firstName);
        temp.setLastName(lastName);
        authorDao.update(temp);
        return "Autor: " + temp.toString() + " pomyślnie zaktualizowany.";
    }

    @RequestMapping("/findAuthor/{id}")
    @ResponseBody
    public String findAuthor(@PathVariable int id) {
        Author temp = authorDao.findById(id);
        return "Wyszukany autor to: " + temp.toString();
    }

    @ResponseBody
    @RequestMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable int id) {
        Author temp = authorDao.findById(id);
        authorDao.delete(temp);
        return "Autor: " + temp.toString() + " usunięty";
    }
}
