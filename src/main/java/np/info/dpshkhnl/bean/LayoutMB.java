package np.info.dpshkhnl.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by rmpestano on 07/05/17.
 */
@Named
@SessionScoped
public class LayoutMB implements Serializable {

    private String layout;

    @PostConstruct
    public void init() {
        setHorizontalLayout();
    }

    public String getLayout() {
        return layout;
    }

    public void setHorizontalLayout() {
        layout = "/WEB-INF/templates/template-horizontal.xhtml";
    }

    public void setDefaultLayout() {
        layout = "/WEB-INF/templates/template.xhtml";
    }
}
