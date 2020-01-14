package com.book.web;

import com.book.domain.BookPressmark;
import com.book.domain.Language;
import com.book.domain.ResultEntity;
import com.book.service.BookPressmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookPressmarkController extends BaseController {
    @Autowired
    private BookPressmarkService bookPressmarkService;

    @RequestMapping("getBookPressmark")
    public ModelAndView getBooKLanguage() {
        List<BookPressmark> bookPressmarks = bookPressmarkService.getAllPressmark();
        ModelAndView modelAndView = new ModelAndView("book_pressmarker_list");
        modelAndView.addObject("bookPressmarks", bookPressmarks);
        return modelAndView;
    }

    /**
     * 跳转新增
     *
     * @return
     */
    @RequestMapping("toAddBookPressmark")
    public ModelAndView toAddLanguage() {
        return new ModelAndView("book_pressmarker_add");
    }

    /**
     * 保存
     *
     * @param
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("bookPressmarkSave")
    public String languageSave(BookPressmark bookPressmark, RedirectAttributes redirectAttributes) {
        int success = bookPressmarkService.saveBookPressmark(bookPressmark);
        if (success > 0) {
            redirectAttributes.addFlashAttribute("succ", "添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "添加失败！");
        }
        return "redirect:/getBookPressmark";
    }

    /**
     * 编辑
     */
    @RequestMapping("editBookPressmark")
    public ModelAndView editLanguage(String id) {
        BookPressmark bookPressmark = bookPressmarkService.findById(id);
        ModelAndView modelAndView = new ModelAndView("book_pressmark__edit");
        modelAndView.addObject("bookPressmark", bookPressmark);
        return modelAndView;
    }

    @RequestMapping("editBookPressmark_do")
    public String editLanguage_do(BookPressmark bookPressmark, RedirectAttributes redirectAttributes) {
        int success = bookPressmarkService.editBookPressmark(bookPressmark);
        if (success > 0) {
            redirectAttributes.addFlashAttribute("succ", "修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "修改失败！");
        }
        return "redirect:/getBookPressmark";
    }

    @RequestMapping("deleteBookPressmark")
    @ResponseBody
    public ResultEntity deleteLanguage(String id) {
        ResultEntity res = new ResultEntity();
        try {
            int success = bookPressmarkService.deleteBookPressmark(id);
            if (success > 0) {
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

    @RequestMapping("findBookPressmark")
    @ResponseBody
    public ResultEntity findLanguage() {
        ResultEntity res = new ResultEntity();
        List<BookPressmark> bookPressmark = bookPressmarkService.getAllPressmark();
        if (bookPressmark.size() > 0) {
            res.setData(bookPressmark);
            res.setSuccess(SUCCESS);
            res.setMessage(OPERATE_SUCCESS);
        } else {
            res.setSuccess(ERROR);
        }
        return res;
    }
}
