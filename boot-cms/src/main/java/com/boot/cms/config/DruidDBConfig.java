package com.boot.cms.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import java.util.Properties;

/**
 * description:
 *DruidDBConfig类被@Configuration标注，用作配置信息；
 * DataSource对象被@Bean声明，为Spring容器所管理，
 * @Primary表示这里定义的DataSource将覆盖其他来源的DataSource。
 * @author ZSX
 *jdbc.url=${jdbc.url}
 *最新的支持方式如下:
 *jdbc.url=@jdbc.url@
 * @auth guoshuai
 * @since 2018/8/9
 */
@Configuration
public class DruidDBConfig {

    private final String dataSourceConfigPrefix = "spring.datasource.";

    /** S数据库配置 */
    // 驱动类名称
    @Value("${"+ dataSourceConfigPrefix +"driver-class-name}")
    private String driverClassName;

    // 数据库连接url
    @Value("${"+ dataSourceConfigPrefix +"url}")
    private String url;

    // 数据库登录用户名
    @Value("${"+ dataSourceConfigPrefix +"username}")
    private String username;

    // 数据库登录密码
    @Value("${"+ dataSourceConfigPrefix +"password}")
    private String password;
    /** E数据库配置 */

    /** S数据连接池配置 */
    // 连接池种类
    @Value("${"+ dataSourceConfigPrefix +"type}")
    private String type;

    // 连接数 - 初始化大小
    @Value("${"+ dataSourceConfigPrefix +"initialSize}")
    private int initialSize;

    //  连接数 - 最小空闲
    @Value("${"+ dataSourceConfigPrefix +"minIdle}")
    private int minIdle;

    //  连接数 - 最大活跃
    @Value("${"+ dataSourceConfigPrefix +"maxActive}")
    private int maxActive;

    // 配置获取连接等待超时的时间(1分钟)
    @Value("${"+ dataSourceConfigPrefix +"maxWait}")
    private long maxWait;

    // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    @Value("${"+ dataSourceConfigPrefix +"timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    // 配置一个连接在池中最小生存的时间，单位是毫秒
    @Value("${"+ dataSourceConfigPrefix +"minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    // 验证数据库连接的有效性的sql语句
    @Value("${"+ dataSourceConfigPrefix +"validationQuery}")
    private String validationQuery;

    // 指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除
    @Value("${"+ dataSourceConfigPrefix +"testWhileIdle}")
    private boolean testWhileIdle;

    // 借出连接时不要测试，否则很影响性能
    @Value("${"+ dataSourceConfigPrefix +"testOnBorrow}")
    private boolean testOnBorrow;

    // 归还连接时不要测试，否则很影响性能
    @Value("${"+ dataSourceConfigPrefix +"testOnReturn}")
    private boolean testOnReturn;

    // 打开PSCache，并且指定每个连接上PSCache的大小
    @Value("${"+ dataSourceConfigPrefix +"poolPreparedStatements}")
    private boolean poolPreparedStatements;

    // 游标缓存最大数量
    @Value("${"+ dataSourceConfigPrefix +"maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    @Value("${"+ dataSourceConfigPrefix +"filters}")
    private String filters;

//    // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
//    @Value("${"+ dataSourceConfigPrefix +"connectionProperties}")
//    private Properties connectionProperties;

    // sql 相同单词合并
    @Value("${"+ dataSourceConfigPrefix +"connectionProperties.mergeSql}")
    private String mergeSql;

    // 认为执行效率低的sql的执行时间(毫秒)
    @Value("${"+ dataSourceConfigPrefix +"connectionProperties.slowSqlMillis}")
    private String slowSqlMillis;

    // 合并多个DruidDataSource的监控数据
    @Value("${"+ dataSourceConfigPrefix +"useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
    /** E数据连接池配置 */


    @Bean(destroyMethod = "close", initMethod = "init")   // 声明其为Bean实例
    @Primary    // 在同样的DataSource中，首先使用被标注的DataSource
    public DruidDataSource initDataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();

        /** S数据库配置 */
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        /** E数据库配置 */

        /** S数据库连接池配置 */
        druidDataSource.setDbType(type);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        druidDataSource.setFilters(filters);
        // sql慢查询
        Properties connectionProperties = new Properties();
        connectionProperties.put("mergeSql", mergeSql);
        connectionProperties.put("slowSqlMillis", slowSqlMillis);
        druidDataSource.setConnectProperties(connectionProperties);
        druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        /** E数据库连接池配置 */
        return druidDataSource;
    }
}
