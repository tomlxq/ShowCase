package com.tom.demo.model;

public class Module {
    private Integer module_id;

    private String module_name;

    private String module_url;

    private Integer parent_id;

    private Integer leaf;

    private Integer expanded;

    private Integer display_index;

    private Integer is_display;

    private String en_module_name;

    private String icon_css;

    private String information;

    public Integer getModule_id() {
        return module_id;
    }

    public void setModule_id(Integer module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name == null ? null : module_name.trim();
    }

    public String getModule_url() {
        return module_url;
    }

    public void setModule_url(String module_url) {
        this.module_url = module_url == null ? null : module_url.trim();
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }

    public Integer getExpanded() {
        return expanded;
    }

    public void setExpanded(Integer expanded) {
        this.expanded = expanded;
    }

    public Integer getDisplay_index() {
        return display_index;
    }

    public void setDisplay_index(Integer display_index) {
        this.display_index = display_index;
    }

    public Integer getIs_display() {
        return is_display;
    }

    public void setIs_display(Integer is_display) {
        this.is_display = is_display;
    }

    public String getEn_module_name() {
        return en_module_name;
    }

    public void setEn_module_name(String en_module_name) {
        this.en_module_name = en_module_name == null ? null : en_module_name.trim();
    }

    public String getIcon_css() {
        return icon_css;
    }

    public void setIcon_css(String icon_css) {
        this.icon_css = icon_css == null ? null : icon_css.trim();
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information == null ? null : information.trim();
    }
}