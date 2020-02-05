package xyz.wanghehe.community.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frog
 */
@Configuration
public class MybatisConfig {


    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //设置下划线到驼峰命名法
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
