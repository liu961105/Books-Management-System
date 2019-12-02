package com.book.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Admin;
import com.book.domain.ResultEntity;
import com.book.service.impl.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	@Autowired
	private AdminService adminService;

	/*
	 * 管理员列表
	 */
	@RequestMapping("allAdmin")
	public ModelAndView allBooks() {
		List<Admin> admins = adminService.getAllAdmin();
		ModelAndView modelAndView = new ModelAndView("admin_list");
		modelAndView.addObject("admins", admins);
		return modelAndView;
	}

	@RequestMapping("toAddadmin")
	public ModelAndView toAddAdmin(HttpServletRequest request) {
		return new ModelAndView("admin_add");

	}

	/*
	 * 添加管理员
	 */
	@RequestMapping("adminSave")
	public String adminSave(Admin admin, RedirectAttributes redirectAttributes) {
		int addAdmin = adminService.addAdmin(admin);
		if (addAdmin > 0) {
			redirectAttributes.addFlashAttribute("succ", "管理员添加成功！");
			return "redirect:/admin/allAdmin";
		} else {
			redirectAttributes.addFlashAttribute("succ", "管理员添加失败！");
			return "redirect:/admin/allAdmin";
		}
	}

	@RequestMapping("deleteAdmin")
	@ResponseBody
	public ResultEntity deleteAdmin(int adminId) {
		ResultEntity res = new ResultEntity();
		try {
			boolean success = adminService.deleteAdmin(adminId);
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

	@RequestMapping("toEditAdmin")
	public ModelAndView toEditAdmin(Integer adminId) {
		Admin admin = adminService.findByAdminId(adminId);
		ModelAndView modelAndView = new ModelAndView("admin_edit");
		modelAndView.addObject("admin", admin);
		return modelAndView;
	}

	@RequestMapping("adminEdit")
	public String adminEdit(Admin admin, RedirectAttributes redirectAttributes) {
		try {
			boolean success = adminService.editAdmin(admin);
			if (success) {
				redirectAttributes.addAttribute("succ", "修改成功！");
			} else {
				redirectAttributes.addAttribute("error", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/allAdmin";
	}

}
