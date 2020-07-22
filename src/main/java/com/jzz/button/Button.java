package com.jzz.button;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:jzz
 * @date:2020/6/30
 */
public class Button {
    private List<AbstractButton> button = new ArrayList<>();

    public List<AbstractButton> getButton() {
        return button;
    }

    public void setButton(List<AbstractButton> button) {
        this.button = button;
    }

}
