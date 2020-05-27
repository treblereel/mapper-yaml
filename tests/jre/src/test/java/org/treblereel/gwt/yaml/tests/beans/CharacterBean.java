package org.treblereel.gwt.yaml.tests.beans;

import java.util.Objects;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 3/27/20
 */
@YAMLMapper
public class CharacterBean {

    private char charVal;

    public char getCharVal() {
        return charVal;
    }

    public void setCharVal(char charVal) {
        this.charVal = charVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharacterBean)) {
            return false;
        }
        CharacterBean that = (CharacterBean) o;
        return getCharVal() == that.getCharVal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharVal());
    }
}