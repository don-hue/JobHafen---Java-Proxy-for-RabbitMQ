package com.JobHafen.Proxy.dto;

public class Company {
    private Long id;
    private String company_name;
    private String url;
    private String api;
    private boolean show_company;

    public Company(Long id, String company_name, String url, String api, boolean show_company) {
        this.company_name = company_name;
        this.url = url;
        this.api = api;
        this.show_company = show_company;
        this.id =id;

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public boolean isShow_company() {
        return show_company;
    }

    public void setShow_company(boolean show_company) {
        this.show_company = show_company;
    }
}
