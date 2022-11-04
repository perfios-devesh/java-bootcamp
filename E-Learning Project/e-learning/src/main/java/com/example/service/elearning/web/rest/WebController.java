package com.example.service.elearning.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/index")
    public String indexPage1() {
        return "index";
    }

    @RequestMapping("/addCourse")
    public String addCourse() {
        return "addCourse";
    }

    @RequestMapping("/addCourseBlobs")
    public String addCourseBlobs() {
        return "addCourseBlobs";
    }

    @RequestMapping("/allCourses")
    public String allCourses() {
        return "allCourses";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("/editCourse")
    public String editCourse() {
        return "editCourse";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/viewCourse")
    public String viewCourse() {
        return "viewCourse";
    }

    @RequestMapping("/viewVideo")
    public String viewVideo() {
        return "viewVideo";
    }
}
