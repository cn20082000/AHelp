package com.cn.ahelp3.core.action;

import com.cn.ahelp3.core.model.ErrorMessage;

public interface FailureAction {
    void execute(ErrorMessage error);
}

