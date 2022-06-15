package com.example.demo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CalendarController {
	@Autowired
    CalendarService calendarService;
	
    //@GetMapping("/calendar")
    //public static void() {
    //int endDay = ld.lengthOfMonth();
    
    //return "/";

    //}
	
	
	private static final int YEAR = Calendar.YEAR;
	private static final int MONTH = Calendar.MONTH;

	@GetMapping("calendar")
	 public String list(CalendarForm form ,Model model) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		
		Calendar c = new GregorianCalendar(year,month,month+1);
		//int year = c.get(Calendar.YEAR);
	    //int month = c.get(Calendar.MONTH);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
    	UserDetails principal = (UserDetails) authentication.getPrincipal();
    	String username = principal.getUsername();
    	model.addAttribute("username", username);
    	//System.out.println(username);
		
		
		
		model.addAttribute("days", days);
		model.addAttribute("month", month+1);
		model.addAttribute("year", year);
		List<CalendarEntity> calendarEntity = calendarService.findAll();
        model.addAttribute("calendarEntity", calendarEntity);
        
			
		
	 return "calendar";
	}
	


	@PostMapping("calendar")
	String output(@ModelAttribute CalendarForm calendarForm, Model model){
		CalendarEntity calendarEntity = new CalendarEntity();
		BeanUtils.copyProperties(calendarForm, calendarEntity);
		calendarService.insert(calendarEntity);
		
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
    	UserDetails principal = (UserDetails) authentication.getPrincipal();
    	String username = principal.getUsername();
    	model.addAttribute("username", username);
	
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
}    




