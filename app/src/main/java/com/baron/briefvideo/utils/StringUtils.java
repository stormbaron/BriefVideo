package com.baron.briefvideo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * java String��������
 * @author baron
 *
 */
public class StringUtils {
	
	/**
	 * ���������ַ�
	 * @author baron
	 */
	//int mMaxLenth = 200;//��������������ַ�����
	public static String remove_Special_character(String str)throws PatternSyntaxException{ 
	 String regEx = "[/\\:*?<>|\"\n\t]";
	 Pattern p = Pattern.compile(regEx);
	 Matcher m = p.matcher(str);
	 return m.replaceAll("");
	 }
	
}
