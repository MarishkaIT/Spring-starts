package com.rish.spring.http.controller;

import com.rish.spring.database.entity.Role;
import com.rish.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto) {

        model.addAttribute("user", userReadDto);

        return "greeting/hello";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, HttpServletRequest request,
                              @RequestParam("age") Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {
        String requestParameter = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") UserReadDto user, Model model) {

        return "greeting/bye";
    }
}
