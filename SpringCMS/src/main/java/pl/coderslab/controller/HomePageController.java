package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.awt.print.Book;
import java.util.List;

@Controller
public class HomePageController {

    @PersistenceContext
    EntityManager entityManager;


    @RequestMapping("/home")
    public String homePage(Model model) {

        Query query = entityManager.createQuery("SELECT a FROM Article a ORDER BY created DESC");
        query.setMaxResults(5);
        List<Article> articles = query.getResultList();
     //   articles.stream();
        model.addAttribute("article", articles);
   //     String result = "";

    //    for (Article item : articles) {
      //      int leng = item.getContent().length();
        //    if (leng < 200) {
          //      result += "Tytuł: " + item.getTitle() + ", utworzony: " + item.getCreated().toString() + ", pierwsze 200 znaków: " + item.getContent() + "\n";
           // } else {
             //   result += "Tytuł: " + item.getTitle() + ", utworzony: " + item.getCreated().toString() + ", pierwsze 200 znaków: " + item.getContent().substring(0, 200) + "\n";
           // }
       // }
        return "home";
    }
}
