package com.example.demo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CalendarController {
    
    //@GetMapping("/calendar")
    //public static void() {
    //int endDay = ld.lengthOfMonth();
    
    //return "/";

    //}
	
	
	private static final int YEAR = Calendar.YEAR;
	private static final int MONTH = Calendar.MONTH;

	@GetMapping("calendar" )
	 public static String calendar(calendarForm form,Model model) {
		
		Calendar c = new GregorianCalendar(YEAR,MONTH,1);
		//int year = c.get(Calendar.YEAR);
	    //int month = c.get(Calendar.MONTH);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		model.addAttribute("days", days);
		
			
			
		
	 return "calendar";
	 }
}    




