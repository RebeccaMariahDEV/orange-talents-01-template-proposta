package br.com.zup.beta.microServico.core.validador;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {CpfCnpjValidador.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CpfCnpj {

    //faz o controle
    //nativo do spring
    String message() default "CPF/CNPJ inválido";

    Class<?>[] groups() default {};

    //nativo do spring
    Class<? extends Payload>[] payload() default {};

}
