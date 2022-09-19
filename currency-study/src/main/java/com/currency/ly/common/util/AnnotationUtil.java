package com.currency.ly.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wuliangyong
 * @Date 2022/3/31 10:53
 */
@Slf4j
public final class AnnotationUtil {
    /**
     * 根据包路径、注解获取所有类
     * @param pack
     * @param annotation
     * @return
     */
    public static final List<Class<?>> getClassListByAnnotation(String pack, Annotation annotation) {
        List<Class<?>> list = new ArrayList<>();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        final String RESOURCE_PATTERN = "/**/*.class";
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(pack) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pack);
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                /* 用于读取类信息 */
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                try {
                    Class<?> clazz = Class.forName(classname);
                    Annotation annotationIns = clazz.getAnnotation(annotation.getClass());
                    if (annotationIns != null) {
                        list.add(clazz);

                    }
                } catch (Exception e) {
                    log.error("加载类：" + classname + "失败", e);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }
}
