package com.book.web;

import com.book.domain.Book;
import com.book.domain.Lend;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.domain.ResultEntity;
import com.book.service.impl.BookService;
import com.book.service.impl.LendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 借还管理
 * 
 * @author LZN
 *
 */
@Controller
public class LendController extends BaseController {

	private LendService lendService;

	@Autowired
	public void setLendService(LendService lendService) {
		this.lendService = lendService;
	}

	private BookService bookService;

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	/*
	 * 图书借阅
	 */
	@RequestMapping("/lendbook.html")
	public ModelAndView bookLend(HttpServletRequest request) {
		long bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.getBook(bookId);
		ModelAndView modelAndView = new ModelAndView("admin_book_lend");
		modelAndView.addObject("book", book);
		return modelAndView;
	}

	/*
	 * 图书借阅表单
	 */
	@RequestMapping("/lendbookdo.html")
	public String bookLendDo(HttpServletRequest request, RedirectAttributes redirectAttributes, int readerId,
			String borrowingDay, String bookName, String readerName) {
		long bookId = Integer.parseInt(request.getParameter("id"));
		boolean lendsucc = lendService.bookLend(bookId, readerId, borrowingDay, bookName, readerName);
		if (lendsucc) {
			redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
			return "redirect:/allbooks.html";
		} else {
			redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
			return "redirect:/allbooks.html";
		}

	}

	/*
	 * 
	 */
	@RequestMapping("/returnbook")
	public ModelAndView returnbook(HttpServletRequest request) {
		long bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.getBook(bookId);
		Lend lend = lendService.getReadId(bookId);
		ModelAndView modelAndView = new ModelAndView("admin_book_return");
		modelAndView.addObject("book", book);
		modelAndView.addObject("lend", lend);
		return modelAndView;
	}

	// 归还图书
	@RequestMapping("/returnbook.html")
	public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		long bookId = Integer.parseInt(request.getParameter("id"));
		boolean retSucc = lendService.bookReturn(bookId);
		if (retSucc) {
			redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
			return "redirect:/allbooks.html";
		} else {
			redirectAttributes.addFlashAttribute("error", "图书归还失败！");
			return "redirect:/allbooks.html";
		}
	}

	/**
	 * 借还日志跳转
	 * 
	 * @return
	 */
	@RequestMapping("/lendlist.html")
	public ModelAndView lendList() {

		ModelAndView modelAndView = new ModelAndView("admin_lend_list");
		modelAndView.addObject("list", lendService.lendList());
		return modelAndView;
	}

	@RequestMapping("/mylend.html")
	public ModelAndView myLend(HttpServletRequest request) {
		ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
		ModelAndView modelAndView = new ModelAndView("reader_lend_list");
		modelAndView.addObject("list", lendService.myLendList(readerCard.getReaderId()));
		return modelAndView;
	}

	/*
	 * 根据读者证号查询姓名
	 */
	@RequestMapping("getReadName")
	@ResponseBody
	public ResultEntity getReadName(int readerId) {
		ResultEntity res = new ResultEntity();
		ReaderInfo entity = lendService.getReadName(readerId);
		if (entity != null) {
			res.setData(entity);
			res.setMessage(GET_SUCCESS);
			res.setSuccess(SUCCESS);
		} else {
			res.setMessage(IS_NULL);
			res.setSuccess(NULL);
		}
		return res;
	}

	/**
	 * 搜索框查询
	 * 
	 * @param searchWord
	 * @return
	 */
	@RequestMapping("queryLog")
	public ModelAndView queryLog(String searchWord) {
		boolean exist = lendService.matchLog(searchWord);
		if (exist) {
			List<Lend> lends = lendService.queryLog(searchWord);
			ModelAndView modelAndView = new ModelAndView("admin_lend_list");
			modelAndView.addObject("list", lends);
			return modelAndView;
		} else {
			return new ModelAndView("admin_lend_list", "error", "没有匹配的借还日志");
		}
	}

}
