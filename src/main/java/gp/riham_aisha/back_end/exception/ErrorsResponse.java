package gp.riham_aisha.back_end.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ErrorsResponse {
    private List<String> errors;

    public ErrorsResponse(String... error) {
        errors = new ArrayList<>();
        Collections.addAll(errors, Arrays.copyOf(error, error.length));
    }
}