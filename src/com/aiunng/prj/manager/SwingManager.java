package com.aiunng.prj.manager;

import static com.aiunng.prj.constant.Constant.ADVER;
import static com.aiunng.prj.constant.Constant.AUTHOR;
import static com.aiunng.prj.constant.Constant.BLOG_LINK;
import static com.aiunng.prj.constant.Constant.BLOG_TEXT;
import static com.aiunng.prj.constant.Constant.ICON_URL;
import static com.aiunng.prj.constant.Constant.TEXT_BOLD;
import static com.aiunng.prj.constant.Constant.TEXT_NORMAL;
import static com.aiunng.prj.constant.Constant.TEXT_SMALL;
import static com.aiunng.prj.constant.Constant.VERSION;
import static com.aiunng.prj.constant.ConverCodeStrategyEnum.getByDesc;
import static com.aiunng.prj.util.SwingUtil.addConvertTypeComboBox;
import static com.aiunng.prj.util.SwingUtil.addJBScrollPane;
import static com.aiunng.prj.util.SwingUtil.addJButton;
import static com.aiunng.prj.util.SwingUtil.addJTextArea;
import static com.aiunng.prj.util.SwingUtil.addLabel;

import com.aiunng.prj.constant.ConverCodeStrategyEnum;
import com.aiunng.prj.entity.FormatCommand;
import com.aiunng.prj.util.SmartFormatUtil;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author：wangXinYu
 * @date：2021/10/21 6:41 下午
 */
public class SwingManager {

  public static void createAndShowGUI() {

    // 创建及设置窗口
    JFrame frame = new JFrame("String-Convert");
    frame.setBounds(360, 100, 720, 660);

    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    JPanel contentPanel = new JPanel();
    contentPanel.setBorder(JBUI.Borders.empty(5));
    contentPane.add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    int ctrlX = 30;
    int ctrlY = 20;
    int ctrlWidth = 60;
    int ctrlHeight = 25;
    int ctrlXOffSet = 130;
    int ctrlYOffSet = 30;
    int ctrlXOffSetL2 = 55;

    // 控制区
    addLabel("原分隔符：", TEXT_SMALL, ctrlX, ctrlY, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea origSplitCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY, ctrlWidth, ctrlHeight, contentPanel);

    addLabel("新分隔符：", TEXT_SMALL, ctrlX, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea newSplitCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);

    ctrlX = ctrlX + ctrlXOffSet;
    addLabel("开始符号：", TEXT_SMALL, ctrlX, ctrlY, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea openCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY, ctrlWidth, ctrlHeight, contentPanel);

    addLabel("结束符号：", TEXT_SMALL, ctrlX, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea closeCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);

    ctrlX = ctrlX + ctrlXOffSet;
    addLabel("转码类型：", TEXT_SMALL, ctrlX, ctrlY+ ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    ctrlX = ctrlX + ctrlXOffSetL2;
    JComboBox convertCtrl = addConvertTypeComboBox(TEXT_SMALL, ctrlX, ctrlY+ ctrlYOffSet, ctrlWidth + 40, ctrlHeight + 6, contentPanel);

    // 输入区
    JTextArea inputView = addJTextArea("\n\n  请输入待处理文本...", TEXT_SMALL, 30, 80, 300, 510, contentPanel);

    JButton button = addJButton("->", TEXT_NORMAL, 334, 300, 53, 40, contentPanel);

    // 结果展示区
    JBScrollPane resultView = addJBScrollPane(TEXT_SMALL, 390, 80, 300, 510, contentPanel);
    JTextArea answer = new JTextArea(1, 1);
    resultView.setViewportView(answer);
    answer.setText("\n\n  处理结果...");

    //按钮提交监听事件
    button.addActionListener(e -> {
      // 用户输入的指令
      FormatCommand formatCommand = new FormatCommand();
      formatCommand.setInput(inputView.getText());
      formatCommand.setOrigSplit(origSplitCtrl.getText());
      formatCommand.setNewSplit(newSplitCtrl.getText());
      formatCommand.setOpen(openCtrl.getText());
      formatCommand.setClose(closeCtrl.getText());
      formatCommand.setConverCodeStrategy(getByDesc(convertCtrl.getSelectedItem().toString()));
      // 执行处理
      String splitResult = SmartFormatUtil.splitFormat(formatCommand);
      // 设置处理后的文本
      answer.setText(splitResult);
    });

    /**
     * 帮助信息
     */
    buildHelpRegion(contentPanel);

    // 关闭按钮
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // 显示窗口
    frame.setVisible(true);

  }


  /**
   * 帮助弹窗
   *
   * @param contentPanel
   */
  private static void buildHelpRegion(JPanel contentPanel) {

    JButton cfgButton = addJButton("help", TEXT_NORMAL, 608, 600, 90, 30, contentPanel);

    cfgButton.addActionListener(e -> {
      JDialog jDialog = new JDialog();
      jDialog.setTitle("help");
      jDialog.setBounds(610, 310, 220, 180);
      jDialog.setVisible(true);
      jDialog.setLayout(null);
      // 禁止用户调整窗口大小
      jDialog.setResizable(false);

      Container contentPane = jDialog.getContentPane();

      JLabel imgLabel = new JLabel();
      ImageIcon img = null;
      try {
        img = new ImageIcon(new URL(ICON_URL));
      } catch (MalformedURLException me) {
        me.printStackTrace();
      }
      imgLabel.setIcon(img);
      imgLabel.setBounds(85, 10, 50, 50);

      int y1 = 70;
      int offset1 = 20;

      JLabel versionLabel = new JLabel(VERSION);
      versionLabel.setBounds(55, y1, 150, 25);
      versionLabel.setFont(TEXT_BOLD);

      y1 = y1 + offset1;
      JLabel textLabel = new JLabel(ADVER);
      textLabel.setBounds(10, y1, 220, 25);
      textLabel.setFont(TEXT_BOLD);

      y1 = y1 + offset1;
      JLabel linklabel = new JLabel(BLOG_TEXT);
      // 光标类型
      linklabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      linklabel.setBounds(35, y1, 200, 25);
      linklabel.setFont(TEXT_BOLD);

      // 鼠标监听
      linklabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            //打开网址
            Desktop.getDesktop().browse(new URI(BLOG_LINK));
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });

      y1 = y1 + offset1;
      JLabel authorLabel = new JLabel(AUTHOR);
      authorLabel.setBounds(70, y1, 100, 25);
      authorLabel.setFont(TEXT_BOLD);

      contentPane.add(imgLabel);
      contentPane.add(versionLabel);
      contentPane.add(textLabel);
      contentPane.add(linklabel);
      contentPane.add(authorLabel);
    });
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }
}
