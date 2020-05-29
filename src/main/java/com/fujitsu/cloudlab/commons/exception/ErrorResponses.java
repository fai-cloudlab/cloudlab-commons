package com.fujitsu.cloudlab.commons.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@XmlRootElement(name = "errorResponses")
@XmlAccessorType(XmlAccessType.PROPERTY)
@ToString
public class ErrorResponses implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String message;
  private String developerMessage;
  private List<MoreMessage> moreInfo = new ArrayList<>();
}
