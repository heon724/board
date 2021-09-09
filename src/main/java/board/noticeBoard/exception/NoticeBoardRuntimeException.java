package board.noticeBoard.exception;

public class NoticeBoardRuntimeException extends RuntimeException {
    public NoticeBoardRuntimeException(String message) {
        super(message);
    }

    public NoticeBoardRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoticeBoardRuntimeException(Throwable cause) {
        super(cause);
    }

    protected NoticeBoardRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
