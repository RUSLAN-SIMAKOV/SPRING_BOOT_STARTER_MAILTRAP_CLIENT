package ruslan.simakov.mailtrap.model;

import org.springframework.web.client.RestClient;

public class MailtrapClient {

    private final RestClient restClient;

    public MailtrapClient(RestClient restClient) {
        this.restClient = restClient;
    }
}
