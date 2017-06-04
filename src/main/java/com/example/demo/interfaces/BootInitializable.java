package com.example.demo.interfaces;

import com.example.demo.controller.utility.PageController;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Damrod on 24.05.2017.
 */
public interface BootInitializable extends Initializable, ApplicationContextAware {
    void setPageParrent(PageController parentPage);
}
