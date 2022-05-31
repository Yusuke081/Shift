package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

   /* @GetMapping
    public String index() {
        // SecurityContextHolderからAuthenticationオブジェクトを取得
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        
        //UserDetails principal = (UserDetails) authentication.getPrincipal();
        //public String hello(Model model) {
        //String name = principal.getUsername();
        //model.addAttribute("name", name);
            
       // Authenticationオブジェクトからユーザー情報を取得
        System.out.println(authentication.getName());  // ユーザー名を表示
        System.out.println(authentication.getAuthorities());  // 権限情報を表示

       //  Authenticationオブジェクトからユーザー情報を取得
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        System.out.println(principal.getUsername());  // ユーザー名を表示
        System.out.println(principal.getPassword());  // パスワードを表示
        System.out.println(principal.getAuthorities());  // 権限情報を表示
        
      
        
        return "index";
    }  */
    
    @GetMapping()
    public String user(Model model) {
        // SecurityContextHolderからAuthenticationオブジェクトを取得
        //SecurityContext context = SecurityContextHolder.getContext();
        //Authentication authentication = context.getAuthentication();
        
    	 /*  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (principal instanceof UserDetails) {
        String username = ((UserDetails)principal).getUsername();
        model.addAttribute("username", username);
        System.out.println(username);
        } else {
        String username = principal.toString();
        
        model.addAttribute("username", username);
        }*/
    	
    	SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
    	UserDetails principal = (UserDetails) authentication.getPrincipal();
    	String username = principal.getUsername();
    	model.addAttribute("username", username);
    	System.out.println(username);
    	
    	
        return "index";
        
    	}
  
    

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String newSignup(SignupForm signupForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated SignupForm signupForm, BindingResult result, Model model,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "signup";
        }

        if (userDetailsServiceImpl.isExistUser(signupForm.getUsername())) {
            model.addAttribute("signupError", "ユーザー名 " + signupForm.getUsername() + "は既に登録されています");
            return "signup";
        }

        try {
            userDetailsServiceImpl.register(signupForm.getUsername(), signupForm.getAddres(), signupForm.getPassword(), "ROLE_USER");
        } catch (DataAccessException e) {
            model.addAttribute("signupError", "ユーザー登録に失敗しました");
            return "signup";
        }

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken == false) {
            SecurityContextHolder.clearContext();
        }

        try {
            request.login(signupForm.getUsername(), signupForm.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    