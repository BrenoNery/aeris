package br.com.caelum.aeris.service;

import java.lang.annotation.*;

import org.hibernate.validator.ValidatorClass;

@ValidatorClass(MaiusculoValidator.class)

@Target( {ElementType.FIELD, ElementType.METHOD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface Maiusculo {
	//String message() default "O campo deve ser sempre em letras maiúsculas";
	String message() default "{validator.Maiusculo}";
}
