/**
 * 
 */
package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author tranhongquan
 *
 */
@Controller
public class LoginController {
	 @RequestMapping("/")
	    public String index() {
		 System.out.println("1");
	        return "MH001";
	    }
}
