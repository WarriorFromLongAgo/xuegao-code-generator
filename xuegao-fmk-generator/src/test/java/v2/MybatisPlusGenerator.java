package v2;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.xuegao.mapper.model.GenericModel;
import com.xuegao.xuegaofmgenerator.XuegaoFmkGeneratorApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = XuegaoFmkGeneratorApplication.class)
public class MybatisPlusGenerator {
    private String packageName = "com.xuegao.springmybatisplus";
    private String DEAL_TABLE_NAME = "";
    private String DEAL_MODEL_NAME = "";
    private String OUTPUT_FILE_PATH = "\\outputv2";

    @Before
    public void before() {
        DEAL_TABLE_NAME = "xue_gao_user";
        DEAL_MODEL_NAME = "SysUser";
    }

    @Test
    public void generator() {
        log.info("[xuegao-code-generator][v2.MybatisPlusGenerator][generator][由于文件路径的问题，此代码只可以处理win，在Linux需要处理一下斜杠]");

        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/xue_gao_im_chat_v2?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&allowMultiQueries=true", "root", "123456")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                // .typeConvertHandler(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        FastAutoGenerator
                .create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    String projectPath = System.getProperty("user.dir");
                    // E:\GitProject\TestProject\xuegao-code-generator\xuegao-fmk-generator
                    log.info("[xuegao-code-generator][v2.MybatisPlusGenerator][generator][projectPath={}]", projectPath);
                    String outputFilePath = projectPath + OUTPUT_FILE_PATH;
                    // .outputDir(projectPath + "/src/main/java")
                    builder
                            .outputDir(outputFilePath)
                            .author("xuegao")
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd");
                })
                .packageConfig(builder -> builder
                        .parent(packageName)
                        .moduleName(DEAL_MODEL_NAME.toLowerCase())
                        .entity("model.doo")
                        // .service("service")
                        .serviceImpl("service")
                        .mapper("mapper")
                        .xml("xml")
                        .controller("controller"))
                .templateConfig(builder -> builder
                        .disable(TemplateType.SERVICE)
                        .controller("/templates/controllerV2.java")
                        .entity("/templates/dooV2.java")
                        // .service("/templates/service.java")
                        .serviceImpl("/templates/manageV2.java")
                        .serviceImpl("/templates/serviceV2.java")
                        .mapper("/templates/mapperV2.java")
                        .xml("/templates/mapperV2.xml")

                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .superClass(GenericModel.class)
                        // .disableSerialVersionUID()
                        .enableChainModel()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .enableActiveRecord()

                        // .versionColumnName("version")
                        // .versionPropertyName("version")
                        // .logicDeleteColumnName("deleted")
                        // .logicDeletePropertyName("deleteFlag")
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        // .addIgnoreColumns("id", "del_flag", "created_by", "created_time", "updated_by", "updated_time", "trace_id")
                        // .addSuperEntityColumns("id", "del_flag", "create_by", "create_time", "update_by", "update_time", "trace_id")
                        // .formatFileName("%sEntity")
                        .idType(IdType.AUTO))
                .strategyConfig(builder -> builder
                        .controllerBuilder()
                        // .enableFileOverride()
                        .enableHyphenStyle()
                        .enableRestStyle())
                .strategyConfig(builder -> builder
                        .serviceBuilder()
                        // .enableFileOverride()
                        // .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sService"))
                .strategyConfig(builder -> builder
                        .mapperBuilder()
                        .superClass(BaseMapper.class)
                        // .enableMapperAnnotation()
                        .mapperAnnotation(Mapper.class)
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        // .formatXmlFileName("%s"))
                        .formatMapperFileName("%sMapper")
                )
                // .strategyConfig(builder -> builder
                                // .addInclude(DEAL_TABLE_NAME)
                        // .addTablePrefix("xue_gao_")
                // )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
