package ruslan.simakov.mailtrap.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import ruslan.simakov.mailtrap.model.Mail;

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
