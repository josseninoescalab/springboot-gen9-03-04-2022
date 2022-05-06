package com.escalab.mediappbackend.dto;

public class RespuestaDTO {

    private String document;

    public RespuestaDTO(String document) {
        this.document = document;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
