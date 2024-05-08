package ruslan.simakov.mailtrap.model;

import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

@Builder
public record Mail(
		@NonNull Sender from,
		@NonNull List<Recipient> to,
		String subject,
		String text,
		String html,
		List<Attachment> attachments) implements Serializable {
}

