package com.tiger.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Package: com.tiger.core.util
 * ClassName: a
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016/4/7
 * Version: 1.0
 */
public class FindPackageClass {

    private static Logger log = LogManager.getLogger(FindPackageClass.class);

    public static List<String> getClassInPackage(String pkgName, String path) {

        List<String> ret = new ArrayList<>();
        String rPath = pkgName.replace('.', '/') + "/";
        try {
            File classPath = new File(path);
            if (classPath.isDirectory()) {
                File dir = new File(classPath, rPath);
                if(!dir.exists()) {
                    log.error(
                            "the dir : " + dir + " is not exit!"
                    );
                    throw new RuntimeException();
                }
                for (File file : dir.listFiles()) {
                    if (file.isFile()) {
                        String clsName = file.getName().substring(0, file.getName().length() - 6);
//                        clsName = pkgName + "." + clsName.substring(0, clsName.length() - 6);
                        ret.add(clsName);
                    }
                }
            } else {
                FileInputStream fis = new FileInputStream(classPath);
                JarInputStream jis = new JarInputStream(fis, false);
                JarEntry e = null;
                while ((e = jis.getNextJarEntry()) != null) {
                    String eName = e.getName();
                    if (eName.startsWith(rPath) && !eName.endsWith("/")) {
                        ret.add(eName.replace('/', '.').substring(0,
                                eName.length() - 6));
                    }
                    jis.closeEntry();
                }
                jis.close();
            }
        } catch (Exception e) {
            log.error(
                    "\n methed : public static List<String> getClassInPackage(String pkgName, String path)" +
                    "\n pkgName = " + pkgName +
                    "\n path = " + path
            );
            throw new RuntimeException(e);
        }

        return ret;
    }
}
