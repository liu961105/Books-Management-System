package com.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 该类为测试ZTree
 */
@Controller
@RequestMapping("ztree")
public class ZtreeController {
    @RequestMapping("ztree")
    public ModelAndView gotoZtree(){
        ModelAndView modelAndView = new ModelAndView("zTreeTest");
        return modelAndView;
    }
}
