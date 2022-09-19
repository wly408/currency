package com.currency.ly.j2se.base.stream;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author wuliangyong
 * @Date 2022/9/19 14:26
 */
public class StreamTest {


    public static void main(String[] args) {
        List<StreamBean> beanList = new ArrayList<>();
        StreamBean one = new StreamBean(1L, "1", "name1");
        StreamBean two = new StreamBean(2L, "2", "name2");
        beanList.add(one);
        beanList.add(one);

        //1.list<对象>转list<String>
        List<String> strList = beanList.stream().map(StreamBean::getCode).collect(Collectors.toList());
        //2.list<对象的long>转list<String>
        List<String> longList = beanList.stream().map(s -> String.valueOf(s.getId())).collect(Collectors.toList());

        //3.list转map：重复时用后面的value 覆盖前面的value
        Map<Long, String> elMap = beanList.stream().collect(Collectors.toMap(StreamBean::getId, StreamBean::getCode, (key1, key2) -> key2));


    }


    @Data
    @NoArgsConstructor
    public static class StreamBean {
        private Long id;

        private String code;

        private String name;

        public StreamBean(Long id, String code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }
    }

}
