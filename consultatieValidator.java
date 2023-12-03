package org.example.domain.validator;


import org.example.domain.consultatie;

public class consultatieValidator implements Validator<consultatie> {
    @Override
    public void validate(consultatie entity) throws ValidationException {
        if(entity.getnumepacient() == null)
            throw new ValidationException("numepacientle nu poate fi null");
        if(entity.getmedic() == null)
            throw new ValidationException("medicul nu poate fi null");
        if(entity.getsectie() == null)
            throw new ValidationException("sectia nu poate fi null");
        //if((entity.getsectie() != "Pediatrie")||(entity.getsectie() != "Psihiatrie" )||(entity.getsectie() !="Urologie")||(entity.getsectie() !="Cardiologie")||(entity.getsectie() !="Ginecologie"))
           // throw new ValidationException("Nu exista aceasta sectie in spitalul nostru :)");
        if(entity.getdurata() <=0)
            throw new ValidationException("durataul nu poate fi null sau negativ");
    }
}
