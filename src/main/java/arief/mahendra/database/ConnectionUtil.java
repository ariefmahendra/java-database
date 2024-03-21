package arief.mahendra.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {

    private static final HikariDataSource hikariDataSource;

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/belajar_java_db");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Makananku123");
        hikariConfig.addDataSourceProperty("ssl", "false");

        // membuat jumlah maksimum pool
        hikariConfig.setMaximumPoolSize(10);

        // membuat jumlah minimum yang idle atau menutup koneksi jika tidak diperlukan
        hikariConfig.setMinimumIdle(5);

        // mengatur waktu idle timeout
        hikariConfig.setIdleTimeout(60_000);

        // mengatur set max lifetime untuk di restart ulang
        hikariConfig.setMaxLifetime(10 * 60_000);

        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }
}
