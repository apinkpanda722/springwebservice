package com.panda.apink.springboot.web;

import com.panda.apink.springboot.config.auth.dto.SessionUser;
import com.panda.apink.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import com.panda.apink.springboot.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());


        if (user != null) {
            model.addAttribute("apink", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
