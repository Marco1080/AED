package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("cine");
        MongoCollection<Document> pelis = database.getCollection("pelis");

        Pelicula fightClub = new Pelicula("Fight Club", "Chuck Palahniuk", 1999, Arrays.asList("Brad Pitt", "Edward Norton"), null, "Bilbo");
        PeliculasCRUD.EjemploInsercionMongoDB(pelis, fightClub);

        PeliculasCRUD.EjemploSelectsMongoDB(pelis);

        PeliculasCRUD.EjemploModificarDocumentoMongoDB(pelis, "Fight Club", "Nueva synopsis...");

        PeliculasCRUD.EjemploBusquedaTextualMongoDB(pelis, "Bilbo");

        PeliculasCRUD.EjemploBorradoMongoDB(pelis, "Long dark");


    }
}
