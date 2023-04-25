package racingcar.controller.dto;


public class ExceptionResponse {
        private final int code;
        private final String status;
        private final String message;

        public ExceptionResponse(final int code, final String status, final String message) {
                this.code = code;
                this.status = status;
                this.message = message;
        }

        public int getCode() {
                return code;
        }

        public String getStatus() {
                return status;
        }

        public String getMessage() {
                return message;
        }
}
