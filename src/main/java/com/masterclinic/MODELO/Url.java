/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masterclinic.MODELO;

import java.util.UUID;

/**
 *
 * @author Gott Jagger
 */
public class Url {
    private String url;
    private UUID uuid;

    public Url() {
    }

    public Url(String url, UUID uuid) {
        this.url = url;
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

   
    
}
