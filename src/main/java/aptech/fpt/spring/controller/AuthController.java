package aptech.fpt.spring.controller;

import aptech.fpt.spring.entity.Product;
import aptech.fpt.spring.entity.User;
import aptech.fpt.spring.entity.UserValidator;
import aptech.fpt.spring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    UserModel userModel;


        //    LOGIN
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String checkLogin(String username, String password, Model model){
        User check = userModel.checkLogin(username, password);
        if(check != null){
            model.addAttribute("username", username);
            return "redirect:product/list";
        }
        return "login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(Model model){
        return "login";
    }

//            MANAGE
    @RequestMapping(path = "/user/create", method = RequestMethod.GET)
    public String createUser(@ModelAttribute User user){return "user-form";}

    @RequestMapping(path = "/user/create", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result){
        System.out.println("username: " + user.getUsername());
        new UserValidator().validate(user, result);
        if(result.hasErrors()) return "user-form";
        userModel.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping(path = "/user/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable int id, Model model){
        Optional<User> optionalUser = userModel.findById(id);
        if(optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            return "user-form";
        }
        return "not-found";
    }

    @RequestMapping(path = "/user/edit/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable int id, @Valid User user, BindingResult result, Model model){
        Optional<User> optionalUser = userModel.findById(id);
        if(optionalUser.isPresent()){
            if(result.hasErrors()) return "user-form";
            userModel.save(user);
            return "redirect:/user/list";
        }
        return "not-found";
    }

    @RequestMapping(path = "/user/delete/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id){
        Optional<User> optionalUser = userModel.findById(id);
        if(optionalUser.isPresent()){
            userModel.delete(optionalUser.get());
            return "redirect:/user/list";
        }
        return "not-found";
    }

    @RequestMapping(path = "/user/list", method = RequestMethod.GET)
    public String getListUser(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        Page<User> pagination = userModel.findAll(PageRequest.of(page - 1, limit));
        model.addAttribute("pagination", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("pagination", pagination);
        return "user-list";
    }

    @RequestMapping(path = "/user/search", method = RequestMethod.POST)
    public String searchUser(String username, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        Page<User> pagination = userModel.findByUsername(username, PageRequest.of(page - 1, limit));
        model.addAttribute("pagination", pagination);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("datetime", Calendar.getInstance().getTime());
        return "user-list";
    }

}
