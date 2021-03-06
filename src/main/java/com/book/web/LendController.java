package com.book.web;

import com.book.domain.*;
import com.book.service.impl.BookService;
import com.book.service.impl.LendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 借还管理
 *
 * @author LZN
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
                             String borrowingDay, String bookName, String readerName, String returnDate) {
        long bookId = Integer.parseInt(request.getParameter("id"));
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Book book = bookService.getBook(bookId);
        boolean lendsucc = lendService.bookLend(bookId, readerId, borrowingDay, bookName, readerName, returnDate, book.getPublish(), admin.getAdminId());
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
        ModelAndView modelAndView = new ModelAndView("admin_book_return");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    // 归还图书
    @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Integer.parseInt(request.getParameter("id"));
        String readerId = request.getParameter("readerId");
        Lend sernum = lendService.getSernum(bookId, readerId);
        boolean retSucc = lendService.bookReturn(sernum.getSernum(), bookId);
        if (retSucc) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "该类图书暂未有借阅记录！");
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

    /**
     * 借阅者系统 我的借还
     *
     * @param request
     * @return
     */
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
    public ResultEntity getReadName(String readerId) {
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

    @RequestMapping("checkReaderLog")
    @ResponseBody
    public ResultEntity checkReaderLog(long bookId, int readerId) {
        ResultEntity res = new ResultEntity();
        boolean exist = lendService.checkReaderLog(bookId, readerId);
        if (exist) {
            res.setMessage(OPERATE_SUCCESS);
            res.setSuccess(SUCCESS);
        } else {
            res.setSuccess(ERROR);
        }
        return res;
    }

}
