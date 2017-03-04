package com.tiger.core.util;

import org.junit.Test;

import java.util.HashMap;

/**
 * Package: com.tiger.core.util
 * ClassName: CglibBeanTest
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016/4/7
 * Version: 1.0
 */
public class CglibBeanTest {

    @Test
    public void testGetObject() throws Exception {
        // 设置类成员属性
        HashMap propertyMap = new HashMap();

        propertyMap.put("id", Class.forName("java.lang.Integer"));

        propertyMap.put("name", Class.forName("java.lang.String"));

        propertyMap.put("address", Class.forName("java.lang.String"));

        // 生成动态 Bean
        CglibBean bean = new CglibBean(propertyMap);

        System.out.println(bean.getClass().getName());
        // 给 Bean 设置值
//        bean.setValue("id", new Integer(123));
//
//        bean.setValue("name", "454");
//
//        bean.setValue("address", "789");
//
//        // 从 Bean 中获取值，当然了获得值的类型是 Object
//
//        System.out.println("  >> id      = " + bean.getValue("id"));
//
//        System.out.println("  >> name    = " + bean.getValue("name"));
//
//        System.out.println("  >> address = " + bean.getValue("address"));
//
//        // 获得bean的实体
//        Object object = bean.getObject();
//
//        // 通过反射查看所有方法名
//        Class clazz = object.getClass();
//        Method[] methods = clazz.getDeclaredMethods();
//        for (int i = 0; i < methods.length; i++) {
//            System.out.println(methods[i].getName());
//        }
    }
}