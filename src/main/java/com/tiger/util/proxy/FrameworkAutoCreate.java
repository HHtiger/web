package com.tiger.util.proxy;

import com.tiger.util.ClassPathUtil;
import com.tiger.util.FindPackageClass;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Package: com.tiger.core.util.proxy
 * ClassName: FrameworkAutoCreate
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016/4/7
 * Version: 1.0
 */
public class FrameworkAutoCreate {

    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static void init(Class clazz){
        String classPath = ClassPathUtil.getClassesPath(clazz);
        String RootPath = ClassPathUtil.getRootPath(clazz);
        //当前编译器
        JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();
        //Java标准文件管理器
        StandardJavaFileManager fm = cmp.getStandardFileManager(null, null, null);
        //编译参数，类似于javac <options>中的options
        List<String> optionsList = new ArrayList<String>();

        String lib = classPath + ";" +
                RootPath + "/lib/log4j-api-2.5.jar;" +
                RootPath + "/lib/spring-context-4.2.5.RELEASE.jar;" +
                RootPath + "/lib/spring-web-4.2.5.RELEASE.jar;" +
                RootPath + "/lib/javaee-api-7.0.jar"
        ;
        System.out.println(lib);
        //编译文件的存放地方
        optionsList.addAll(
                Arrays.asList(
                        "-d", classPath
                        ,"-classpath", lib
                )
        );
        //要编译的单元
        List<JavaFileObject> jfos = new ArrayList<>();

        String packageName = "com.tiger.model";

        List<String> beanNames = FindPackageClass.getClassInPackage(packageName, classPath);

        for (String beanName : beanNames) {

            //类名及文件名
            String clsName = beanName +"Controller";
            //Java源代码
            String sourceStr =
                    "package com.tiger.controller; " + "\n"+
                    "import com.tiger.model."+beanName + ";" +"\n"+
                    "import com.tiger.service."+beanName +"Service;"+ "\n"+
                    "import org.apache.logging.log4j.LogManager;" + "\n"+
                    "import org.apache.logging.log4j.Logger;" + "\n"+
                    "import org.springframework.stereotype.Controller;" + "\n"+
                    "import org.springframework.ui.Model;" + "\n"+
                    "import org.springframework.web.bind.annotation.PathVariable;" + "\n"+
                    "import org.springframework.web.bind.annotation.RequestMapping;" + "\n"+
                    "import javax.annotation.Resource;" + "\n"+
                    "import javax.servlet.http.HttpServletRequest;" + "\n"+
                    "@Controller" + "\n"+
                    "@RequestMapping(\"/"+beanName.toLowerCase()+"\")" + "\n"+
                    "public class "+beanName+"Controller {" + "\n"+
                    "    private static Logger log = LogManager.getLogger("+clsName+".class);" + "\n"+
                    "    @Resource" + "\n"+
                    "    private "+beanName+"Service "+toLowerCaseFirstOne(beanName)+"Service;" + "\n"+
                    "    @RequestMapping(\"/show"+beanName.toLowerCase()+"/{id}\")" + "\n"+
                    "    public String toIndex( @PathVariable(\"id\") int id, HttpServletRequest request, Model model ){" + "\n"+
                    "        "+beanName+" obj = "+toLowerCaseFirstOne(beanName)+"Service.selectByPrimaryKey(id);" + "\n"+
                    "        model.addAttribute(\"obj\", obj);" + "\n"+
                    "        log.debug(obj.toString());" + "\n"+
                    "        return \"show"+beanName+"\";" + "\n"+
                    "    }" + "\n"+
                    "}" ;



            //方法名
            //String methodName = "sayHello";

            //Java文件对象
            JavaFileObject jfo = new JavaStringObject(clsName, sourceStr);
            jfos.add(jfo);

        }

        //设置编译环境
        try {
            JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null, optionsList, null, jfos);
            //编译成功
            if (task.call()) {
                System.out.println("ok");
//            //生成对象
//            Object obj = Class.forName(clsName).newInstance();
//            Class<? extends Object> cls = obj.getClass();
//            //调用sayHello方法
//            Method m = cls.getMethod(methodName, String.class);
//            String str = (String) m.invoke(obj, "Dynamic Compilation");
//            System.out.println(str);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
