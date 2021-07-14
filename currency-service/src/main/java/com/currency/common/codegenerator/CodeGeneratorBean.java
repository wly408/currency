package com.currency.common.codegenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 吴良勇
 * @date 2019/11/19 14:15
 */
@Data
public class CodeGeneratorBean {

    private MyGlobalConfig globalConfig = new MyGlobalConfig();

    private MyDataSourceConfig dataSourceConfig = new MyDataSourceConfig();

    private MyPackageConfig packageConfig = new MyPackageConfig();

    private MyFileOutConfig fileOutConfig = new MyFileOutConfig();

    private MyStrategyConfig strategyConfig = new MyStrategyConfig();

    private MyInjectionConfig injectionConfig = new MyInjectionConfig();

    private static final String dir = System.getProperty("user.dir") + "/doc/autocode/java";

    @Data
    public static class MyGlobalConfig extends GlobalConfig {
        public MyGlobalConfig() {
            this.setAuthor("admin");
            this.setOutputDir(dir);
            this.setEnableCache(false);
            this.setOpen(false);
            this.setBaseResultMap(true);
            this.setBaseColumnList(true);
            this.setFileOverride(true);
            //主键策略
            this.setIdType(IdType.INPUT);
            //swagger
            this.setSwagger2(true);



        }

    }

    @Data
    public class MyDataSourceConfig extends DataSourceConfig {
        public MyDataSourceConfig() {
            this.setUrl("jdbc:mysql://127.0.0.1:3306/currency?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8");
            this.setDriverName("com.mysql.jdbc.Driver");
            this.setUsername("root");
            this.setPassword("123456");
        }
    }

    @Data
    public class MyPackageConfig extends PackageConfig {

        public MyPackageConfig() {
            this.setParent("com.currency");


        }
    }

    @Data
    public class MyFileOutConfig extends FileOutConfig {

        public MyFileOutConfig(String templatePath) {
            this.setTemplatePath(templatePath);
        }

        public MyFileOutConfig() {
            this.setTemplatePath("/templates/mapper.xml.ftl");
        }

        @Override
        public String outputFile(TableInfo tableInfo) {
            final String projectPath = System.getProperty("user.dir");
            final String path = CodeGeneratorBean.this.packageConfig.getParent().replace(".", "/");
            // 自定义输入文件名称

            return projectPath + "/doc/autocode/config/" + path
                    + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;

        }
    }

    @Data
    public class MyStrategyConfig extends StrategyConfig {
        public MyStrategyConfig() {
            // 策略配置
            this.setNaming(NamingStrategy.underline_to_camel);
            this.setColumnNaming(NamingStrategy.underline_to_camel);
        this.setSuperEntityClass("com.currency.common.entity.BaseEntity");
//        strategy.setSuperEntityClass("Serializable");
            this.setSuperServiceClass("com.currency.common.mybatis.BaseServiceImpl");


            this.setEntityLombokModel(true);
            this.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
            this.setSuperControllerClass(null);

            //this.setInclude("ly_test");
            //this.setSuperEntityColumns("id");
            this.setControllerMappingHyphenStyle(true);
            this.setTablePrefix(CodeGeneratorBean.this.packageConfig.getModuleName() + "_");
            //【实体】是否生成字段常量
            this.setEntityColumnConstant(true);

//            this.setSuperEntityClass("com.ly.myspringboot.common.entity.BaseEntity");
            //表字段大写
            this.setCapitalMode(true);
            this.setSuperServiceClass(null);
            this.setSuperEntityColumns("create_date","create_user","tenant_id","update_date","update_user","remark","status_cd");


        }

    }

    public class MyInjectionConfig extends InjectionConfig {
        public MyInjectionConfig() {
            //添加add 请求对象
            List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();

            final String path = CodeGeneratorBean.this.packageConfig.getParent().replace(".", "/");
            fileOutConfigList.add(new FileOutConfig("/template/dto.vm") {

                @Override
                public String outputFile(TableInfo tableInfo) {
                    String rs = dir + "/" + path+"/" +CodeGeneratorBean.this.getPackageConfig().getModuleName()+ "/dto/" + tableInfo.getEntityName() + "DTO"
                            + StringPool.DOT_JAVA;
                    System.out.println("-------------------rs:"+rs);
                    return rs;
                }
            });
            this.setFileOutConfigList(fileOutConfigList);
        }

        @Override
        public void initMap() {
            Map<String, Object> map = getMap();
            if(map==null){
                map = new HashMap<String,Object>();
                final String path = CodeGeneratorBean.this.packageConfig.getParent().replace(".", "/");
                map.put("Dto",CodeGeneratorBean.this.packageConfig.getParent()+".dto");
            }
            this.setMap(map);
        }


    }

}
