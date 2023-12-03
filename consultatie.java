package org.example.domain;

import java.util.Objects;


public class consultatie extends Entity<Long>{
    private String numepacient;
    private String medic;
    private String sectie;
    private double durata;

    public consultatie(String numepacient, String medic, String sectie, double durata) {
        this.numepacient = numepacient;
        this.medic = medic;
        this.sectie = sectie;
        this.durata = durata;
    }
    public consultatie(){}

    public String getnumepacient() {
        return numepacient;
    }

    public void setnumepacient(String numepacient) {
        this.numepacient = numepacient;
    }

    public String getmedic() {
        return medic;
    }

    public void setmedic(String medic) {
        this.medic = medic;
    }

    public String getsectie() {
        return sectie;
    }

    public void setsectie(String sectie) {
        this.sectie = sectie;
    }

    public double getdurata() {
        return this.durata;
    }

    public void setdurata(double durata) {
        this.durata = durata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        consultatie consultatie = (consultatie) o;
        return Double.compare(consultatie.durata, durata) == 0 && Objects.equals(numepacient, consultatie.numepacient) && Objects.equals(medic, consultatie.medic) && Objects.equals(sectie, consultatie.sectie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numepacient, medic, sectie, durata);
    }

    @Override
    public String toString() {
        return "\nconsultatie:" +
                "\nnumepacient='" + numepacient + '\'' +
                "\nmedic='" + medic + '\'' +
                "\nsectie='" + sectie + '\'' +
                "\ndurata=" + durata +
                ";\n";
    }
}