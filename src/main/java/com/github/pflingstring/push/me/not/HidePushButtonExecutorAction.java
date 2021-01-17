package com.github.pflingstring.push.me.not;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.changes.actions.BaseCommitExecutorAction;
import com.intellij.vcs.commit.AmendCommitHandler;
import com.intellij.vcs.commit.CommitWorkflowHandler;

public class HidePushButtonExecutorAction extends BaseCommitExecutorAction {

  @Override
  public void update(@NotNull AnActionEvent event) {
    final Presentation presentation = event.getPresentation();

    @SuppressWarnings("UnstableApiUsage")
    final boolean isAmendCommit = Optional
        .ofNullable(event.getDataContext().getData(VcsDataKeys.COMMIT_WORKFLOW_HANDLER))
        .map(CommitWorkflowHandler::getAmendCommitHandler)
        .map(AmendCommitHandler::isAmendCommitMode)
        .orElse(false);

    final String presentationText = isAmendCommit
        ? VcsBundle.message("amend.action.name", presentation.getTextWithMnemonic())
        : presentation.getTextWithMnemonic();

    presentation.setText(presentationText);
    presentation.setVisible(false);

    super.update(event);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    super.actionPerformed(event);
  }

}