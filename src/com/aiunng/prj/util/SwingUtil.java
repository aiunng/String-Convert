package com.aiunng.prj.util;

import com.aiunng.prj.constant.ConverCodeStrategyEnum;
import com.intellij.ui.components.JBScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author：wangXinYu
 * @date：2021/7/16 4:56 下午
 */
public class SwingUtil {

  /**
   * 文本展示
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JLabel addLabel(String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JLabel label = new JLabel(text);
    label.setFont(font);
    label.setBounds(x, y, width, height);
    contentPanel.add(label);
    return label;
  }

  /**
   * 文本输入
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JTextArea addJTextArea(String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JTextArea textArea = new JTextArea();
    textArea.setFont(font);
    textArea.setBounds(x, y, width, height);
    textArea.setText(text);
    contentPanel.add(textArea);
    return textArea;
  }

  /**
   * 按钮
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JButton addJButton(String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JButton button = new JButton(text);
    button.setFont(font);
    button.setBounds(x, y, width, height);
    contentPanel.add(button);

    return button;
  }

  public static JBScrollPane addJBScrollPane(Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JBScrollPane scrollPane = new JBScrollPane();
    scrollPane.setFont(font);
    scrollPane.setBounds(x, y, width, height);
    // 滚动条
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    contentPanel.add(scrollPane);
    return scrollPane;
  }

  public static JComboBox addConvertTypeComboBox(Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
   for (ConverCodeStrategyEnum value : ConverCodeStrategyEnum.values()) {
      comboBox.addItem(value.getDesc());
    }

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    contentPanel.add(comboBox);

    return comboBox;
  }

}
