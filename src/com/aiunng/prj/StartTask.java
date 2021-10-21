package com.aiunng.prj;

import static com.aiunng.prj.manager.SwingManager.createAndShowGUI;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author：wangXinYu
 * @date：2021/10/21 6:31 下午
 */
public class StartTask extends AnAction {
  @Override
  public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
    createAndShowGUI();
  }
}
