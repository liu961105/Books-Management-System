package com.book.web;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
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
	public ResultEntity deleteAdmin(String  adminId) {
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
	public ModelAndView toEditAdmin(String  adminId) {
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
	@RequestMapping("writeExcel")
	@ResponseBody
	public void writeExcel(HttpServletResponse response) throws Exception {
		File file = new File("E:\\\\temp\\\\withoutHead1.xlsx");
		OutputStream outputStream = new FileOutputStream(file);
		ExcelWriter excelWriter = new ExcelWriter(getOutputStream("下载",response), ExcelTypeEnum.XLSX);
		Sheet sheet = new Sheet(1,0);
		sheet.setSheetName("sheet1");
		Table table = new Table(1);
		List<List<String >> titles = new ArrayList<>();
		titles.add(Arrays.asList("用户ID"));
		titles.add(Arrays.asList("名称"));
		titles.add(Arrays.asList("年龄"));
		titles.add(Arrays.asList("生日"));
		table.setHead(titles);
		List<List<String>> userList = new ArrayList<>();

		for (int i = 0; i < 100; i++) {

			userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));

		}

		excelWriter.write0(userList, sheet, table);

		excelWriter.finish();

 	}
	private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "no-store");
			response.addHeader("Cache-Control", "max-age=0");
			return response.getOutputStream();
		} catch (IOException e) {
			throw new Exception("导出excel表格失败!", e);
		}
	}

}
