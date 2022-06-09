package pl.bartoszmatras.projektto2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmatras.projektto2.dto.OperatorAuthenticationResultDto;
import pl.bartoszmatras.projektto2.dto.OperatorCredentialsDto;
import pl.bartoszmatras.projektto2.entity.Operator;
import pl.bartoszmatras.projektto2.repository.OperatorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorRepository operatorRepository;

    @PostMapping("/operators")
    public Operator newOperator(@RequestBody Operator operator){
        return operatorRepository.save(operator);
    }

    @GetMapping("/operators")
    public List<Operator> listOperators(){
        return operatorRepository.findAll();
    }

    @DeleteMapping("/operators")
    public ResponseEntity deleteOperator(@RequestBody Long idOperator){
        operatorRepository.deleteById(idOperator);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify_operator_credentials")
    public OperatorAuthenticationResultDto verifyOperatorCredentials(@RequestBody OperatorCredentialsDto operatorCredentialsDto){
        Optional<Operator> operatorOptional = operatorRepository.findByLogin(operatorCredentialsDto.getLogin());

        if(!operatorOptional.isPresent()){
            return OperatorAuthenticationResultDto.createUnauthenticated();
        }

        Operator operator = operatorOptional.get();

        if (!operator.getPassword().equals(operatorCredentialsDto.getPassword())){
            return OperatorAuthenticationResultDto.createUnauthenticated();
        }else{
            return OperatorAuthenticationResultDto.of(operator);
        }
    }

}
