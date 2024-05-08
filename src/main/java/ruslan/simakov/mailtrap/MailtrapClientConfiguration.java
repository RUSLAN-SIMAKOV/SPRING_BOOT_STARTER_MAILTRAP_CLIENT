package ruslan.simakov.mailtrap;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import ruslan.simakov.mailtrap.model.MailtrapClient;

@AutoConfiguration
@EnableConfigurationProperties(MailtrapClientProperties.class)
public class MailtrapClientConfiguration {

    private final MailtrapClientProperties mailTrapClientProperties;

    MailtrapClientConfiguration(MailtrapClientProperties mailTrapClientProperties) {
        this.mailTrapClientProperties = mailTrapClientProperties;
    }

    @Bean
    RestClient mailtrapRestClient(RestClient.Builder builder) {
        return builder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Api-Token", mailTrapClientProperties.apiToken())
                .baseUrl(mailTrapClientProperties.baseUrl())
                .build();
    }

    @Bean
    MailtrapClient mailtrapClient(RestClient restClient) {
        return new MailtrapClient(restClient);
    }
}
