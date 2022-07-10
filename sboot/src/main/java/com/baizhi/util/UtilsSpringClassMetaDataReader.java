package com.baizhi.util;

import com.baizhi.entity.Student;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

public class UtilsSpringClassMetaDataReader {


    public static void main(String[] args) {
          Class stu=  Student.class;
        System.out.println(stu.getName());

        MetadataReader reader = getClassMetadata(stu.getName());
        AnnotationMetadata stuAnnotationMetadata = reader.getAnnotationMetadata();
        ClassMetadata stuClassMetaData = reader.getClassMetadata();
        System.out.println("判断student是否是抽象方法"+stuClassMetaData.isAbstract());
        boolean annotation = stuClassMetaData.isAnnotation();
        System.out.println("判断是否是注释类"+annotation);
        String className = stuClassMetaData.getClassName();
        System.out.println("包路径"+className);
        
        String enclosingClassName = stuClassMetaData.getEnclosingClassName();
        System.out.println("enclosingClassName"+enclosingClassName);
        
        
        
        String[] interfaceNames = stuClassMetaData.getInterfaceNames();

        System.out.println("实现的所有接口名字");
        for (String interfaceName : interfaceNames) {
            System.out.println(interfaceName);
        }
        
        
        String[] memberClassNames = stuClassMetaData.getMemberClassNames();
        System.out.println("memberClassNames=======");
        for (String memberClassName : memberClassNames) {
            System.out.println(memberClassName);
        }
        
        
        String superClassName = stuClassMetaData.getSuperClassName();
        System.out.println(superClassName);



        boolean concrete = stuClassMetaData.isConcrete();


        boolean independent = stuClassMetaData.isIndependent();


        boolean anInterface = stuClassMetaData.isInterface();
        System.out.println("判断本身是否是接口"+anInterface);
        
        
    }



    private static MetadataReader getClassMetadata(String classStr){
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = null;
        try {
            metadataReader = simpleMetadataReaderFactory.getMetadataReader(classStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  metadataReader;
    }
}
