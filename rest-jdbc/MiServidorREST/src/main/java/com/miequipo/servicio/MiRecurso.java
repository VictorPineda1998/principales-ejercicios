/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miequipo.servicio;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/miRecurso")
public class MiRecurso {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hola Mundo que nos rodea!";
    }
}


