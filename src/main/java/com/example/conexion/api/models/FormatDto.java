package com.example.conexion.api.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class FormatDto {

    @JsonProperty("text/plain")
    private String text;

    @JsonProperty("application/epub+zip")
    private String apliepub;

    @JsonProperty("application/x-mobipocket-ebook")
    private String aplimobi;

    @JsonProperty("text/plain; charset=us-ascii")
    private String textplain;

    @JsonProperty("application/rdf+xml")
    private String aplirdf;

    @JsonProperty("image/jpeg")
    private String image;

    @JsonProperty("application/octet-stream")
    private String aplioctet;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getApliepub() {
        return apliepub;
    }

    public void setApliepub(String apliepub) {
        this.apliepub = apliepub;
    }

    public String getAplimobi() {
        return aplimobi;
    }

    public void setAplimobi(String aplimobi) {
        this.aplimobi = aplimobi;
    }

    public String getTextplain() {
        return textplain;
    }

    public void setTextplain(String textplain) {
        this.textplain = textplain;
    }

    public String getAplirdf() {
        return aplirdf;
    }

    public void setAplirdf(String aplirdf) {
        this.aplirdf = aplirdf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAplioctet() {
        return aplioctet;
    }

    public void setAplioctet(String aplioctet) {
        this.aplioctet = aplioctet;
    }

    public FormatDto() {
    }

    @Override
    public String toString() {
        return "FormatDto{" +
                "text='" + text + '\'' +
                ", apliepub='" + apliepub + '\'' +
                ", aplimobi='" + aplimobi + '\'' +
                ", textplain='" + textplain + '\'' +
                ", aplirdf='" + aplirdf + '\'' +
                ", image='" + image + '\'' +
                ", aplioctet='" + aplioctet + '\'' +
                '}';
    }
}
