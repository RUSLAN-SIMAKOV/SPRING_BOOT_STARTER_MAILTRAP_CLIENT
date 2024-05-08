package ruslan.simakov.mailtrap.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

@Builder
record Sender(String name, @NonNull String email) implements Serializable {
}

@Builder
record Recipient(String name, @NonNull String email) implements Serializable {
}

@Builder
record Attachment(@NonNull String content,
				  String type,
				  @NonNull String filename,
				  String disposition) implements Serializable {
}
