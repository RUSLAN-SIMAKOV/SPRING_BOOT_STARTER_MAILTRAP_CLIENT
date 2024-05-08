package ruslan.simakov.mailtrap.model;

import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;

@Builder
public record Sender(String name, @NonNull String email) implements Serializable {
}
