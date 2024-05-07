package ruslan.simakov.mailtrap.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ruslan.simakov.mailtrap.MailTrapClientConfiguration;
import ruslan.simakov.mailtrap.MailTrapClientProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailtrapClientTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(
                    MailTrapClientConfiguration.class,
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
                    assertThat(context).hasSingleBean(MailTrapClientProperties.class);
                    assertThat(context.getBean(MailTrapClientProperties.class).baseUrl()).isEqualTo("https://send.api.mailtrap.io/api/send");
                });
    }

    @Test
    void shouldSetCustomBaseUrl() {
        contextRunner
                .withPropertyValues("mailtrap-client.base-url=override")
                .run((context) -> {
                    assertThat(context).hasSingleBean(MailTrapClientProperties.class);
                    assertThat(context.getBean(MailTrapClientProperties.class).baseUrl()).isEqualTo("override");
                });
    }

}