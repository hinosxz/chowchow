import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

public class MigrationManager {

  @Bean(initMethod = "migrate")
  public Flyway flyway() {
    Flyway flyway = new Flyway();
    return flyway;
  }
}
