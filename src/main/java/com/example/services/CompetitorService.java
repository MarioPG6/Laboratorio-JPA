/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.PersistenceManager;
import com.example.models.Competitor;
import com.example.models.CompetitorDTO;
import com.example.models.Credentials;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mauricio
 */
@Path("/competitors")
@Produces(MediaType.APPLICATION_JSON)
public class CompetitorService {

    @PersistenceContext(unitName = "CompetitorsPU")
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            entityManager
                    = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Competitor> competitors = new ArrayList<Competitor>();
        Competitor competitorTmp = new Competitor("Carlos", "Alvarez", 35, "7658463", "3206574839 ", "carlos.alvarez@gmail.com", "Bogota", "Colombia", false);
        Competitor competitorTmp2 = new Competitor("Gustavo", "Ruiz", 55, "2435231", "3101325467", "gustavo.ruiz@gmail.com", "Buenos Aires", "Argentina", false);
        competitors.add(competitorTmp);
        competitors.add(competitorTmp2);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitors).build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCompetitor(CompetitorDTO competitor) {

        Competitor competitorTmp = new Competitor(
                competitor.getName(),
                competitor.getSurname(),
                competitor.getAge(),
                competitor.getTelephone(),
                competitor.getCellphone(),
                competitor.getAddress(),
                competitor.getCity(),
                competitor.getCountry(),
                false
        );

        competitorTmp.setPassword(competitor.getPassword());
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(competitorTmp).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @javax.ws.rs.Consumes(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        try {
            // Simulación de búsqueda en base de datos (aquí deberías usar entityManager y consulta real)
            List<Competitor> competitors = new ArrayList<>();
            competitors.add(new Competitor("Laura", "Martinez", 28, "12345", "12345", "laura.martinez@gmail.com", "Bogotá", "Colombia", false));
            competitors.get(0).setPassword("1234");

            for (Competitor c : competitors) {
                if (c.getAddress().equals(credentials.getAddress())
                        && c.getPassword().equals(credentials.getPassword())) {
                    return Response.ok().header("Access-Control-Allow-Origin", "*").entity(c).build();
                }
            }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Correo o contraseña incorrectos").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Error en el servidor: " + e.getMessage()).build();
        }
    }

}
