/*
 * Copyright © 2022 Treblereel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.treblereel.gwt.yaml.demo;

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
