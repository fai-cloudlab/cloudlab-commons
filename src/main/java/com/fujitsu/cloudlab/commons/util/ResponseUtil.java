package com.fujitsu.cloudlab.commons.util;

import com.fujitsu.cloudlab.commons.exception.ApiException;
import com.fujitsu.cloudlab.commons.exception.ErrorResponses;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ResponseUtil {

  private ResponseUtil() {}

  private static Gson gson = new Gson();

  private static final String SUCCESS_RES = "Success Respone => {}";
  private static final String FAILED_RES = "Failed Respone => {}";

  public static <T> T process(Class<T> c, ResponseEntity<String> re, String app, String msg)
      throws ApiException {
    try {
      if (re.getStatusCodeValue() == HttpStatus.OK.value() && re.getBody() != null) {
        log.info(SUCCESS_RES, msg);
        return gson.fromJson(re.getBody(), c);
      } else if (re.getStatusCodeValue() == HttpStatus.CREATED.value()
          || re.getStatusCodeValue() == HttpStatus.NO_CONTENT.value()) {
        if (re.getBody() != null) {
          return gson.fromJson(re.getBody(), c);
        }
        return null;

      } else if (re.getStatusCodeValue() == HttpStatus.MULTI_STATUS.value()
          && re.getBody() != null) {
        log.error(FAILED_RES, msg);
        ErrorResponses er = gson.fromJson(re.getBody(), ErrorResponses.class);
        throw new ApiException(
            er.getCode(), er.getMessage(), msg +": "+ er.getDeveloperMessage(), er.getMoreInfo());
      }
    } catch (JsonSyntaxException e) {
      msg = msg+": "+e.getMessage();
    }
    log.error(FAILED_RES, msg);
    throw new ApiException(app, app, msg, null);
  }
}
