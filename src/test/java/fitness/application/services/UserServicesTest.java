package fitness.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServicesTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemServices.APPLICATION_FOLDER = ".test-fitnessapplication";
        FileSystemServices.initDirectory();
        FileUtils.cleanDirectory(FileSystemServices.getApplicationHomeFolder().toFile());
        UserServices.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserServices.getDatabase().close();
        System.out.println("After Each");
    }

}