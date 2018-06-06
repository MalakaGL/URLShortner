package com.shortURL.demo.controller;

import com.shortURL.demo.model.ShortURL;
import com.shortURL.demo.service.ShortURLsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class DemoController {

    @Autowired
    ShortURLsService shortURLsService;

    @RequestMapping(value="/")
    public String urlList(Model model) {
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        model.addAttribute("shortURL", new ShortURL());
        return "shortURLsList";
    }

    @RequestMapping(value={"/r","/r/{url}"}, method = RequestMethod.GET)
    public RedirectView resolveShortURL(Model model, @PathVariable(required = false, name = "url") String url) {
        RedirectView redirectView = new RedirectView();
        String redirectURL = "http://localhost:4555/";
        ShortURL s = shortURLsService.findByShortURL(url);
        if (s != null) {
            redirectURL = s.getUrl();
        }
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        redirectView.setUrl(redirectURL);
        return redirectView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, ShortURL shortURL) {
        ShortURL s = shortURLsService.findOne(shortURL.getUrl());
        if(s == null) {
            shortURLsService.saveShortURLs(shortURL.getUrl());
        }
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        model.addAttribute("shortURL", new ShortURL());
        return "shortURLsList";
    }
}
