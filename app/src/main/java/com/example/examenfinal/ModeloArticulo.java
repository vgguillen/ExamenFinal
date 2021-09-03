package com.example.examenfinal;

public class ModeloArticulo {
    private String title;
    private String resumen;
    private String doi;
    private String submission_id;


    public ModeloArticulo(String title, String resumen, String doi, String submission_id) {
        this.title = title;
        this.resumen = resumen;
        this.doi = doi;
        this.submission_id = submission_id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(String submission_id) {
        this.submission_id = submission_id;
    }
}
