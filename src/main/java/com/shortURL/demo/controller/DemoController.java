package com.shortURL.demo.controller;

import com.shortURL.demo.model.ShortURL;
import com.shortURL.demo.service.ShortURLsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class DemoController {

    @Autowired
    ShortURLsService shortURLsService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String urlList(Model model) {
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        model.addAttribute("shortURL", new ShortURL());
        return "shortURLsList";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(Model model, ShortURL shortURL) {
        ShortURL s = shortURLsService.findOne(shortURL.getUrl());
        if(s == null) {
            shortURLsService.saveShortURLs(shortURL.getUrl());
        }
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        model.addAttribute("shortURL", new ShortURL());
        return "shortURLsList";
    }

    @RequestMapping(value={"/r","/r/{url}"}, method = RequestMethod.GET)
    public RedirectView resolveShortURL(Model model,
                                        HttpServletRequest request,
                                        @PathVariable(required = false, name = "url") String url) {
        RedirectView redirectView = new RedirectView();
        String redirectURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        ShortURL s = shortURLsService.findByShortURL(url);
        if (s != null) {
            s.setLastAccessed(new Date());
            s.setUsage(s.getUsage() + 1);
            shortURLsService.updateShortURLs(s);
            redirectURL = s.getUrl();
        }
        model.addAttribute("shortURLsList", shortURLsService.findAll());
        redirectView.setUrl(redirectURL);
        return redirectView;
    }
}
