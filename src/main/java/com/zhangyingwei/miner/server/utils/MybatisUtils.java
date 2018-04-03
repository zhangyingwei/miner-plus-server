package com.zhangyingwei.miner.server.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * Created by zhangyw on 2017/8/16.
 */
public class MybatisUtils {
    private static Logger logger = Logger.getLogger(MybatisUtils.class);
    private static SqlSessionFactory getSessionFactory(){
        String resource = "mybatis-conf.xml";
        InputStream input = MybatisUtils.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);
        return factory;
    }

    public static <T> T getMapper(Class clazz){
        SqlSessionFactory factory = getSessionFactory();
        SqlSession session = factory.openSession(true);
        return (T) session.getMapper(clazz);
    }

    public static void main(String[] args) {
        System.out.println(MybatisUtils.getSessionFactory());
    }
}
