package com.JobHafen.Proxy.dto;

import java.util.ArrayList;

public class Search {
    private Long id;
    private String keyword;
    private String portal;
    private String postal_code;
    private String radius;
    private boolean is_custom;
    private ArrayList<String> urls;

    public Search(Long id, String keyword, String portal, String postal_code, String radius,
                  boolean is_custom, ArrayList<String> urls) {
        this.keyword = keyword;
        this.portal = portal;
        this.postal_code = postal_code;
        this.radius = radius;
        this.is_custom = is_custom;
        this.urls =urls;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public boolean isIs_custom() {
        return is_custom;
    }

    public void setIs_custom(boolean is_custom) {
        this.is_custom = is_custom;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

}
