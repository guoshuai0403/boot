package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * description:
 *
 * @auth guoshuai
 * @since 2018/8/14
 */
public class Test {

    public static void main(String[] args) {

//        String filename = "C:/Users/40805/AppData/Local/Temp/fz3temp-2/asdf.java";
//
//        Test.readFileByLines(filename);

        System.out.println("valuethe new value".length());
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            HashSet<String> strs = new HashSet<>();
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line === " + line + ":" + tempString);
                String substring = StringUtils.substring(tempString, tempString.indexOf("@"));
                strs.add(substring);
//                3d55e819
                line++;
            }
            System.out.println("strs.size() === "+strs.size());
            for(String str : strs){
                System.out.println(str);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
