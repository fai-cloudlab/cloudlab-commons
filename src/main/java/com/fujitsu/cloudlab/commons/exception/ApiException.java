package com.fujitsu.cloudlab.commons.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class ApiException extends Exception {

  private static final long serialVersionUID = 1L;

  private final String code;
  private final String message;
  private final String developerMessage;
  private final List<MoreMessage> moreInfo = new ArrayList<>();

  public ApiException(
      String code, String message, String developerMessage, List<MoreMessage> moreInfo) {
    super(message);
    this.code = code;
    this.message = message;
    this.developerMessage = developerMessage;
    if (moreInfo != null) this.moreInfo.addAll(moreInfo);
    log.error(this.toString());
  }

  public ApiException(
      String code,
      String message,
      String developerMessage,
      List<MoreMessage> moreInfo,
      Throwable inCause) {
    super(inCause);
    this.code = code;
    this.message = message;
    this.developerMessage = developerMessage;
    if (moreInfo != null) this.moreInfo.addAll(moreInfo);
    log.error(this.toString());
  }
}
