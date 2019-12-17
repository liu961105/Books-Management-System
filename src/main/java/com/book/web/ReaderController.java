package com.book.web;

import com.book.domain.Book;
import com.book.domain.Lend;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.service.impl.LendService;
import com.book.service.impl.LoginService;
import com.book.service.impl.ReaderCardService;
import com.book.service.impl.ReaderInfoService;
import com.book.util.QRCodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 读者管理
 * 
 * @author LZN
 *
 */
@Controller
public class ReaderController {

	private ReaderInfoService readerInfoService;
	
	

	@Autowired
	public void setReaderInfoService(ReaderInfoService readerInfoService) {
		this.readerInfoService = readerInfoService;
	}

	private LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	private ReaderCardService readerCardService;

	@Autowired
	public void setReaderCardService(ReaderCardService readerCardService) {
		this.readerCardService = readerCardService;
	}

	@Autowired
	private LendService lendService;

	// 读者列表跳转
	@RequestMapping("allreaders.html")
	public ModelAndView allBooks() {
		ArrayList<ReaderInfo> readers = readerInfoService.readerInfos();
		ModelAndView modelAndView = new ModelAndView("admin_readers");
		modelAndView.addObject("readers", readers);
		return modelAndView;
	}

	/**
	 * 删除读者
	 * 
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("reader_delete.html")
	public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes,String readerId) {
		boolean success = readerInfoService.deleteReaderInfo(readerId);
		boolean readCard = readerCardService.deleteReader(readerId);
		if (success && readCard) {
			redirectAttributes.addFlashAttribute("succ", "删除成功！");
			return "redirect:/allreaders.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "删除失败！");
			return "redirect:/allreaders.html";
		}

	}

	// 读者功能
	@RequestMapping("/reader_info.html")
	public ModelAndView toReaderInfo(HttpServletRequest request) {
		ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
		ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
		ModelAndView modelAndView = new ModelAndView("reader_info");
		modelAndView.addObject("readerinfo", readerInfo);
		return modelAndView;
	}

	@RequestMapping("reader_edit.html")
	public ModelAndView readerInfoEdit(HttpServletRequest request,String readerId) {
		ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
		ModelAndView modelAndView = new ModelAndView("admin_reader_edit");
		modelAndView.addObject("readerInfo", readerInfo);
		return modelAndView;
	}

	// 跳转到二维码
	@RequestMapping("reader_show")
	public ModelAndView readInfoShow(HttpServletRequest request) {
		String readerId = request.getParameter("readerId");
		ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
		String text = readerInfo.getReaderId();
		String destPath = "E:\\img\\" + readerInfo.getName() + readerInfo.getReaderId() + ".jpg";
		// 取出id
		try {
			QRCodeUtil.encode(text + "", destPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("admin_reader_show");
		modelAndView.addObject("readerInfo", readerInfo);

		return modelAndView;

	}

	@RequestMapping("reader_edit_do.html")
	public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address,
			String telcode, RedirectAttributes redirectAttributes) {
		String readerId = request.getParameter("id");
		ReaderCard readerCard = loginService.findReaderCardByUserId(readerId);
		String oldName = readerCard.getName();
		if (!oldName.equals(name)) {
			boolean succo = readerCardService.updateName(readerId, name);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nbirth = new Date();
			try {
				java.util.Date date = sdf.parse(birth);
				nbirth = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ReaderInfo readerInfo = new ReaderInfo();
			readerInfo.setAddress(address);
			readerInfo.setBirth(nbirth);
			readerInfo.setName(name);
			readerInfo.setReaderId(readerId);
			readerInfo.setTelcode(telcode);
			readerInfo.setSex(sex);
			boolean succ = readerInfoService.editReaderInfo(readerInfo);
			if (succo && succ) {
				redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
				return "redirect:/allreaders.html";
			} else {
				redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
				return "redirect:/allreaders.html";
			}
		} else {
			System.out.println("部分修改");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nbirth = new Date();
			try {
				java.util.Date date = sdf.parse(birth);
				nbirth = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ReaderInfo readerInfo = new ReaderInfo();
			readerInfo.setAddress(address);
			readerInfo.setBirth(nbirth);
			readerInfo.setName(name);
			readerInfo.setReaderId(readerId);
			readerInfo.setTelcode(telcode);
			readerInfo.setSex(sex);

			boolean succ = readerInfoService.editReaderInfo(readerInfo);
			if (succ) {
				redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
				return "redirect:/allreaders.html";
			} else {
				redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
				return "redirect:/allreaders.html";
			}
		}

	}

	// 读者新增跳转
	@RequestMapping("reader_add.html")
	public ModelAndView readerInfoAdd() {
		ModelAndView modelAndView = new ModelAndView("admin_reader_add");
		return modelAndView;

	}

	// 用户功能--进入修改密码页面
	@RequestMapping("reader_repasswd.html")
	public ModelAndView readerRePasswd() {
		ModelAndView modelAndView = new ModelAndView("reader_repasswd");
		return modelAndView;
	}

	// 用户功能--修改密码执行
	@RequestMapping("reader_repasswd_do.html")
	public String readerRePasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd,
			RedirectAttributes redirectAttributes) {
		ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
		String readerId = readerCard.getReaderId();
		String passwd = readerCard.getPasswd();

		if (newPasswd.equals(reNewPasswd)) {
			if (passwd.equals(oldPasswd)) {
				boolean succ = readerCardService.updatePasswd(readerId, newPasswd);
				if (succ) {
					ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerId);
					request.getSession().setAttribute("readercard", readerCardNew);
					redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
					return "redirect:/reader_repasswd.html";
				} else {
					redirectAttributes.addFlashAttribute("succ", "密码修改失败！");
					return "redirect:/reader_repasswd.html";
				}

			} else {
				redirectAttributes.addFlashAttribute("error", "修改失败,原密码错误");
				return "redirect:/reader_repasswd.html";
			}
		} else {
			redirectAttributes.addFlashAttribute("error", "修改失败,两次输入的新密码不相同");
			return "redirect:/reader_repasswd.html";
		}

	}

	// 管理员功能--读者信息添加
	@RequestMapping("reader_add_do.html")
	public String readerInfoAddDo(String readerId,String name, String sex, String birth, String address, String telcode,
			String schoolName, String className, RedirectAttributes redirectAttributes) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date nbirth = new Date();
		try {
			java.util.Date date = sdf.parse(birth);
			nbirth = date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 生成一个UUID
		ReaderInfo readerInfo = new ReaderInfo();
		readerInfo.setAddress(address);
		readerInfo.setBirth(nbirth);
		readerInfo.setReaderId(readerId);
		readerInfo.setName(name);
		readerInfo.setTelcode(telcode);
		readerInfo.setSex(sex);
		readerInfo.setSchoolName(schoolName);
		readerInfo.setClassName(className);
		// 添加读者信息
		boolean succ = readerInfoService.addReaderInfo(readerInfo);
		ReaderInfo entity = readerInfoService.findByName(name);
		boolean succc = readerCardService.addReaderCard(entity);
		String text = entity.getReaderId();
		String destPath = "E:\\img\\" + readerInfo.getName() + text + ".jpg";
		// 取出id
		try {
			QRCodeUtil.encode(text + "", destPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<ReaderInfo> readers = readerInfoService.readerInfos();
		if (succ && succc) {
			redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
			return "redirect:/allreaders.html";
		} else {
			redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
			return "redirect:/allreaders.html";
		}
	}

	// 读者功能--读者信息修改
	@RequestMapping("reader_info_edit.html")
	public ModelAndView readerInfoEditReader(HttpServletRequest request) {
		ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
		ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
		ModelAndView modelAndView = new ModelAndView("reader_info_edit");
		modelAndView.addObject("readerinfo", readerInfo);
		return modelAndView;

	}

	@RequestMapping("reader_edit_do_r.html")
	public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth,
			String address, String telcode, RedirectAttributes redirectAttributes) {
		ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
		if (!readerCard.getName().equals(name)) {
			boolean succo = readerCardService.updateName(readerCard.getReaderId(), name);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nbirth = new Date();
			try {
				java.util.Date date = sdf.parse(birth);
				nbirth = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ReaderInfo readerInfo = new ReaderInfo();
			readerInfo.setAddress(address);
			readerInfo.setBirth(nbirth);
			readerInfo.setName(name);
			readerInfo.setReaderId(readerCard.getReaderId());
			readerInfo.setTelcode(telcode);
			readerInfo.setSex(sex);

			boolean succ = readerInfoService.editReaderInfo(readerInfo);
			if (succ && succo) {
				ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
				request.getSession().setAttribute("readercard", readerCardNew);
				redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
				return "redirect:/reader_info.html";
			} else {
				redirectAttributes.addFlashAttribute("error", "信息修改失败！");
				return "redirect:/reader_info.html";
			}

		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nbirth = new Date();
			try {
				java.util.Date date = sdf.parse(birth);
				nbirth = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ReaderInfo readerInfo = new ReaderInfo();
			readerInfo.setAddress(address);
			readerInfo.setBirth(nbirth);
			readerInfo.setName(name);
			readerInfo.setReaderId(readerCard.getReaderId());
			readerInfo.setTelcode(telcode);
			readerInfo.setSex(sex);

			boolean succ = readerInfoService.editReaderInfo(readerInfo);
			if (succ) {
				ReaderCard readerCardNew = loginService.findReaderCardByUserId(readerCard.getReaderId());
				request.getSession().setAttribute("readercard", readerCardNew);
				redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
				return "redirect:/reader_info.html";
			} else {
				redirectAttributes.addFlashAttribute("error", "信息修改失败！");
				return "redirect:/reader_info.html";
			}
		}
	}

	/**
	 * 读者搜索框查询
	 */
	@RequestMapping("queryReader")
	public ModelAndView queryReader(String searchWord) {
		boolean exist = readerInfoService.matchReader(searchWord);
		if (exist) {
			List<ReaderInfo> readerInfos = readerInfoService.queryReader(searchWord);
			ModelAndView modelAndView = new ModelAndView("admin_readers");
			modelAndView.addObject("readers", readerInfos);
			return modelAndView;
		} else {
			return new ModelAndView("admin_readers", "error", "没有匹配的读者");
		}
	}

	/**
	 * 查看读者借阅记录
	 */
	@RequestMapping("showReaderLend")
	public ModelAndView showReaderLend(String readerId) {
		List<Lend> lendList = lendService.myLendList(readerId);
		ModelAndView modelAndView = new ModelAndView("show_reader_lend_list");
		modelAndView.addObject("lendList", lendList);
		return modelAndView;
	}
	/**
	 * 查看读者借阅下钻搜索框(有点问题)
	 */
	@RequestMapping("queryreaderLend")
	public ModelAndView queryreaderLend(String searchWord,String readerId){
		boolean exist = lendService.matchReaderLend(searchWord,readerId);
		if (exist) {
			List <Lend> lendList = lendService.queryReaderLend(searchWord,readerId);
			ModelAndView modelAndView = new ModelAndView("show_reader_lend_list");
			modelAndView.addObject("lendList", lendList);
			return modelAndView;
		}else {
			return new ModelAndView("show_reader_lend_list", "error", "没有匹配的信息");
		}
	}
}
