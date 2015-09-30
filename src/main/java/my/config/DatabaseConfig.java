package my.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by mohamed on 30/08/15.
 */
@Configuration
public class DatabaseConfig {

    @Value("${database.driver}")
    private String driver;

    @Value("${database.url}")
    private String url;

    @Value("${database.schema}")
    private String schema;

    @Value("${database.schema.params}")
    private String schemaParams;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.hibernate.show_sql}")
    private boolean showSql;

    @Value("${database.connection.pool.max.size}")
    private Integer maxConnectionPoolSize;

    @Value("${database.connection.pool.min.size}")
    private Integer minConnectionPoolSize;

    @Value("${database.connection.pool.increment.size}")
    private Integer connectionPoolIncrementSize;

    @Value("${database.connection.pool.idle.connection.test.period.seconds}")
    private Integer connectionPoolIdleConnectionTestPeriod;

    @Value("${database.connection.pool.max.idle.time.seconds}")
    private Integer connectionPoolMaxIdleTime;

    @Value("${database.connection.pool.test.connection.on.checkout}")
    private Boolean testConnectionOnCheckout;

    @Value("${database.connection.pool.unreturnedConnectionTimeout}")
    private Integer unreturnedConnectionTimeout;



    @Bean
    public DataSource dataSource() throws Exception {
        final ComboPooledDataSource ds = new ComboPooledDataSource();

        ds.setDriverClass(driver);
        ds.setJdbcUrl(url + schema + schemaParams);
        ds.setUser(username);
        ds.setPassword(password);

        ds.setMinPoolSize(minConnectionPoolSize);
        ds.setMaxPoolSize(maxConnectionPoolSize);
        ds.setAcquireIncrement(connectionPoolIncrementSize);
        ds.setIdleConnectionTestPeriod(connectionPoolIdleConnectionTestPeriod);
        ds.setMaxIdleTime(connectionPoolMaxIdleTime);
        ds.setTestConnectionOnCheckout(testConnectionOnCheckout);

        return ds;
    }
}
