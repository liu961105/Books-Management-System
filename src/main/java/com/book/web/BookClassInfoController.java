package com.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Admin;
import com.book.domain.ClassInfo;
import com.book.service.ClassInfoService;

/**
 * 图书分类
 * 
 * @author LZN
 *
 */
@Controller
@RequestMapping("classInfo")
public class BookClassInfoController extends BaseController {
	@Autowired
	private ClassInfoService classInfoService;

	/*
	 * 获取图书全部分类
	 */
	@RequestMapping("getClassInfo")
	public ModelAndView getClassInfo() {
		List<ClassInfo> classInfos = classInfoService.getClassInfos();
		ModelAndView modelAndView = new ModelAndView("book_classInfo");
		modelAndView.addObject("classInfos", classInfos);
		return modelAndView;
	}
	@RequestMapping("toAddClassInfo")
	public ModelAndView toAddClassInfo(){
		return new ModelAndView("book_addClassInfo");
	}
	@RequestMapping("classInfoSave")
	public String classInfoSave(ClassInfo classInfo, RedirectAttributes redirectAttributes) {
		int succ = classInfoService.classInfoSave(classInfo);
		if (succ > 0) {
			redirectAttributes.addFlashAttribute("succ", "图书分类添加成功！");
			return "redirect:/classInfo/getClassInfo";
		} else {
			redirectAttributes.addFlashAttribute("succ", "图书分类添加失败！");
			return "redirect:/classInfo/getClassInfo";
		}
	}

}
