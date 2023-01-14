package org.treblereel.gwt.yaml.mapper.client;

import org.treblereel.gwt.yaml.api.annotation.YAMLMapper;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 5/27/20
 */
@YAMLMapper
public class Company {

    private String companyName;
    private String ceo;
    private String Industry;
    private int founded;
    private int employees;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", ceo='" + ceo + '\'' +
                ", Industry='" + Industry + '\'' +
                ", founded=" + founded +
                ", employees=" + employees +
                '}';
    }
}
