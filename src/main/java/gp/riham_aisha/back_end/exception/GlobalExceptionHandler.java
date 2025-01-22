package gp.riham_aisha.back_end.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.springframework.util.StringUtils.capitalize;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getErrors());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorsResponse(ex.getMessage()));
    }

    @ExceptionHandler({AccessDeniedException.class, SecurityException.class})
    public ResponseEntity<Object> handleAccessDeniedException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorsResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException ex, WebRequest request) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException constraintViolationException) {
            // Access the set of constraint violations
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            String[] violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .toArray(String[]::new);
            return ResponseEntity.badRequest().body(new ErrorsResponse(violationMessages));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorsResponse("An unexpected error occurred."));
        }
    }

    @ExceptionHandler({jakarta.validation.ValidationException.class})
    public ResponseEntity<Object> handleJakartaBadRequestException(RuntimeException ex, WebRequest request) {
        if (ex instanceof jakarta.validation.ConstraintViolationException) {
            jakarta.validation.ConstraintViolationException violationException = (jakarta.validation.ConstraintViolationException) ex;
            List<String> errorMessages = violationException.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorsResponse(errorMessages.toArray(new String[0])));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorsResponse(ex.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class, NoSuchElementException.class,
            BadCredentialsException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorsResponse(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLIntegrityConstraintViolationException sqlEx) {
            String message = sqlEx.getMessage();
            String property = extractPropertyFromExceptionMessage(message);
            String errorMessage = property + " already exists";
            return new ResponseEntity<>(new ErrorsResponse(errorMessage), HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorsResponse("An unexpected error occurred."));
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorsResponse("An error occurred: " + ex.getMessage()));
    }

    private String extractPropertyFromExceptionMessage(String message) {
        // Example message: "Duplicate entry '0541842785' for key 'unique_phone_number'"
        if (message.contains("Duplicate entry") && message.contains("for key")) {
            int keyIndex = message.indexOf("for key");
            if (keyIndex != -1) {
                String keyName = message.substring(keyIndex + 8).replace("'", "").trim();
                return mapConstraintNameToProperty(keyName);
            }
        }
        return "Unknown property";
    }

    private String mapConstraintNameToProperty(String constraintName) {
        if (constraintName.startsWith("unique_")) {
            String property = constraintName.substring("unique_".length());
            property = property.replace('_', ' ');
            property = capitalize(property);
            return property;
        } else {
            return "Unknown property";
        }
    }
}