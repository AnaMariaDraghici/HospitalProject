package org.example.ui;

import org.example.domain.consultatie;
import org.example.service.Service;

import java.util.Scanner;

public class Console {

    private final Service service;

    public Console(Service service) {
        this.service = service;
    }

    void printMenu(){

        System.out.println("\n");
        System.out.println("------------------------------------------");
        System.out.println("1. Add consultatie");
        System.out.println("2. Delete consultatie");
        System.out.println("3. Update consultatie");
        System.out.println("4. -");
        System.out.println("5. Filter Pediatrie");
        System.out.println("6. Filter Pediatrie and time higher than 30");
        System.out.println("7. Sort by numepacient and medic");
        System.out.println("8. Sort by sectie descrescator");
        System.out.println("9. Sort by durata");
        System.out.println("10.Show all");
        System.out.println("0. Exit");
        System.out.println("------------------------------------------");
        System.out.println("\nIntroduceti optiunea:  ");

    }
    public void run() {
        Scanner scan = new Scanner(System.in);
        label:
        while (true) {
            printMenu();
            String cmd = scan.nextLine();
            switch (cmd) {
                case "1":
                    addconsultatie();
                    break;
                case "2":
                    deleteconsultatie();
                    break;
                case "3":
                    updateconsultatie();
                    break;
                case "4":
                    searchThriller();
                    break;
                case "5":
                    filterrock();
                    break;
                case "6":
                    filterrockPrice();
                    break;
                case "7":
                    sortnumepacientdurata();
                    break;
                case "8":
                    sortsectiere();
                    break;
                case "9":
                    sortPriceAsc();
                    break;
                case "10":
                    printconsultaties();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("COMANDA INVALIDA!");
                    break;
            }
        }
    }

    private void showAll() {
        System.out.println(service.findAllconsultaties());
    }

    private void searchThriller() {
        System.out.println(service.searchThriller());
    }

    private void sortsectiere() {
        service.sortBysectie().forEach(s-> System.out.println(s.getnumepacient()+"         "+s.getmedic() +"       "+s.getsectie()));
    }

    private void sortPriceAsc() {
        service.sortByPrice().forEach(s-> System.out.println(s.getId()+"     "+s.getdurata()));

    }

    private void sortnumepacientdurata() {
        service.sortBynumepacientAnddurata().forEach(s-> System.out.println(s.getId()+"         "+s.getnumepacient()+"         "+s.getdurata()));

    }

    private void filterrockPrice() {
        System.out.println(service.filterPediatrieAndHigherTime());
    }

    private void filterrock() {
        System.out.println(service.filterPediatrie());
    }


    void printconsultaties() {
        for (consultatie u : service.findAllconsultaties()) {
            System.out.println("\nId: " + u.getId() + "; \nnumepacient: " + u.getnumepacient() + "; \nmedic: " + u.getmedic() + "; \nsectie: "
                    + u.getsectie() + "; \ndurata: " + u.getdurata());
        }
    }

    private void addconsultatie() {
        Scanner scan = new Scanner(System.in);
        System.out.println("numepacient: ");
        String numepacient = scan.nextLine();
        System.out.println("medic: ");
        String medic = scan.nextLine();
        System.out.println("sectie: ");
        String sectie = scan.nextLine();
        System.out.println("durata: ");
        Double durata = scan.nextDouble();
        try{
            consultatie consultatie = new consultatie(numepacient,medic,sectie,durata);
            service.add(consultatie);}
        catch (IllegalArgumentException e) {
            System.out.println("Invalid arguments!");
        }

    }

    private void deleteconsultatie() {
        printconsultaties();
        Scanner scan = new Scanner(System.in);
        System.out.println("Id: ");
        String val = scan.nextLine();
        try{
            Long id = Long.parseLong(val);
            service.delete(id);
        }
        catch (IllegalArgumentException e) {
            System.out.println("The id can not contain letters or symbols and can not be empty!!");
        }
    }

    private void updateconsultatie() {
        printconsultaties();
        Scanner scan = new Scanner(System.in);
        System.out.println("Id consultatie you want to update");
        String val = scan.nextLine();
        Long id1 = Long.parseLong(val);
        System.out.println("numepacient: ");
        String numepacient = scan.nextLine();
        System.out.println("medic: ");
        String medic = scan.nextLine();
        System.out.println("sectie: ");
        String sectie = scan.nextLine();
        System.out.println("durata: ");
        Double durata = scan.nextDouble();
        consultatie consultatie = new consultatie(numepacient,medic,sectie,durata);
        consultatie.setId(id1);
        try{
            service.update(consultatie);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid arguments!");
        }
    }
}
