import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {

  public void generator(String path) throws Exception {

    List<String> warnings = new ArrayList<String>();
    // 允许覆盖
    boolean overwrite = true;
    // 配置文件
    File configFile = new File(path);
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);

    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }

  public static void main(String[] args) throws Exception {
    try {
      Generator generator = new Generator();
      String path = generator.getClass().getResource("generatorConfig.xml").getPath();
      System.out.println(path);
      generator.generator(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
