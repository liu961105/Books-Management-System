package com.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Language;
import com.book.domain.ResultEntity;
import com.book.service.LanguageService;

@Controller
@RequestMapping("bookLanguage")
public class LanguageController extends BaseController {
	@Autowired
	private LanguageService languageService;

	@RequestMapping("getBooKLanguage")
	public ModelAndView getBooKLanguage() {
		List<Language> allLanguage = languageService.getAllLanguage();
		ModelAndView modelAndView = new ModelAndView("language_list");
		modelAndView.addObject("allLanguage", allLanguage);
		return modelAndView;
	}

	/**
	 * 跳转新增
	 * 
	 * @return
	 */
	@RequestMapping("toAddLanguage")
	public ModelAndView toAddLanguage() {
		return new ModelAndView("language_add");
	}

	/**
	 * 保存
	 * 
	 * @param language
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("languageSave")
	public String languageSave(Language language, RedirectAttributes redirectAttributes) {
		int success = languageService.saveLanguage(language);
		if (success > 0) {
			redirectAttributes.addFlashAttribute("succ", "添加成功！");
			return "redirect:/bookLanguage/getBooKLanguage";
		} else {
			redirectAttributes.addFlashAttribute("succ", "添加失败！");
			return "redirect:/bookLanguage/getBooKLanguage";
		}
	}

	/**
	 * 编辑
	 */
	@RequestMapping("editLanguage")
	public ModelAndView editLanguage(String id) {
		Language language = languageService.findById(id);
		ModelAndView modelAndView = new ModelAndView("language_edit");
		modelAndView.addObject("language", language);
		return modelAndView;
	}

	@RequestMapping("editLanguage_do")
	public String editLanguage_do(Language language, RedirectAttributes redirectAttributes) {
		boolean success = languageService.editById(language);
		if (success) {
			redirectAttributes.addFlashAttribute("succ", "修改成功！");
			return "redirect:/bookLanguage/getBooKLanguage";
		} else {
			redirectAttributes.addFlashAttribute("succ", "修改失败！");
			return "redirect:/bookLanguage/getBooKLanguage";
		}
	}

	@RequestMapping("deleteLanguage")
	@ResponseBody
	public ResultEntity deleteLanguage(String id) {
		ResultEntity res = new ResultEntity();
		try {
			boolean success = languageService.deleteLanguage(id);
			if (success) {
				res.setSuccess(SUCCESS);
				res.setMessage(DELETE_SUCCESS);
			} else {
				res.setSuccess(ERROR);
				res.setMessage(DELETE_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage(DELETE_ERROR + e.getMessage());
		}
		return res;
	}

	@RequestMapping("findLanguage")
	@ResponseBody
	public ResultEntity findLanguage() {
		ResultEntity res = new ResultEntity();
		List<Language> allLanguage = languageService.getAllLanguage();
		if (allLanguage.size() > 0) {
			res.setData(allLanguage);
			res.setSuccess(SUCCESS);
			res.setMessage(OPERATE_SUCCESS);
		} else {
			res.setSuccess(ERROR);
		}
		return res;
	}

}
