package bo.com.cognos.proyecto.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Temporal {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String passwordEnTextoPlano = "admin";
		System.out.println(encoder.encode("adminchelo"));
		System.out.println(encoder.encode("userchelo"));
		System.out.println(encoder.encode("rootchelo"));
		/*for(int i = 1; i < 100; i++) {
			System.out.println(encoder.encode(passwordEnTextoPlano));
		}*/
		//System.out.println("Verificacion: " + encoder.matches("adminl", "$2a$10$mM.ALmQR0rzeZWqnjMvK/euCIt5NPI3WIpY6B08toIK9ggdrxNslG"));
		/*System.out.println(encoder.encode(passwordEnTextoPlano));
		System.out.println(encoder.encode(passwordEnTextoPlano));
		System.out.println(encoder.encode(passwordEnTextoPlano));*/
	}

}
