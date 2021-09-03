package board.noticeBoard.exception;

public class NoticeBoradRuntimeException extends RuntimeException{
    public NoticeBoradRuntimeException(String message) {
        super(message);
    }
    public NoticeBoradRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


    public NoticeBoradRuntimeException(Throwable cause) {
        super(cause);
    }


    protected NoticeBoradRuntimeException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}