package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

   @Autowired
   ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDo (Model model){
       model.addAttribute("toDos", toDoRepository.findAll());
       return "toDolist";
    }
    @GetMapping("/add")
    public String toDoForm(Model model){
        model.addAttribute("toDo", new ToDo());
        return "toDoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid ToDo toDo,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "toDoform";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showToDo (@PathVariable("id") long id, Model model) {
        model.addAttribute("toDo", toDoRepository.findById(id).get());
        return "toDoshow";
    }
    @RequestMapping("update/{id}")
    public String updateList(@PathVariable("id") long id,
                             Model model){
    model.addAttribute("toDo", toDoRepository.findById(id).get());
    return "toDoform";
    }
    @RequestMapping("/delete/{id}")
    public String delList(@PathVariable("id") long id){
        return "redirect:/";
    }
}


