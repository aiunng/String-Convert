package com.aiunng.prj.entity;

import com.aiunng.prj.constant.ConverCodeStrategyEnum;
import com.aiunng.prj.constant.CountStrategyEnum;

/**
 * @author：wangXinYu
 * @date：2021/10/21 6:36 下午
 */
public class FormatCommand {

  /**
   * 输入的字符串
   */
  private String input;
  /**
   * 原分割符
   */
  private String origSplit;
  /**
   * 新分割符
   */
  private String newSplit;
  /**
   * 开始字符
   */
  private String open;
  /**
   * 结束字符
   */
  private String close;
  /**
   * 计数字符串
   */
  private String countStr;
  /**
   * 编码转换策略
   */
  private ConverCodeStrategyEnum converCodeStrategy = ConverCodeStrategyEnum.NONE;
  /**
   * 计数策略
   */
  private CountStrategyEnum countStrategy = CountStrategyEnum.NONE;

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public String getOrigSplit() {
    return origSplit;
  }

  public void setOrigSplit(String origSplit) {
    this.origSplit = origSplit;
  }

  public String getNewSplit() {
    return newSplit;
  }

  public void setNewSplit(String newSplit) {
    this.newSplit = newSplit;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  public String getClose() {
    return close;
  }

  public void setClose(String close) {
    this.close = close;
  }

  public ConverCodeStrategyEnum getConverCodeStrategy() {
    return converCodeStrategy;
  }

  public void setConverCodeStrategy(ConverCodeStrategyEnum converCodeStrategy) {
    this.converCodeStrategy = converCodeStrategy;
  }

  public CountStrategyEnum getCountStrategy() {
    return countStrategy;
  }

  public void setCountStrategy(CountStrategyEnum countStrategy) {
    this.countStrategy = countStrategy;
  }

  public String getCountStr() {
    return countStr;
  }

  public void setCountStr(String countStr) {
    this.countStr = countStr;
  }
}
