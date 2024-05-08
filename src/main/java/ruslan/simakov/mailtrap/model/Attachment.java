package ruslan.simakov.mailtrap.model;

import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;

@Builder
public record Attachment(@NonNull String content,
				  String type,
				  @NonNull String filename,
				  String disposition) implements Serializable {
}
