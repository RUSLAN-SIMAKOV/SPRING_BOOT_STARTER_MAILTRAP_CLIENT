package ruslan.simakov.mailtrap.model;

import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;

@Builder
public record Recipient(String name, @NonNull String email) implements Serializable {
}
