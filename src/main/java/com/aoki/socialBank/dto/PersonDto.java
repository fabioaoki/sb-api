package com.aoki.socialBank.dto;

import java.io.Serializable;
import java.util.Date;

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

	private String name;

	private Date birthDate;

	private String email;

	private String address;

	@Override
	public String toString() {
		return "PersonDto [id=" + id + ", name=" + name + ", birthDate="
				+birthDate + ", email=" + email + ", address="
				+ address + "]";
	}

}
