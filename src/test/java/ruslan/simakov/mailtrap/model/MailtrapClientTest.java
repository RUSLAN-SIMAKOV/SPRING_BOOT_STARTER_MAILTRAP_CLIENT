package ruslan.simakov.mailtrap.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.http.ResponseEntity;
import ruslan.simakov.mailtrap.MailtrapClientConfiguration;
import ruslan.simakov.mailtrap.MailtrapClientProperties;
import ruslan.simakov.mailtrap.client.MailtrapClient;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailtrapClientTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(
					MailtrapClientConfiguration.class,
					RestClientAutoConfiguration.class));

	@Test
	void shouldContainsMailtrapRestClientTest() {
		contextRunner.run(context -> {
			assertTrue(context.containsBean("mailtrapRestClient"));
			assertTrue(context.containsBean("mailtrapClient"));
		});
	}

	@Test
	void shouldContainDefaultBaseUrl() {
		contextRunner
				.run((context) -> {
					assertThat(context).hasSingleBean(MailtrapClientProperties.class);
					assertThat(context.getBean(MailtrapClientProperties.class).baseUrl()).isEqualTo("https://send.api.mailtrap.io/api");
				});
	}

	@Test
	void shouldSetCustomBaseUrl() {
		contextRunner
				.withPropertyValues("mailtrap-client.base-url=override")
				.run((context) -> {
					assertThat(context).hasSingleBean(MailtrapClientProperties.class);
					assertThat(context.getBean(MailtrapClientProperties.class).baseUrl()).isEqualTo("override");
				});
	}

	@Disabled("use for smoke tests with valid credentials")
	@Test
	void shouldSendMail() {
		Mail mail = Mail.builder()
				.from(Sender.builder().email("sender@example.com").build())
				.to(List.of(Recipient.builder().email("recipient@example.com").build()))
				.subject("Subject of the email")
				.text("Plain text content of the email")
				.html("<html><body><h1>HTML content of the email</h1></body></html>")
				.attachments(List.of(Attachment.builder()
											 .content(Base64.getEncoder().encodeToString("CONTENT".getBytes()))
											 .type("application/pdf")
											 .filename("document.pdf")
											 .disposition("attachment")
											 .build()))
				.build();

		contextRunner
				.run((context) -> {
					MailtrapClient mailtrapClient = context.getBean(MailtrapClient.class);
					ResponseEntity<Void> response = mailtrapClient.send(mail);

					assertTrue(response.getStatusCode().is2xxSuccessful());
				});
	}

}