package com.aiunng.prj.manager;

import static com.aiunng.prj.constant.Constant.ADVER;
import static com.aiunng.prj.constant.Constant.AUTHOR;
import static com.aiunng.prj.constant.Constant.BLOG_LINK;
import static com.aiunng.prj.constant.Constant.BLOG_TEXT;
import static com.aiunng.prj.constant.Constant.DATE_CONVERT_LINK;
import static com.aiunng.prj.constant.Constant.ICON_URL;
import static com.aiunng.prj.constant.Constant.SPLIT;
import static com.aiunng.prj.constant.Constant.SQL_GENER_LINK;
import static com.aiunng.prj.constant.Constant.TEXT_BOLD;
import static com.aiunng.prj.constant.Constant.TEXT_NORMAL;
import static com.aiunng.prj.constant.Constant.TEXT_SMALL;
import static com.aiunng.prj.constant.Constant.TOMO;
import static com.aiunng.prj.constant.Constant.TOMO_TEXT_DATE_CONVERT;
import static com.aiunng.prj.constant.Constant.TOMO_TEXT_SQL_GENER;
import static com.aiunng.prj.constant.Constant.VERSION;
import static com.aiunng.prj.constant.ConverCodeStrategyEnum.getByDesc;
import static com.aiunng.prj.constant.CountStrategyEnum.getCountByDesc;
import static com.aiunng.prj.util.SwingUtil.addConvertTypeComboBox;
import static com.aiunng.prj.util.SwingUtil.addCountTypeComboBox;
import static com.aiunng.prj.util.SwingUtil.addJBScrollPane;
import static com.aiunng.prj.util.SwingUtil.addJButton;
import static com.aiunng.prj.util.SwingUtil.addJTextArea;
import static com.aiunng.prj.util.SwingUtil.addLabel;

import com.aiunng.prj.constant.ConverCodeStrategyEnum;
import com.aiunng.prj.constant.CountStrategyEnum;
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
 * @author???wangXinYu
 * @date???2021/10/21 6:41 ??????
 */
public class SwingManager {

