package gp.riham_aisha.back_end.repository.product_and_configuration;

import gp.riham_aisha.back_end.model.product_and_configuration.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}