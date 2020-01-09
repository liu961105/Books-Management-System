package com.book.web;

import com.book.domain.Book;
import com.book.domain.BookDictionaries;
import com.book.domain.BookImport;
import com.book.domain.ResultEntity;
import com.book.service.BookDictionariesService;
import com.book.service.impl.BookService;
import com.book.util.ExcelVerifyUserMsg;
import com.book.util.StringHelper;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图书管理
 *
 * @author LZN
 */
@Controller
public class BookController extends BaseController {
    @Autowired
    private BookDictionariesService bookDictionariesService;

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request, String searchWord, String searchISBN) {
        boolean exist = bookService.matchBook(searchWord, searchISBN);
        if (exist) {
            ArrayList<Book> books = bookService.queryBook(searchWord, searchISBN);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("admin_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook() {
        return new ModelAndView("reader_book_query");

    }

    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request, String searchWord, String searchISBN, String bookClass,
                                    RedirectAttributes redirectAttributes) {
        boolean exist = bookService.matchBook(searchWord, searchISBN);
        if (exist) {
            ArrayList<Book> books = bookService.queryBook(searchWord, searchISBN);
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }

    }

    /**
     * 全部图书
     *
     * @return
     */
    @RequestMapping("/allbooks.html")
    public ModelAndView allBook() {
        ArrayList<Book> books = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("admin_books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        int res = bookService.deleteBook(bookId);

        if (res == 1) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    /*
     * 图书新增跳转
     */
    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request) {

        return new ModelAndView("admin_book_add");

    }

    /**
     * 图书保存表单提交
     *
     * @param bookAddCommand
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/book_add_do.html")
    public String addBookDo(BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes) {
        /*
         * 新增时查询字典库是否有，没有则不能添加
         */
        BookDictionaries bookDictionaries = bookDictionariesService.checkISBN(bookAddCommand.getIsbn());
        if (bookDictionaries.getId() == null) {
            redirectAttributes.addFlashAttribute("succ", "该图书暂未收录！");
            return "redirect:/allbooks.html";
        }
        /*
         *新增查询是否已经新增过，如果有则只增加数量
         */
        Book byISBN = bookService.findByISBN(bookAddCommand.getIsbn());
        if (byISBN.getIsbn() != null) {
            bookService.addBookNumber(byISBN.getIsbn(), bookAddCommand.getNumber());
        } else {
            Book book = new Book();
            book.setBookId(0);
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
            book.setNumber(bookAddCommand.getNumber());
            book.setInNumber(bookAddCommand.getInNumber());
            book.setLendNumber(0);
            boolean succ = bookService.addBook(book);
            if (succ) {
                redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            } else {
                redirectAttributes.addFlashAttribute("succ", "图书添加失败！");

            }
        }
        return "redirect:/allbooks.html";
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request, BookAddCommand bookAddCommand,
                             RedirectAttributes redirectAttributes) {
        long bookId = Integer.parseInt(request.getParameter("id"));
        Book book = new Book();
        book.setBookId(bookId);
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
        book.setNumber(bookAddCommand.getNumber());
        boolean succ = bookService.editBook(book);
        if (succ) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("importExport")
    @ResponseBody
    public ResultEntity importExport(HttpServletRequest request, HttpServletResponse response) {
        ResultEntity res = new ResultEntity();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) (request);
            MultipartFile file = multipartRequest.getFile("file");
            File localfile = null;
            file.getInputStream();
            localfile = new File(file.getName());
            file.transferTo(localfile);
            response.setCharacterEncoding("utf-8");
            ImportParams params = new ImportParams();
            params.setVerifyHanlder(new ExcelVerifyUserMsg());
            ExcelImportResult<BookImport> result = ExcelImportUtil.importExcelVerify(localfile, BookImport.class,
                    params);
            String errorMsg = "";// 导入表格错误信息
            List<BookImport> userList = result.getList();
            for (int i = 0; i < userList.size(); i++) {
                if (!StringHelper.isNotBlack(userList.get(i).getErrorMsg())) {
                    BookImport book = userList.get(i);
                    System.out.println(book.getBookId());
                    if (bookService.getBook(book.getBookId()) != null) {
                        Book newOrder = new Book();
                        newOrder.setBookId(book.getBookId());
                        newOrder.setAuthor(book.getAuthor());
                        newOrder.setClassId(book.getClassId());
                        newOrder.setIntroduction(book.getIntroduction());
                        newOrder.setIsbn(book.getISBN());
                        newOrder.setLanguage(book.getLanguage());
                        newOrder.setName(book.getName());
                        newOrder.setNumber(book.getNumber());
                        newOrder.setPressmark(book.getPressmark());
                        newOrder.setPrice(book.getPrice());
                        newOrder.setPubdate(book.getPubdate());
                        newOrder.setState(book.getState());
                        newOrder.setPublish(book.getPublish());
                        bookService.addBook(newOrder);
                    }
                } else {
                    errorMsg += "第" + (i + 1) + "行错误:" + userList.get(i).getErrorMsg() + "</br>";
                }
            }

            if (StringHelper.isNotBlack(errorMsg)) {
                res.setSuccess(ERROR);
                res.setMessage("有部分信息未导入:</br>" + errorMsg);
            } else {
                res.setSuccess(SUCCESS);
                res.setMessage("导入成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(ERROR);
            res.setMessage(GET_ERROR + ":" + e.getMessage());
        }
        return res;
    }

}
