package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {

	private static final long serialVersionUID = 3361322915138943769L;

	private Long id;

	@NotEmpty(message = "nome nao pode ser vazio.")
	@Length(min = 5, max = 255, message = "nome deve conter entre 5 a 200 caracteres.")
	private String name;

	@NotNull
	@Past
	private Date birthDate;

	@NotEmpty(message = "email nao pode ser vazio.")
	private String email;

	@NotEmpty(message = "endereco nao pode ser vazio.")
	private String address;

	@Override
	public String toString() {
		return "PersonDto [id=" + id + ", name=" + name + ", birthDate="
				+birthDate + ", email=" + email + ", address="
				+ address + "]";
	}

}
