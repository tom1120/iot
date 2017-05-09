package org.jeecgframework.core.util;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

/**
 * Created by zhaoyipc on 2017/4/25.
 */

public class GetClassDir {
    private static GetClassDir ourInstance = new GetClassDir();


    public static GetClassDir getInstance() {
        return ourInstance;
    }

    public static String classRootDir="";
/*    static {
        String absoluteFileName =GetClassDir.class.getClass().getResource("/").getPath();
        String classRootDir = absoluteFileName.replaceFirst("/", "");
        setClassRootDir(classRootDir);
    }*/

    private static void setClassRootDir(String classRootDir){
        GetClassDir.classRootDir=classRootDir;
    }

    /**
     * 获取当前类根目录下其他文件
     * @param path
     * @return
     */
    public String getResourcePath(String path) {
        String absoluteFileName = getClass().getResource(path).getPath();
        absoluteFileName = absoluteFileName.replaceFirst("/", "");
        return absoluteFileName;
    }

    /**
     * 获取当前类根目录
     * @return
     */
    public String getResourceRootPath() {
        String absoluteFileName = getClass().getResource("").getPath();
        String replaceString=getClass().getPackage().getName().replace(".","/");
        absoluteFileName = absoluteFileName.replace(replaceString+"/","").replaceFirst("/", "");
        return absoluteFileName;
    }

    /**
     * 获取项目根目录
     * @return
     */
    public String getProjectRootPath(){
        String projectRootPath= ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        return  projectRootPath;
    }

    /**
     * 获取相对于项目根目录的字符串路径
     * @param pathString
     * @return
     */
    public String getProjectRootPathByString(String pathString){
        String projectRootPath= ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(pathString);
        return  projectRootPath;
    }




}
