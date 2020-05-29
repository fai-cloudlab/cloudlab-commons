package com.fujitsu.cloudlab.commons.exception;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MoreMessage implements Serializable {
  private static final long serialVersionUID = 600032822600142347L;
  private String code;
  private String field;
  private String message;
}
