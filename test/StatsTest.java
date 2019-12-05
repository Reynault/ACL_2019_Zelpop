import org.junit.jupiter.api.*;

/**
 * Class test providing tests for defence, attack, regen and HP stats for entities
 */
class StatsTest {


    @BeforeAll
    public void init() {
    }

    @BeforeEach
    public void initEach() {
    }

    @Nested
    @DisplayName("Defence")
    class Counter {

        @Nested
        @DisplayName("Right")
        class Right {

            /**
             * Testing the case where an entity has a positive defence stat and
             * another entity is attacking, and then dying from the counter
             */
            @Test
            public void positive_tuer() {

            }

        }

    }

    @Nested
    @DisplayName("Regen")
    class Regen {

    }

    @Nested
    @DisplayName("Attack")
    class Attack {

    }

    @Nested
    @DisplayName("HP")
    class HP {

    }

}