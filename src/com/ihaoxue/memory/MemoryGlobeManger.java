package com.ihaoxue.memory;

import java.util.ArrayList;
import java.util.List;

import com.ihaoxue.memory.bean.SelectCourseModel;

public class MemoryGlobeManger {

	/* 下载包安装路径 */ 
    public static final String savePath = "/sdcard/test/"; 
    /*安装包的下载完成之后保存的完整路径*/
    public static final String saveFileName = savePath + "test.apk";
    
    public static int serverVersion =1;
    
    public static int localVersion = 0 ;
    
    // 定义全局的试题
    public static List<SelectCourseModel> mQuestionModels = new ArrayList<SelectCourseModel>() ;
    
    // 全局网页访问路径
    public static String DEMO_URL_PATH = "file:///android_asset/views/jstest.html" ;
    
    
   //图片的异步缓存 路径
    public static String IMAGE_PATH = "/Android/data/images/"  ;
}
