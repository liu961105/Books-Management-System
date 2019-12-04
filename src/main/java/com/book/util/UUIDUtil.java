package com.book.util;

import java.util.UUID;

/**
 * uuid生成工具类
 * 
 * @author LZN
 *
 */
public  class UUIDUtil {

	public static String expordUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");

	}

}
