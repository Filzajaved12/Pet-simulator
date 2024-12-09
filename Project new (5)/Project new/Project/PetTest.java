import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    private Pet<String> pet;

    @BeforeEach
    void setUp() {
        pet = new Pet<>("Buddy", "Dog", 100, 80);
    }

    @Test
    void testInitialization() {
        assertEquals("Buddy", pet.getName());
        assertEquals("Dog", pet.getType());
        assertEquals(100, pet.getHealth());
        assertEquals(80, pet.getHappiness());
        assertEquals(0, pet.getHunger());
    }

    @Test
    void testFeedAction() {
        pet.performAction("feed");
        assertEquals(0, pet.getHunger(), "Hunger should decrease to a minimum of 0");
        assertEquals(85, pet.getHappiness(), "Happiness should increase by 5 after feeding");
        assertEquals(100, pet.getHealth(), "Health should increase slightly but capped at 100");
    }

    @Test
    void testPlayAction() {
        pet.performAction("play");
        assertEquals(95, pet.getHappiness(), "Happiness should increase by 15 after playing");
        assertEquals(5, pet.getHunger(), "Hunger should increase by 5 after playing");
        assertEquals(100, pet.getHealth(), "Health should increase slightly but capped at 100");
    }





    @Test
    void testGetEmotionHappy() {
        // Test when both health and happiness are well above thresholds
        assertEquals("happy", pet.getEmotion(), "Emotion should be happy for high health and happiness");
    }

    @Test
    void testGetEmotionNeutral() {
        // Decrease happiness to test neutral emotion
        for (int i = 0; i < 15; i++) {
            pet.performAction("play");
        }
        assertEquals("neutral", pet.getEmotion(), "Emotion should be neutral when happiness or health is lower");
    }
}
