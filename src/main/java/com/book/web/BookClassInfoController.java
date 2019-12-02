package com.book.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Admin;
import com.book.domain.ClassInfo;
import com.book.domain.ResultEntity;
import com.book.service.impl.ClassInfoService;

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
	public ModelAndView toAddClassInfo() {
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

	@RequestMapping("findClassInfos")
	@ResponseBody
	public ResultEntity findClassInfos() {
		ResultEntity res = new ResultEntity();
		List<ClassInfo> classInfos = classInfoService.getClassInfos();
		if (classInfos.size() > 0) {
			res.setData(classInfos);
			res.setSuccess(SUCCESS);
		} else {
			res.setSuccess(ERROR);
			res.setMessage(NULL);
		}
		return res;
	}

	/*
	 * 详情
	 */
	@RequestMapping("classInfoDetail")
	public ModelAndView classInfoDetail(HttpServletRequest request) {
		int classId = Integer.parseInt(request.getParameter("classId"));
		ClassInfo classInfo = classInfoService.findByClassId(classId);
		ModelAndView modelAndView = new ModelAndView("classInfo_detail");
		modelAndView.addObject("classInfo", classInfo);
		return modelAndView;
	}

	@RequestMapping("toClassInfoEdit")
	public ModelAndView toEdit(String classId) {
		ClassInfo classInfo = classInfoService.findByClassId(Integer.parseInt(classId));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("classInfo_edit");
		modelAndView.addObject("classInfo", classInfo);
		return modelAndView;
	}

	@RequestMapping("classInfoEdit")
	public String classInfoEdit(ClassInfo classInfo, RedirectAttributes redirectAttributes) {
		int classInfoSave = classInfoService.editClassInfo(classInfo);
		if (classInfoSave > 0) {
			redirectAttributes.addAttribute("succ", "图书分类修改成功！");
			return "redirect:/classInfo/getClassInfo";
		} else {
			redirectAttributes.addAttribute("error", "图书分类修改失败！");
			return "redirect:/classInfo/getClassInfo";
		}
	}

	/*
	 * 刪除
	 */
	@RequestMapping("classInfoDelete")
	@ResponseBody
	public ResultEntity classInfoDelete(int classId) {
		ResultEntity res = new ResultEntity();

		boolean success = classInfoService.deleteClassInfo(classId);
		if (success) {
			res.setSuccess(SUCCESS);
			res.setMessage(GET_SUCCESS);
		} else {
			res.setSuccess(ERROR);
			res.setMessage(DELETE_ERROR);
		}
		return res;
	}

}
