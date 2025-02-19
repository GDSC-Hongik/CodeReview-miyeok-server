package gdsc.codereview.global.exception;

import gdsc.codereview.global.exception.status.ErrorStatus;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public GeneralException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ReasonDto getErrorStatus() {
        return this.errorStatus.getReason();
    }

}