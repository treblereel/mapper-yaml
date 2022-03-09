package org.treblereel.gwt.yaml.tests.beans;

import java.util.Objects;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

@YAMLMapper
public class BooleanBean {

    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BooleanBean)) {
            return false;
        }
        BooleanBean that = (BooleanBean) o;
        return isCheck() == that.isCheck();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCheck());
    }
}
