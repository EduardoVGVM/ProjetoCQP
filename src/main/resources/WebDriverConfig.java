import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver edgeWebDriver() {
        System.setProperty("webdriver.edge.driver", "src\\main\\resources");
        //System.setProperty("webdriver.edge.driver", "src/main/resources");
        return new EdgeDriver();
    }
}
