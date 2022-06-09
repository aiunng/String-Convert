package com.aiunng.prj.constant;

import cn.hutool.core.codec.Morse;
import cn.hutool.core.text.UnicodeUtil;
import com.intellij.openapi.util.text.StringUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * 转换策略
 *
 * @author：wangXinYu
 * @date：2021/10/21 6:35 下午
 */
public enum ConverCodeStrategyEnum {

  /**
   * 输出入参
   */
  NONE(101, "无") {
    @Override
    protected String doConvert(String input) {
      return input;
    }
  },

  URL_ENCODE(102, "url编码") {
    @Override
    protected String doConvert(String input) throws UnsupportedEncodingException {
      return URLEncoder.encode(input, "UTF-8");
    }
  },

  URL_DECODE(103, "url解码") {
    @Override
    protected String doConvert(String input) throws UnsupportedEncodingException {
      return URLDecoder.decode(input, "UTF-8");
    }
  },

  BASE64_ENCODE(104, "base64编码") {
    @Override
    protected String doConvert(String input) {
      return Base64.getEncoder().encodeToString(input.getBytes());
    }
  },

  BASE64_DECODE(105, "base64解码") {
    @Override
    protected String doConvert(String input) {
      return new String(Base64.getDecoder().decode(input));
    }
  },

  UNICODE_ENCODE(106, "unicode编码") {
    @Override
    protected String doConvert(String input) {
      return UnicodeUtil.toUnicode(input);
    }
  },

  UNICODE_DECODE(107, "unicode解码") {
    @Override
    protected String doConvert(String input) {
      return UnicodeUtil.toString(input);
    }
  },

  MORSE_ENCODE(108, "morse编码") {
    @Override
    protected String doConvert(String input) {
      return new Morse().encode(input);
    }
  },

  MORSE_DECODE(109, "morse解码") {
    @Override
    protected String doConvert(String input) {
      return new Morse().decode(input);
    }
  },

  ;

  ConverCodeStrategyEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private final int code;
  private final String desc;

  protected abstract String doConvert(String input) throws UnsupportedEncodingException;

  public String convert(String input) {
    String output = input.trim();
    try {
      output = this.doConvert(input);
    } catch (Exception e) {
    }
    return output;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static ConverCodeStrategyEnum getByDesc(String desc) {
    for (ConverCodeStrategyEnum value : ConverCodeStrategyEnum.values()) {
      if (StringUtil.equals(desc, value.getDesc())) {
        return value;
      }
    }
    return NONE;
  }
}
