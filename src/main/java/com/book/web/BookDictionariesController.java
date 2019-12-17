package com.book.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.domain.Book;
import com.book.domain.BookDictionaries;
import com.book.domain.ResultEntity;
import com.book.service.BookDictionariesService;

/**
 * 图书字典controller
 * 
 * @author LZN
 *
 */
@Controller
@RequestMapping("bookDictionaries")
public class BookDictionariesController extends BaseController {

	@Autowired
	private BookDictionariesService bookDictionariesService;

	@RequestMapping("getBookDictionaries")
	public ModelAndView getBookDictionaries() {
		List<BookDictionaries> books = bookDictionariesService.getDictionaries();
		ModelAndView modelAndView = new ModelAndView("book_dictionaries");
		modelAndView.addObject("books", books);
		return modelAndView;
	}

	/**
	 * 字典新增跳转
	 */
	@RequestMapping("addBookDictionaries")
	public ModelAndView addBookDictionaries() {
		return new ModelAndView("dictionaries_add");
	}

	/**
	 * 新增表单提交
	 */
	@RequestMapping("/saveBook")
	public String saveBookDictionaries(BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes) {
		BookDictionaries bookDictionaries = new BookDictionaries();
		bookDictionaries.setName(bookAddCommand.getName());
		bookDictionaries.setPrice(bookAddCommand.getPrice());
		bookDictionaries.setState(bookAddCommand.getState());
		bookDictionaries.setPublish(bookAddCommand.getPublish());
		bookDictionaries.setPubdate(bookAddCommand.getPubdate());
		bookDictionaries.setName(bookAddCommand.getName());
		bookDictionaries.setIsbn(bookAddCommand.getIsbn());
		bookDictionaries.setClassId(bookAddCommand.getClassId());
		bookDictionaries.setAuthor(bookAddCommand.getAuthor());
		bookDictionaries.setIntroduction(bookAddCommand.getIntroduction());
		bookDictionaries.setPressmark(bookAddCommand.getPressmark());
		bookDictionaries.setLanguage(bookAddCommand.getLanguage());
		int success = bookDictionariesService.save(bookDictionaries);
		if (success > 0) {
			redirectAttributes.addFlashAttribute("succ", "添加成功！");
			return "redirect:/bookDictionaries/getBookDictionaries";
		} else {
			redirectAttributes.addFlashAttribute("succ", "添加失败！");
			return "redirect:/bookDictionaries/getBookDictionaries";
		}
	}

	@RequestMapping("checkISBN")
	@ResponseBody
	public ResultEntity checkISBN(String isbn) {
		ResultEntity res = new ResultEntity();
		BookDictionaries bookDictionariesbook = bookDictionariesService.checkISBN(isbn);
		if (bookDictionariesbook != null) {
			res.setData(bookDictionariesbook);
			res.setSuccess(SUCCESS);
		} else {
			res.setSuccess(ERROR);
		}
		return res;
	}

	@RequestMapping("/updatebook.html")
	public ModelAndView bookEdit(HttpServletRequest request) {
		String id = request.getParameter("id");
		BookDictionaries bookDictionaries = bookDictionariesService.findById(id);
		ModelAndView modelAndView = new ModelAndView("book_dictional_edit");
		modelAndView.addObject("detail", bookDictionaries);
		return modelAndView;
	}
	@RequestMapping("/book_edit_do")
	public String bookEditDo(HttpServletRequest request, BookAddCommand bookAddCommand,
			RedirectAttributes redirectAttributes) {
		String  id = request.getParameter("id");
		BookDictionaries book = new BookDictionaries();
		book.setId(id);
		book.setPrice(bookAddCommand.getPrice());
		book.setState(bookAddCommand.getState());
		book.setPublish(bookAddCommand.getPublish());
		book.setPubdate(bookAddCommand.getPubdate());
		book.setName(bookAddCommand.getName());
		book.setIsbn(bookAddCommand.getIsbn());
		book.setClassId(bookAddCommand.getClassId());
		book.setAuthor(bookAddCommand.getAuthor());
		book.setIntroduction(bookAddCommand.getIntroduction());
		book.setPressmark(bookAddCommand.getPressmark());
		book.setLanguage(bookAddCommand.getLanguage());
		boolean succ = bookDictionariesService.editBook(book);
		if (succ) {
			redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
			return "redirect:/bookDictionaries/getBookDictionaries";
		} else {
			redirectAttributes.addFlashAttribute("error", "图书修改失败！");
			return "redirect:/bookDictionaries/getBookDictionaries";
		}
	}
	@RequestMapping("findByBookId")
	@ResponseBody
	public ResultEntity findById(String id){
		ResultEntity res = new ResultEntity();
		BookDictionaries bookDictionaries = bookDictionariesService.findById(id);
		if (bookDictionaries!=null) {
			res.setData(bookDictionaries);
			res.setSuccess(SUCCESS);
		}else{
			res.setSuccess(ERROR);
		}
		return res;
	}
	

}
