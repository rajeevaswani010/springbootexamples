package com.rjasw.demo.jpa_sql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class JpaSqlApplicationTests {

	@Test
	void contextLoads() {
	}

}
