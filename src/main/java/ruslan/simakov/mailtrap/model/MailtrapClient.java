package ruslan.simakov.mailtrap.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class MailtrapClient {

    private final RestClient restClient;

    public MailtrapClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<Void> send(Mail mail){
        return restClient.post()
                .uri("/send")
                .body(mail)
                .retrieve()
                .toBodilessEntity();
    }
}
