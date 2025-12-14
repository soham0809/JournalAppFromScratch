package com.journalingapp.soham.journalApp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("dev")
@SpringBootTest
class JournalAppApplicationTests {

	@Test
	void contextLoads() {
	}

}


//more annotations :: n anotations are meant to be at the top of the functions
/*
@BeforeAll() // each test case se pehle ye code chalega
@BeforeEach() // every test case se pehle ye code chalega

*/


//@AfterEach // each test case ke baad mai ye chalega
//@AfterAll // every test case se baad mai ye chalega

// example use case , before running a test case , suppose you create a csv , run the test function and then
// ,you can delete the csv created