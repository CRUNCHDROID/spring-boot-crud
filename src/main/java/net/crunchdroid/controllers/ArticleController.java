package net.crunchdroid.controllers;

import net.crunchdroid.entities.Article;
import net.crunchdroid.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author CrunchDroid
 */
@Controller
@SessionAttributes("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public String index(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "articles/index";
    }

    @GetMapping("/articles/search")
    public String search(@RequestParam(value = "term", required = false, defaultValue = "") String term, Model model) {
        model.addAttribute("articles", articleService.search(term));
        return "articles/index";
    }

    @GetMapping("/article/view/{slug}")
    public String view(@PathVariable String slug, Model model) {
        model.addAttribute("articleView", articleService.getArticle(slug));
        return "articles/view";
    }

    @GetMapping("/article/create")
    public String create(Article article) {
        return "articles/process";
    }

    @GetMapping("/article/update/{slug}")
    public String update(@PathVariable String slug, Model model) {
        model.addAttribute("article", articleService.getArticle(slug));
        return "articles/process";
    }

    @PostMapping("/article/process")
    public String process(Article article, SessionStatus sessionStatus) {
        articleService.save(article);
        sessionStatus.setComplete();
        return "redirect:/articles";
    }

    @GetMapping("/article/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles";
    }

}
