package ruslan.simakov.mailtrap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("mailtrap-client")
public record MailtrapClientProperties(
		@DefaultValue("https://send.api.mailtrap.io/api")
		String baseUrl,
		String apiToken) {
}
