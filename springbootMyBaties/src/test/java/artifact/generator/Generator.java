package artifact.generator;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

//生成代码,generatorConfig.xml  可以不配置数据库驱动  <classPathEntry
//遇到问题   在eclipse安装mybatis-generator插件后   使用它生成没有反应   代码没有生成
//使用java程序或运行 mybatis-generator 命令  生成的代码没有注释  及实体的属性类型 没有和数据库类型对应上 如数据库的字符串类型    实体的属性却变成object
public class Generator {

	public static void main(String[] args) throws Exception {

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("src/main/resources/generator/generatorConfig.xml");//mbg.xml为上面创建的配置文件。
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);


	}

}