  public static void createAndShowGUI() {

    // ?????????????????????
    JFrame frame = new JFrame("String-Convert");
    frame.setBounds(360, 100, 720, 660);

    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    JPanel contentPanel = new JPanel();
    contentPanel.setBorder(JBUI.Borders.empty(5));
    contentPane.add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    int ctrlX = 30;
    int ctrlY = 15;
    int ctrlWidth = 60;
    int ctrlHeight = 25;
    int ctrlXOffSet = 130;
    int ctrlYOffSet = 30;
    int ctrlXOffSetL2 = 55;

    // ?????????
    addLabel("????????????", TEXT_SMALL, ctrlX, ctrlY, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea origSplitCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY, ctrlWidth, ctrlHeight, contentPanel);

    addLabel("????????????", TEXT_SMALL, ctrlX, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea newSplitCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);

    ctrlX = ctrlX + ctrlXOffSet;
    addLabel("?????????", TEXT_SMALL, ctrlX, ctrlY, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea openCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY, ctrlWidth, ctrlHeight, contentPanel);

    addLabel("?????????", TEXT_SMALL, ctrlX, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea closeCtrl = addJTextArea(null, TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);

    ctrlX = ctrlX + ctrlXOffSet;
    addLabel("?????????", TEXT_SMALL, ctrlX, ctrlY, ctrlWidth, ctrlHeight, contentPanel);
    JTextArea countStr = addJTextArea(null, TEXT_SMALL, ctrlX + 35, ctrlY, ctrlWidth - 10, ctrlHeight, contentPanel);
    JComboBox countCtrl = addCountTypeComboBox(TEXT_SMALL, ctrlX + 30 + ctrlXOffSetL2, ctrlY, ctrlWidth + 10, ctrlHeight + 6, contentPanel);

    addLabel("???????????????", TEXT_SMALL, ctrlX, ctrlY + ctrlYOffSet, ctrlWidth, ctrlHeight, contentPanel);
    JComboBox convertCtrl = addConvertTypeComboBox(TEXT_SMALL, ctrlX + ctrlXOffSetL2, ctrlY + ctrlYOffSet, ctrlWidth + 40, ctrlHeight + 6,
        contentPanel);

    // ?????????
    JTextArea inputView = addJTextArea("\n\n  ????????????????????????...", TEXT_SMALL, 30, 80, 300, 510, contentPanel);

    JButton button = addJButton("->", TEXT_NORMAL, 334, 300, 53, 40, contentPanel);

    // ???????????????
    JBScrollPane resultView = addJBScrollPane(TEXT_SMALL, 390, 80, 300, 510, contentPanel);
    JTextArea answer = new JTextArea(1, 1);
    resultView.setViewportView(answer);
    answer.setText("\n\n  ????????????...");

    //????????????????????????
    button.addActionListener(e -> {
      // ?????????????????????
      FormatCommand formatCommand = new FormatCommand();
      formatCommand.setInput(inputView.getText());
      formatCommand.setOrigSplit(origSplitCtrl.getText());
      formatCommand.setNewSplit(newSplitCtrl.getText());
      formatCommand.setOpen(openCtrl.getText());
      formatCommand.setClose(closeCtrl.getText());
      formatCommand.setCountStr(countStr.getText());

      // ????????????????????????????????????
      ConverCodeStrategyEnum codeStrategy = getByDesc(convertCtrl.getSelectedItem().toString());
      CountStrategyEnum countStrategy = getCountByDesc(countCtrl.getSelectedItem().toString());
      formatCommand.setConverCodeStrategy(codeStrategy);
      formatCommand.setCountStrategy(countStrategy);

      // ????????????
      String splitResult;
      // ????????????
      if (!CountStrategyEnum.NONE.equals(countStrategy)) {
        int count = SmartFormatUtil.calcCount(formatCommand);
        splitResult = String.valueOf(count);
      } else {
        // ?????????????????????
        splitResult = SmartFormatUtil.splitFormat(formatCommand);
      }
      // ????????????????????????
      answer.setText(splitResult);
    });

    /**
     * ????????????
     */
    buildHelpRegion(contentPanel);

    // ????????????
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // ????????????
    frame.setVisible(true);

  }


  /**
   * ????????????
   *
   * @param contentPanel
   */
  private static void buildHelpRegion(JPanel contentPanel) {

    JButton cfgButton = addJButton("help", TEXT_NORMAL, 608, 600, 90, 30, contentPanel);

    cfgButton.addActionListener(e -> {
      JDialog jDialog = new JDialog();
      jDialog.setTitle("help");
      jDialog.setBounds(610, 310, 225, 250);
      jDialog.setVisible(true);
      jDialog.setLayout(null);
      // ??????????????????????????????
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
      // ????????????
      linklabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      linklabel.setBounds(35, y1, 200, 25);
      linklabel.setFont(TEXT_BOLD);

      // ????????????
      linklabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            //????????????
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

      y1 = y1 + offset1 + 10;
      JLabel splitLabel = new JLabel(SPLIT);
      splitLabel.setBounds(0, y1, 300, 5);
      splitLabel.setFont(TEXT_BOLD);

      y1 = y1 + 10;
      JLabel tomoLabel = new JLabel(TOMO);
      tomoLabel.setBounds(10, y1, 100, 25);
      tomoLabel.setFont(TEXT_BOLD);

      y1 = y1 + offset1;
      JLabel dateConvertLink = new JLabel(TOMO_TEXT_DATE_CONVERT);
      // ????????????
      dateConvertLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
      dateConvertLink.setBounds(20, y1, 80, 25);
      dateConvertLink.setFont(TEXT_BOLD);

      // ????????????
      dateConvertLink.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            //????????????
            Desktop.getDesktop().browse(new URI(DATE_CONVERT_LINK));
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });

      JLabel sqlGenerLink = new JLabel(TOMO_TEXT_SQL_GENER);
      // ????????????
      sqlGenerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
      sqlGenerLink.setBounds(100, y1, 80, 25);
      sqlGenerLink.setFont(TEXT_BOLD);

      // ????????????
      sqlGenerLink.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            //????????????
            Desktop.getDesktop().browse(new URI(SQL_GENER_LINK));
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });

      contentPane.add(imgLabel);
      contentPane.add(versionLabel);
      contentPane.add(textLabel);
      contentPane.add(linklabel);
      contentPane.add(authorLabel);
      contentPane.add(splitLabel);
      contentPane.add(tomoLabel);
      contentPane.add(dateConvertLink);
      contentPane.add(sqlGenerLink);
    });
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }
}
