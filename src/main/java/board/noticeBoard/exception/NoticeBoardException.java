package board.noticeBoard.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoticeBoardException extends NoticeBoradRuntimeException {
    String msgKey;
    HttpStatus status;
    String[] args;

    public NoticeBoardException(String msgKey) {
        super(msgKey);
        this.msgKey = msgKey;
    }

    public NoticeBoardException(String msgKey, String[] args) {
        super(msgKey);
        this.msgKey = msgKey;
        this.args = args;
    }

    public NoticeBoardException(HttpStatus status, String msgKey) {
        super(msgKey);
        this.msgKey = msgKey;
        this.status = status;
    }

    public NoticeBoardException(HttpStatus status, String msgKey, String[] args) {
        super(msgKey);
        this.msgKey = msgKey;
        this.status = status;
        this.args = args;
    }

    public NoticeBoardException(HttpStatus status, String msgKey, Throwable cause) {
        super(msgKey, cause);
        this.msgKey = msgKey;
        this.status = status;
    }


    public NoticeBoardException(HttpStatus status, String msgKey, String[] args, Throwable cause) {
        super(msgKey, cause);
        this.msgKey = msgKey;
        this.status = status;
        this.args = args;
    }
}

