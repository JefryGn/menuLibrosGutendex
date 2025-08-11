package com.example.conexion.api.models;

import java.util.ArrayList;
import java.util.List;

public class ResultsDto {

    private Integer id;
    private String title;
    private ArrayList<AuthorDto> authors;
    private List<String> summaries;
    private ArrayList<TranslatorDto> translators;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String > languages;
    private Boolean copyright;
    private String meida_type;
    private FormatDto formats;
    private Integer download_count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<AuthorDto> authors) {
        this.authors = authors;
    }

    public List<String> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<String> summaries) {
        this.summaries = summaries;
    }

    public ArrayList<TranslatorDto> getTranslators() {
        return translators;
    }

    public void setTranslators(ArrayList<TranslatorDto> translators) {
        this.translators = translators;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMeida_type() {
        return meida_type;
    }

    public void setMeida_type(String meida_type) {
        this.meida_type = meida_type;
    }

    public FormatDto getFormats() {
        return formats;
    }

    public void setFormats(FormatDto formats) {
        this.formats = formats;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    public ResultsDto() {
    }

    @Override
    public String toString() {
        return "ResultsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", summaries=" + summaries +
                ", translators=" + translators +
                ", subjects=" + subjects +
                ", bookshelves=" + bookshelves +
                ", languages=" + languages +
                ", copyright=" + copyright +
                ", meida_type='" + meida_type + '\'' +
                ", formats=" + formats +
                ", download_count=" + download_count +
                '}';
    }
}
