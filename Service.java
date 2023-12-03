package org.example.service;

import org.example.domain.consultatie;
import org.example.repository.consultatieRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private consultatieRepository consultatieRepository;
    private Connection connection;

    List<consultatie> consultaties;

    public Service(consultatieRepository consultatieRepository) {
        this.consultatieRepository = consultatieRepository;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MAP", "postgres", "0rleans");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        consultaties = new ArrayList<>(consultatieRepository.findAll());
    }

    public consultatie add(consultatie consultatie){
        return this.consultatieRepository.save(consultatie);

    }

    public consultatie delete(Long id)
    {
        return this.consultatieRepository.delete(id);
    }
    public consultatie update(consultatie consultatie)
    {
        return this.consultatieRepository.update(consultatie);
    }

    public consultatie findOneconsultatie(Long id)
    {
        return consultatieRepository.findOne(id);
    }

    public List<consultatie> findAllconsultaties(){
        return consultatieRepository.findAll();
    }
    public consultatie searchThriller(){
        consultatie finded = consultaties.stream().filter(b->b.getnumepacient().equals("Thriller")).findFirst().orElse(null);
        return finded;
    }

    public List<consultatie> filterPediatrie(){
        List<consultatie> consultaties2 =consultaties.stream().filter(b->b.getsectie().equals("Pediatrie")).collect(Collectors.toList());
        return consultaties2;
    }
    public List<consultatie> filterPediatrieAndHigherTime(){
        List<consultatie> consultaties2 =consultaties.stream().filter(b->b.getsectie().equals("Pediatrie") && b.getdurata()>30).collect(Collectors.toList());
        return consultaties2;
    }

    public List<consultatie> sortBynumepacientAnddurata(){
        return consultaties.stream().sorted(Comparator.comparing(consultatie::getdurata).thenComparing(consultatie::getnumepacient)).collect(Collectors.toList());
    }

    public List<consultatie> sortBysectie(){
        return consultaties.stream().sorted(Comparator.comparing(consultatie::getsectie).reversed()).collect(Collectors.toList());
    }
    public List<consultatie> sortByPrice(){
        return consultaties.stream().sorted(Comparator.comparing(consultatie::getdurata)).collect(Collectors.toList());
    }




}
