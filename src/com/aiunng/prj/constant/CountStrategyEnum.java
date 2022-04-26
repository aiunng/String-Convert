package com.aiunng.prj.constant;

import cn.hutool.core.util.StrUtil;
import com.aiunng.prj.entity.FormatCommand;
import com.intellij.openapi.util.text.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * 转换策略
 *
 * @author：wangXinYu
 * @date：2021/10/21 6:35 下午
 */
public enum CountStrategyEnum {

  /**
   * 输出入参
   */
  NONE(101, "无") {
    @Override
    protected int doConvert(FormatCommand input) {
      return 0;
    }
  },

  STR_LENGTH(102, "长度") {
    @Override
    protected int doConvert(FormatCommand input) throws UnsupportedEncodingException {
      return Optional.ofNullable(input).map(FormatCommand::getInput).map(String::length).orElse(0);
    }
  },

  STR_COUNT(103, "个数") {
    @Override
    protected int doConvert(FormatCommand input) throws UnsupportedEncodingException {
      String inputStr = Optional.ofNullable(input).map(FormatCommand::getInput).orElse("");
      String countStr = Optional.ofNullable(input).map(FormatCommand::getCountStr).orElse("");
      if (StrUtil.isBlank(inputStr) || StrUtil.isBlank(countStr)) {
        return 0;
      }
      return countStr(inputStr, countStr);
    }
  },

  ;

  CountStrategyEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private final int code;
  private final String desc;

  protected abstract int doConvert(FormatCommand input) throws UnsupportedEncodingException;

  public int convert(FormatCommand input) {
    int output = 0;
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

  public static CountStrategyEnum getCountByDesc(String desc) {
    for (CountStrategyEnum value : CountStrategyEnum.values()) {
      if (StringUtil.equals(desc, value.getDesc())) {
        return value;
      }
    }
    return NONE;
  }

  /**
   * 查询字符串str2在字符串str1中的数量
   *
   * @param str1
   * @param str2
   * @return
   */
  private static int countStr(String str1, String str2) {
    String replaceStr = str1.replaceAll(str2, "");
    int str1L = str1.length();
    int repL = replaceStr.length();
    int str2L = str2.length();
    return (str1L - repL) / str2L;
  }
}
