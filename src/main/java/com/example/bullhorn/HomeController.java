package com.example.bullhorn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    //inject message repo
    @Autowired
    MessageRepo messageRepo;

    //to show list
    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messageRepo.findAll());
        return "list";
    }

    //add message
    @GetMapping("/add")
    public String msgForm(Model model){
        model.addAttribute("message", new Message());
        return "msgform";
    }

    //Validate and save
    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "msgform";
        }
        messageRepo.save(message);
        return "redirect:/";
    }

    @RequestMapping("/showmsg/{id}")
    public String showMsg(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepo.findById(id).get());
        return "showmsg";

    }

    @RequestMapping("/edit/{id}")
    public String editMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepo.findById(id).get());
        return "msgform";
    }

    @RequestMapping("/delete/{id}")
    public String delMessage(@PathVariable("id") long id){
        messageRepo.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
