package com.aiunng.prj.util;

import static com.aiunng.prj.constant.ConverCodeStrategyEnum.BASE64_ENCODE;
import static com.aiunng.prj.constant.ConverCodeStrategyEnum.URL_ENCODE;

import cn.hutool.core.util.StrUtil;
import com.aiunng.prj.constant.ConverCodeStrategyEnum;
import com.aiunng.prj.constant.CountStrategyEnum;
import com.aiunng.prj.entity.FormatCommand;

/**
 * @author：wangXinYu
 * @date：2021/10/21 6:37 下午
 */
public class SmartFormatUtil {

  /**
   * 将指定字符串按指定字符分隔，替换分割符号，分割后字符串首尾填充
   *
   * @return
   */
  public static String splitFormat(FormatCommand command) {
    String input = command.getInput();
    String origSplit = command.getOrigSplit();
    String newSplit = command.getNewSplit();
    String open = command.getOpen();
    String close = command.getClose();
    ConverCodeStrategyEnum converStrategy = command.getConverCodeStrategy();

    StringBuilder output = new StringBuilder();

    boolean splitEnable = StrUtil.isNotEmpty(origSplit) || StrUtil.isNotEmpty(newSplit);
    boolean openEnable = StrUtil.isNotEmpty(open);
    boolean closeEnable = StrUtil.isNotEmpty(close);

    String[] splits;
    if (splitEnable) {
      splits = input.split(origSplit);
    } else {
      splits = new String[]{input};
    }

    for (String s : splits) {
      if (openEnable) {
        output.append(open);
      }

      output.append(converStrategy.convert(s));

      if (closeEnable) {
        output.append(close);
      }

      output.append(newSplit);
    }

    return output.toString();
  }

  /**
   * 计数
   * @param command
   * @return
   */
  public static int calcCount(FormatCommand command) {
    CountStrategyEnum countStrategy = command.getCountStrategy();
    return countStrategy.convert(command);
  }

  private static void splitFormatTest() {
    FormatCommand formatCommand1 = new FormatCommand();
    formatCommand1.setInput("R80017556\n0.61kg กระเป๋าถือสุภาพสตรี&\nR80017558\n");
    formatCommand1.setOrigSplit("\n");
    formatCommand1.setNewSplit(",\n");
    formatCommand1.setOpen("'");
    formatCommand1.setClose("'");
    formatCommand1.setConverCodeStrategy(URL_ENCODE);

    System.out.println(splitFormat(formatCommand1));

    FormatCommand formatCommand2 = new FormatCommand();
    formatCommand2.setInput("R80017556 测试 R80017558 ");
    formatCommand2.setOrigSplit(" ");
    formatCommand2.setNewSplit(",\n");
    formatCommand2.setOpen("(");
    formatCommand2.setClose(")");
    formatCommand2.setConverCodeStrategy(BASE64_ENCODE);
    System.out.println(splitFormat(formatCommand2));
  }


  public static void main(String[] args) {
    splitFormatTest();
  }
}
