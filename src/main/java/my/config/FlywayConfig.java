package my.config;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

/**
 * Created by mohamed on 30/08/15.
 */
@Configuration
public class FlywayConfig {

    private static final String MIGRATION_SCRIPTS_SCHEMA_TABLE = "migration_scripts_flyway_meta_schema";
    private static final String SAMPLE_DATA_SCHEMA_TABLE = "sample_data_flyway_meta_schema";
    public static final String CLEAN_MIGRATE = "cleanMigrate";
    public static final String MIGRATE = "migrate";

    @Value("${database.db.migration.strategy}")
    private String databaseMigrationStrategy;

    @Value("${flyway.migration.sample.data.enabled}")
    private Boolean migrationSampleDataEnabled;

    @Value("${flyway.migration.sample.data.folder}")
    private String migrationSampleDataFolder;

    @Bean(name = "flywayMigrationBean")
    public Flyway flywayMigrationBean(DataSource dataSource) {
        Flyway flywayMigrationBean = new Flyway();
        //  flywayMigrationBean.setLocations("db/migration");
        flywayMigrationBean.setDataSource(dataSource);
        flywayMigrationBean.setTable(MIGRATION_SCRIPTS_SCHEMA_TABLE);
        switch (databaseMigrationStrategy) {
            case CLEAN_MIGRATE:
                flywayMigrationBean.setInitVersion("0");
                flywayMigrationBean.clean();
                flywayMigrationBean.init();
                flywayMigrationBean.migrate();
                break;
            case MIGRATE:
                flywayMigrationBean.migrate();
                break;
            default:
                throw new RuntimeException("Invalid database db.migration strategy value " + databaseMigrationStrategy);
        }
        return flywayMigrationBean;
    }

    @Bean(name = "flywaySampleDataBean")
    @DependsOn("flywayMigrationBean")
    public Flyway flywaySampleDataBean(DataSource dataSource) {
        Flyway flywayMigrationBean = new Flyway();
        flywayMigrationBean.setLocations(migrationSampleDataFolder);
        flywayMigrationBean.setDataSource(dataSource);
        flywayMigrationBean.setTable(SAMPLE_DATA_SCHEMA_TABLE);
        if (migrationSampleDataEnabled) {
            switch (databaseMigrationStrategy) {
                case CLEAN_MIGRATE:
                    flywayMigrationBean.setInitVersion("0");
                    flywayMigrationBean.init();
                    flywayMigrationBean.migrate();
                    break;
                case MIGRATE:
                    flywayMigrationBean.migrate();
                    break;
                default:
                    throw new RuntimeException("Invalid database migration strategy value " + databaseMigrationStrategy);
            }
        }
        return flywayMigrationBean;
    }
}
