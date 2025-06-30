package com.nccformation.gestiondestock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class GestionDeStockApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("✅ Test de démarrage de l'application réussi !");
		System.out.println("📦 L'application Spring Boot se charge correctement");
		System.out.println("🔧 Tous les beans sont configurés et injectés");
	}

}
