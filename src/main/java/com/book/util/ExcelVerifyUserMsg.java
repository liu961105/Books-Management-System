package com.book.util;

import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;

import com.book.domain.BookImport;


public class ExcelVerifyUserMsg implements IExcelVerifyHandler<BookImport>{

	@Override
	public ExcelVerifyHanlderResult verifyHandler(BookImport bookImport) {
		StringBuilder builder = new StringBuilder();
		if(!StringHelper.isNotBlack(bookImport.getISBN())){
			builder.append("ISBN不能为空！");
		}
		if(!StringHelper.isNotBlack(bookImport.getName())){
			builder.append("图书名称不能为空");
		}
		return new ExcelVerifyHanlderResult(false, builder.toString());
	}

}
