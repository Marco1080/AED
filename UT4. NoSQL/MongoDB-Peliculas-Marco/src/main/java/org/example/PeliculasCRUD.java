package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class PeliculasCRUD {
    public static void EjemploInsercionMongoDB(MongoCollection<Document> pelis, Pelicula pelicula) {
        Document doc = new Document("title", pelicula.getTitle())
                .append("writer", pelicula.getWriter())
                .append("year", pelicula.getYear())
                .append("actors", pelicula.getActors())
                .append("franchise", pelicula.getFranchise())
                .append("synopsis", pelicula.getSynopsis());
        pelis.insertOne(doc);
    }

    public static void EjemploSelectsMongoDB(MongoCollection<Document> pelis){
        System.out.println("Todas las películas:");
        for (Document doc : pelis.find()) {
            System.out.println(doc.toJson());
        }
    }

    public static void EjemploModificarDocumentoMongoDB(MongoCollection<Document> pelis, String titulo, String nuevaSynopsis){
        pelis.updateOne(Filters.eq("title", titulo), Updates.set("synopsis", nuevaSynopsis));
    }

    public static void EjemploBusquedaTextualMongoDB(MongoCollection<Document> pelis, String textoBusqueda){
        pelis.createIndex(new Document("synopsis", "text"));
        System.out.println("Películas que contienen: " + textoBusqueda);
        for (Document doc : pelis.find(Filters.text(textoBusqueda))) {
            System.out.println(doc.toJson());
        }
    }

    public static void EjemploBorradoMongoDB(MongoCollection<Document> pelis, String titulo){
        pelis.deleteOne(Filters.eq("title", titulo));
    }
}
