package hu.syscode.teszt_feladat.address;

import java.util.Locale;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
public class AddressController {

	private final Faker faker = new Faker(new Locale("hu", "HU"));

	@GetMapping("/address")
	public AddressDto one() {
		return new AddressDto(UUID.randomUUID().toString(), faker.address().fullAddress());
	}

}
