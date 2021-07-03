import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.currency.common.codegenerator.CodeGeneratorBean;

import java.util.List;

public class MyCodeGenerator {

    private static final String MODEL_NAME ="sys";
    private static  final String PARENT = "com.currency";

    public static void generator(CodeGeneratorBean codeGeneratorBean){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        CodeGeneratorBean.MyGlobalConfig gc = codeGeneratorBean.getGlobalConfig();
        mpg.setGlobalConfig(gc);
        CodeGeneratorBean.MyPackageConfig packageConfig = codeGeneratorBean.getPackageConfig();
        packageConfig.setParent(PARENT);
        mpg.setPackageInfo(packageConfig);
        // 数据源配置
        DataSourceConfig dsc = codeGeneratorBean.getDataSourceConfig();
        mpg.setDataSource(dsc);
        final PackageConfig pc = codeGeneratorBean.getPackageConfig();
        pc.setModuleName(MODEL_NAME);

        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = codeGeneratorBean.getInjectionConfig();

        List<FileOutConfig> focList = cfg.getFileOutConfigList();
        focList.add(codeGeneratorBean.getFileOutConfig());
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        // 策略配置
        StrategyConfig strategy = codeGeneratorBean.getStrategyConfig();
        //设置表
        strategy.setInclude("sys_tenant","sys_user","sys_role","sys_menu","sys_user_role","sys_role_menu");
        //strategy.setSuperEntityColumns("id");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }



    public static void main(String[] args) {
        CodeGeneratorBean codeGeneratorBean = new CodeGeneratorBean();
        generator(codeGeneratorBean);
    }

}