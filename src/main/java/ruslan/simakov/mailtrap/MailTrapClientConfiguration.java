package ruslan.simakov.mailtrap;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import ruslan.simakov.mailtrap.model.MailtrapClient;

@AutoConfiguration
@EnableConfigurationProperties(MailTrapClientProperties.class)
public class MailTrapClientConfiguration {

    private final MailTrapClientProperties mailTrapClientProperties;

    MailTrapClientConfiguration(MailTrapClientProperties mailTrapClientProperties) {
        this.mailTrapClientProperties = mailTrapClientProperties;
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl(mailTrapClientProperties.baseUrl())
                .build();
    }

    @Bean
    MailtrapClient mailTrapClient(RestClient restClient) {
        return new MailtrapClient(restClient);
    }
}
