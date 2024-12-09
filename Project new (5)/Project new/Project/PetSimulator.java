//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//import java.util.Map;
//
//// Main class for the pet simulator application
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PetSimulator extends JFrame {
//    private Pet pet; // Instance of the selected Pet
//    private JLabel petImageLabel; // Label to display pet image
//    private JLabel petInfoLabel; // Label to display pet info
//    private Map<String, ImageIcon> petImages; // Map to store pet images for different states
//    private Timer animationTimer;
//
//    public PetSimulator() {
//        setTitle("Visual Pet Simulator");
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Set background color to #18392B (dark green)
//        getContentPane().setBackground(new Color(24, 57, 43)); // Dark green background
//
//        // Initialize pet and images
//        String petType = selectPetType();
//        if (petType == null) System.exit(0); // Exit if no pet type selected
//
//        String petName = getPetName();
//        pet = createPet(petType, petName);
//        loadPetImages(petType); // Load images for the chosen pet type
//
//        // Pet image label
//        petImageLabel = new JLabel(scaleImage(petImages.get("neutral"))); // Dynamically scale initial image
//        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        // Pet info label
//        petInfoLabel = new JLabel(pet.displayInfo());
//        petInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        petInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        petInfoLabel.setForeground(new Color(238, 149, 158)); // Soft pink font color (#EE959E)
//
//        // Action buttons
//        JPanel actionPanel = new JPanel();
//        JButton feedButton = new JButton("Feed " + petName);
//        JButton playButton = new JButton("Play with " + petName);
//
//        // Button customization
//        Color buttonColor = new Color(238, 149, 158); // Soft pink color (#EE959E)
//        feedButton.setBackground(buttonColor);
//        feedButton.setForeground(Color.WHITE);
//        playButton.setBackground(buttonColor);
//        playButton.setForeground(Color.WHITE);
//        feedButton.setFont(new Font("Arial", Font.BOLD, 14));
//        playButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        feedButton.addActionListener(e -> {
//            pet.feed();
//            animatePet("eating");
//            updatePetInfo();
//        });
//
//        playButton.addActionListener(e -> {
//            pet.play();
//            animatePet("playing");
//            updatePetInfo();
//        });
//
//        actionPanel.add(feedButton);
//        actionPanel.add(playButton);
//
//        // Add components to the frame
//        add(petImageLabel, BorderLayout.CENTER);
//        add(petInfoLabel, BorderLayout.NORTH);
//        add(actionPanel, BorderLayout.SOUTH);
//
//        // Add resizing listener for dynamic image scaling
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                petImageLabel.setIcon(scaleImage(petImages.get("neutral")));
//            }
//        });
//
//        setVisible(true);
//    }
//
//    // Method to load images for different pet states
//    private void loadPetImages(String petType) {
//        petImages = new HashMap<>();
//        try {
//            petImages.put("neutral", new ImageIcon("Project/out/images/" + petType + "_neutral.jpg"));
//            petImages.put("eating", new ImageIcon("Project/out/images/" + petType + "_eating.jpg"));
//            petImages.put("playing", new ImageIcon("Project/out/images/" + petType + "_playing.jpg"));
//            petImages.put("happy", new ImageIcon("Project/out/images/" + petType + "_happy.jpg"));
//        } catch (Exception e) {
//            System.out.println("Error loading images: " + e.getMessage());
//        }
//    }
//
//    // Method to animate pet actions
//    private void animatePet(String action) {
//        petImageLabel.setIcon(scaleImage(petImages.get(action))); // Scale dynamically based on screen size
//
//        if (animationTimer != null && animationTimer.isRunning()) {
//            animationTimer.stop();
//        }
//
//        animationTimer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                petImageLabel.setIcon(scaleImage(petImages.get("happy"))); // Switch to happy image after action
//                animationTimer.stop();
//            }
//        });
//
//        animationTimer.setRepeats(false);
//        animationTimer.start();
//    }
//
//    // Method to scale images dynamically based on screen size
//    private ImageIcon scaleImage(ImageIcon imageIcon) {
//        if (imageIcon == null) {
//            System.out.println("Image not found!"); // Debugging info
//            return null;
//        }
//
//        // Get screen dimensions
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int scaledWidth = (int) (screenSize.width * 0.4); // 40% of screen width
//        int scaledHeight = (int) (screenSize.height * 0.4); // 40% of screen height
//
//        // Scale the image
//        Image image = imageIcon.getImage();
//        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//
//    // Method to select pet type
//    private String selectPetType() {
//        String[] petTypes = {"Dog", "Cat", "Bird", "Monkey"};
//        return (String) JOptionPane.showInputDialog(
//                this,
//                "Choose your pet type:",
//                "Pet Selection",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                petTypes,
//                petTypes[0]);
//    }
//
//    // Method to get custom pet name from user
//    private String getPetName() {
//        return JOptionPane.showInputDialog(this, "Enter a name for your pet:");
//    }
//
//    // Method to create a pet with type and name
//    private Pet createPet(String type, String name) {
//        return new Pet(name, type, 100, 80);
//    }
//
//    // Update the pet info label
//    private void updatePetInfo() {
//        petInfoLabel.setText(pet.displayInfo());
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(PetSimulator::new);
//    }
//}
//
//
//// Pet class to manage pet attributes and behaviors
//class Pet {
//    private String name;
//    private String type;
//    private int health;
//    private int happiness;
//    private int hunger;
//
//    public Pet(String name, String type, int health, int happiness) {
//        this.name = name;
//        this.type = type;
//        this.health = health;
//        this.happiness = happiness;
//        this.hunger = 0;
//    }
//
//    // Feeding the pet decreases hunger and increases happiness
//    public void feed() {
//        if (hunger > 0) {
//            hunger -= 10;
//            happiness += 5;
//            checkHealth();
//        } else {
//            JOptionPane.showMessageDialog(null, name + " is not hungry right now!");
//        }
//    }
//
//    // Playing with the pet increases happiness and hunger
//    public void play() {
//        happiness += 15;
//        hunger += 5;
//        checkHealth();
//    }
//
//    // Method to adjust health based on happiness and hunger levels
//    private void checkHealth() {
//        if (happiness <= 0) {
//            health -= 10;
//        }
//        if (hunger >= 50) {
//            health -= 20;
//        }
//    }
//
//    // Display pet information
//    public String displayInfo() {
//        return "<html><body><center>Name: " + name +
//                "<br>Type: " + type +
//                "<br>Health: " + health +
//                "<br>Happiness: " + happiness +
//                "<br>Hunger: " + hunger + "</center></body></html>";
//    }
//}
//
//
//

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.HashMap;
//import java.util.Map;
//
//// Main class for the pet simulator application
//public class PetSimulator extends JFrame {
//    private Pet<String> pet; // Instance of the selected Pet with generics
//    private JLabel petImageLabel; // Label to display pet image
//    private JLabel petInfoLabel; // Label to display pet info
//    private Map<String, ImageIcon> petImages; // Map to store pet images for different states
//    private Timer animationTimer;
//
//    public PetSimulator() {
//        setTitle("Visual Pet Simulator");
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Set background color to #18392B (dark green)
//        getContentPane().setBackground(new Color(24, 57, 43)); // Dark green background
//
//        // Initialize pet and images
//        String petType = selectPetType();
//        if (petType == null) System.exit(0); // Exit if no pet type selected
//
//        String petName = getPetName();
//        pet = createPet(petType, petName);
//        loadPetImages(petType); // Load images for the chosen pet type
//
//        // Pet image label
//        petImageLabel = new JLabel(scaleImage(petImages.get("neutral"))); // Dynamically scale initial image
//        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        // Pet info label
//        petInfoLabel = new JLabel(pet.displayInfo());
//        petInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        petInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        petInfoLabel.setForeground(new Color(238, 149, 158)); // Soft pink font color (#EE959E)
//
//        // Action buttons
//        JPanel actionPanel = new JPanel();
//        JButton feedButton = new JButton("Feed " + petName);
//        JButton playButton = new JButton("Play with " + petName);
//
//        // Button customization
//        Color buttonColor = new Color(238, 149, 158); // Soft pink color (#EE959E)
//        feedButton.setBackground(buttonColor);
//        feedButton.setForeground(Color.WHITE);
//        playButton.setBackground(buttonColor);
//        playButton.setForeground(Color.WHITE);
//        feedButton.setFont(new Font("Arial", Font.BOLD, 14));
//        playButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        feedButton.addActionListener(e -> {
//            try {
//                pet.performAction("feed");
//                animatePet("eating");
//                updatePetInfo();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        playButton.addActionListener(e -> {
//            try {
//                pet.performAction("play");
//                animatePet("playing");
//                updatePetInfo();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        actionPanel.add(feedButton);
//        actionPanel.add(playButton);
//
//        // Add components to the frame
//        add(petImageLabel, BorderLayout.CENTER);
//        add(petInfoLabel, BorderLayout.NORTH);
//        add(actionPanel, BorderLayout.SOUTH);
//
//        // Add resizing listener for dynamic image scaling
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                petImageLabel.setIcon(scaleImage(petImages.get("neutral")));
//            }
//        });
//
//        setVisible(true);
//    }
//
//    // Method to load images for different pet states
//    private void loadPetImages(String petType) {
//        petImages = new HashMap<>();
//        try {
//            petImages.put("neutral", new ImageIcon("Project/out/images/" + petType + "_neutral.jpg"));
//            petImages.put("eating", new ImageIcon("Project/out/images/" + petType + "_eating.jpg"));
//            petImages.put("playing", new ImageIcon("Project/out/images/" + petType + "_playing.jpg"));
//            petImages.put("happy", new ImageIcon("Project/out/images/" + petType + "_happy.jpg"));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error loading images for " + petType + ": " + e.getMessage(),
//                    "Image Error", JOptionPane.ERROR_MESSAGE);
//            System.exit(1); // Exit the program gracefully
//        }
//    }
//
//    // Method to animate pet actions
//    private void animatePet(String action) {
//        petImageLabel.setIcon(scaleImage(petImages.get(action))); // Scale dynamically based on screen size
//
//        if (animationTimer != null && animationTimer.isRunning()) {
//            animationTimer.stop();
//        }
//
//        animationTimer = new Timer(1000, e -> {
//            petImageLabel.setIcon(scaleImage(petImages.get("happy"))); // Switch to happy image after action
//            animationTimer.stop();
//        });
//
//        animationTimer.setRepeats(false);
//        animationTimer.start();
//    }
//
//    // Method to scale images dynamically based on screen size
//    private ImageIcon scaleImage(ImageIcon imageIcon) {
//        if (imageIcon == null) {
//            System.out.println("Image not found!"); // Debugging info
//            return null;
//        }
//
//        // Get screen dimensions
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int scaledWidth = (int) (screenSize.width * 0.4); // 40% of screen width
//        int scaledHeight = (int) (screenSize.height * 0.4); // 40% of screen height
//
//        // Scale the image
//        Image image = imageIcon.getImage();
//        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//
//    // Method to select pet type
//    private String selectPetType() {
//        String[] petTypes = {"Dog", "Cat", "Bird", "Monkey"};
//        return (String) JOptionPane.showInputDialog(
//                this,
//                "Choose your pet type:",
//                "Pet Selection",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                petTypes,
//                petTypes[0]);
//    }
//
//    // Method to get custom pet name from user
//    private String getPetName() {
//        try {
//            String name = JOptionPane.showInputDialog(this, "Enter a name for your pet:");
//            if (name == null || name.trim().isEmpty()) {
//                throw new IllegalArgumentException("Pet name cannot be empty!");
//            }
//            return name.trim();
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
//            return getPetName(); // Retry until valid input is provided
//        }
//    }
//
//    // Method to create a pet with type and name
//    private Pet<String> createPet(String type, String name) {
//        if (type == null || type.isEmpty()) {
//            throw new IllegalArgumentException("Pet type cannot be null or empty!");
//        }
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Pet name cannot be null or empty!");
//        }
//        return new Pet<>(name, type, 100, 80);
//    }
//
//    // Update the pet info label
//    private void updatePetInfo() {
//        petInfoLabel.setText(pet.displayInfo());
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(PetSimulator::new);
//    }
//}
//
//// Pet class to manage pet attributes and behaviors with generics
//class Pet<T> {
//    private String name;
//    private String type;
//    private int health;
//    private int happiness;
//    private int hunger;
//
//    public Pet(String name, String type, int health, int happiness) {
//        this.name = name;
//        this.type = type;
//        this.health = health;
//        this.happiness = happiness;
//        this.hunger = 0;
//    }
//
//    public void performAction(T action) {
//        if (action instanceof String) {
//            String actionStr = (String) action;
//            switch (actionStr.toLowerCase()) {
//                case "feed":
//                    feed();
//                    break;
//                case "play":
//                    play();
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown action: " + action);
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid action type!");
//        }
//    }
//
//    public void feed() {
//        if (hunger > 0) {
//            hunger -= 10;
//            happiness += 5;
//            checkHealth();
//        } else {
//            throw new IllegalStateException(name + " is not hungry right now!");
//        }
//    }
//
//    public void play() {
//        happiness += 15;
//        hunger += 5;
//        checkHealth();
//    }
//
//    private void checkHealth() {
//        if (happiness <= 0) {
//            health -= 10;
//        }
//        if (hunger >= 50) {
//            health -= 20;
//        }
//    }
//
//    public String displayInfo() {
//        return "<html><body><center>Name: " + name +
//                "<br>Type: " + type +
//                "<br>Health: " + health +
//                "<br>Happiness: " + happiness +
//                "<br>Hunger: " + hunger + "</center></body></html>";
//    }
//}



//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.HashMap;
//import java.util.Map;
//
//// Main class for the pet simulator application
//public class PetSimulator extends JFrame {
//    private Pet<String> pet; // Instance of the selected Pet with generics
//    private JLabel petImageLabel; // Label to display pet image
//    private JLabel petInfoLabel; // Label to display pet info
//    private Map<String, ImageIcon> petImages; // Map to store pet images for different states
//    private Timer animationTimer;
//
//    public PetSimulator() {
//        setTitle("Visual Pet Simulator");
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Set background color to #18392B (dark green)
//        getContentPane().setBackground(new Color(24, 57, 43)); // Dark green background
//
//        // Initialize pet and images
//        String petType = selectPetType();
//        if (petType == null) System.exit(0); // Exit if no pet type selected
//
//        String petName = getPetName();
//        pet = createPet(petType, petName);
//        loadPetImages(petType); // Load images for the chosen pet type
//
//        // Pet image label
//        petImageLabel = new JLabel(scaleImage(petImages.get("neutral"))); // Dynamically scale initial image
//        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        // Pet info label
//        petInfoLabel = new JLabel(pet.displayInfo());
//        petInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        petInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        petInfoLabel.setForeground(new Color(238, 149, 158)); // Soft pink font color (#EE959E)
//
//        // Action buttons
//        JPanel actionPanel = new JPanel();
//        JButton feedButton = new JButton("Feed " + petName);
//        JButton playButton = new JButton("Play with " + petName);
//
//        // Button customization
//        Color buttonColor = new Color(238, 149, 158); // Soft pink color (#EE959E)
//        feedButton.setBackground(buttonColor);
//        feedButton.setForeground(Color.WHITE);
//        playButton.setBackground(buttonColor);
//        playButton.setForeground(Color.WHITE);
//        feedButton.setFont(new Font("Arial", Font.BOLD, 14));
//        playButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        feedButton.addActionListener(e -> {
//            if (pet.isHungry()) {
//                pet.performAction("feed");
//                animatePet("eating");
//                updatePetInfo();
//            } else {
//                JOptionPane.showMessageDialog(this, pet.getName() + " is not hungry right now!", "Info", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//
//        playButton.addActionListener(e -> {
//            pet.performAction("play");
//            animatePet("playing");
//            updatePetInfo();
//        });
//
//        actionPanel.add(feedButton);
//        actionPanel.add(playButton);
//
//        // Add components to the frame
//        add(petImageLabel, BorderLayout.CENTER);
//        add(petInfoLabel, BorderLayout.NORTH);
//        add(actionPanel, BorderLayout.SOUTH);
//
//        // Add resizing listener for dynamic image scaling
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                petImageLabel.setIcon(scaleImage(petImages.get("neutral")));
//            }
//        });
//
//        setVisible(true);
//    }
//
//    // Method to load images for different pet states
//    private void loadPetImages(String petType) {
//        petImages = new HashMap<>();
//        try {
//            petImages.put("neutral", new ImageIcon("Project/out/images/" + petType + "_neutral.jpg"));
//            petImages.put("eating", new ImageIcon("Project/out/images/" + petType + "_eating.jpg"));
//            petImages.put("playing", new ImageIcon("Project/out/images/" + petType + "_playing.jpg"));
//            petImages.put("happy", new ImageIcon("Project/out/images/" + petType + "_happy.jpg"));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error loading images for " + petType + ": " + e.getMessage(),
//                    "Image Error", JOptionPane.ERROR_MESSAGE);
//            System.exit(1); // Exit the program gracefully
//        }
//    }
//
//    // Method to animate pet actions
//    private void animatePet(String action) {
//        petImageLabel.setIcon(scaleImage(petImages.get(action))); // Scale dynamically based on screen size
//
//        if (animationTimer != null && animationTimer.isRunning()) {
//            animationTimer.stop();
//        }
//
//        animationTimer = new Timer(1000, e -> {
//            petImageLabel.setIcon(scaleImage(petImages.get("happy"))); // Switch to happy image after action
//            animationTimer.stop();
//        });
//
//        animationTimer.setRepeats(false);
//        animationTimer.start();
//    }
//
//    // Method to scale images dynamically based on screen size
//    private ImageIcon scaleImage(ImageIcon imageIcon) {
//        if (imageIcon == null) {
//            System.out.println("Image not found!"); // Debugging info
//            return null;
//        }
//
//        // Get screen dimensions
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int scaledWidth = (int) (screenSize.width * 0.4); // 40% of screen width
//        int scaledHeight = (int) (screenSize.height * 0.4); // 40% of screen height
//
//        // Scale the image
//        Image image = imageIcon.getImage();
//        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//
//    // Method to select pet type
//    private String selectPetType() {
//        String[] petTypes = {"Dog", "Cat", "Bird", "Monkey"};
//        return (String) JOptionPane.showInputDialog(
//                this,
//                "Choose your pet type:",
//                "Pet Selection",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                petTypes,
//                petTypes[0]);
//    }
//
//    // Method to get custom pet name from user
//    private String getPetName() {
//        try {
//            String name = JOptionPane.showInputDialog(this, "Enter a name for your pet:");
//            if (name == null || name.trim().isEmpty()) {
//                throw new IllegalArgumentException("Pet name cannot be empty!");
//            }
//            return name.trim();
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
//            return getPetName(); // Retry until valid input is provided
//        }
//    }
//
//    // Method to create a pet with type and name
//    private Pet<String> createPet(String type, String name) {
//        if (type == null || type.isEmpty()) {
//            throw new IllegalArgumentException("Pet type cannot be null or empty!");
//        }
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Pet name cannot be null or empty!");
//        }
//        return new Pet<>(name, type, 100, 80);
//    }
//
//    // Update the pet info label
//    private void updatePetInfo() {
//        petInfoLabel.setText(pet.displayInfo());
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(PetSimulator::new);
//    }
//}
//
//// Pet class to manage pet attributes and behaviors with generics
//class Pet<T> {
//    private String name;
//    private String type;
//    private int health;
//    private int happiness;
//    private int hunger;
//
//    public Pet(String name, String type, int health, int happiness) {
//        this.name = name;
//        this.type = type;
//        this.health = health;
//        this.happiness = happiness;
//        this.hunger = 0;
//    }
//
//    public void performAction(T action) {
//        if (action instanceof String) {
//            String actionStr = (String) action;
//            switch (actionStr.toLowerCase()) {
//                case "feed":
//                    feed();
//                    break;
//                case "play":
//                    play();
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown action: " + action);
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid action type!");
//        }
//    }
//
//    public void feed() {
//        hunger = Math.max(hunger - 10, 0);
//        happiness += 5;
//        checkHealth();
//    }
//
//    public void play() {
//        happiness += 15;
//        hunger += 5;
//        checkHealth();
//    }
//
//    private void checkHealth() {
//        if (happiness <= 0) {
//            health -= 10;
//        }
//        if (hunger >= 50) {
//            health -= 20;
//        }
//    }
//
//    public String displayInfo() {
//        return "<html><body><center>Name: " + name +
//                "<br>Type: " + type +
//                "<br>Health: " + health +
//                "<br>Happiness: " + happiness +
//                "<br>Hunger: " + hunger + "</center></body></html>";
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public boolean isHungry() {
//        return hunger > 0;
//    }
//}







//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PetSimulator extends JFrame {
//    private Pet<String> pet;
//    private JLabel petImageLabel;
//    private JLabel petInfoLabel;
//    private Map<String, ImageIcon> petImages;
//    private Timer animationTimer;
//
//    public PetSimulator() {
//        setTitle("Visual Pet Simulator");
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        getContentPane().setBackground(new Color(24, 57, 43));
//
//        String petType = selectPetType();
//        if (petType == null) System.exit(0);
//
//        String petName = getPetName();
//        pet = createPet(petType, petName);
//        loadPetImages(petType);
//
//        petImageLabel = new JLabel(scaleImage(petImages.get("neutral")));
//        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        petInfoLabel = new JLabel(pet.displayInfo());
//        petInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        petInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        petInfoLabel.setForeground(new Color(238, 149, 158));
//
//        JPanel actionPanel = new JPanel();
//        JButton feedButton = new JButton("Feed " + petName);
//        JButton playButton = new JButton("Play with " + petName);
//
//        Color buttonColor = new Color(238, 149, 158);
//        feedButton.setBackground(buttonColor);
//        feedButton.setForeground(Color.WHITE);
//        playButton.setBackground(buttonColor);
//        playButton.setForeground(Color.WHITE);
//        feedButton.setFont(new Font("Arial", Font.BOLD, 14));
//        playButton.setFont(new Font("Arial", Font.BOLD, 14));
//
//        feedButton.addActionListener(e -> {
//            if (pet.isHungry()) {
//                pet.performAction("feed");
//                animatePet("eating");
//                updatePetInfo();
//            } else {
//                JOptionPane.showMessageDialog(this, pet.getName() + " is not hungry right now!", "Info", JOptionPane.INFORMATION_MESSAGE);
//            }
//        });
//
//        playButton.addActionListener(e -> {
//            pet.performAction("play");
//            animatePet("playing");
//            updatePetInfo();
//        });
//
//        actionPanel.add(feedButton);
//        actionPanel.add(playButton);
//
//        add(petImageLabel, BorderLayout.CENTER);
//        add(petInfoLabel, BorderLayout.NORTH);
//        add(actionPanel, BorderLayout.SOUTH);
//
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                petImageLabel.setIcon(scaleImage(petImages.get(pet.getEmotion())));
//            }
//        });
//
//        setVisible(true);
//    }
//
//    private void loadPetImages(String petType) {
//        petImages = new HashMap<>();
//        try {
//            petImages.put("neutral", new ImageIcon("Project/out/images/" + petType + "_neutral.jpg"));
//            petImages.put("eating", new ImageIcon("Project/out/images/" + petType + "_eating.jpg"));
//            petImages.put("playing", new ImageIcon("Project/out/images/" + petType + "_playing.jpg"));
//            petImages.put("happy", new ImageIcon("Project/out/images/" + petType + "_happy.jpg"));
//            petImages.put("sad", new ImageIcon("Project/out/images/" + petType + "_sad.jpg"));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error loading images for " + petType + ": " + e.getMessage(),
//                    "Image Error", JOptionPane.ERROR_MESSAGE);
//            System.exit(1);
//        }
//    }
//
//    private void animatePet(String action) {
//        String emotion = pet.getEmotion();
//        petImageLabel.setIcon(scaleImage(petImages.get(action)));
//
//        if (animationTimer != null && animationTimer.isRunning()) {
//            animationTimer.stop();
//        }
//
//        animationTimer = new Timer(1000, e -> {
//            petImageLabel.setIcon(scaleImage(petImages.get(emotion)));
//            animationTimer.stop();
//        });
//
//        animationTimer.setRepeats(false);
//        animationTimer.start();
//    }
//
//    private ImageIcon scaleImage(ImageIcon imageIcon) {
//        if (imageIcon == null) {
//            return new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
//        }
//
//        Image image = imageIcon.getImage();
//        int originalWidth = image.getWidth(null);
//        int originalHeight = image.getHeight(null);
//
//        int targetWidth = getWidth() / 2;
//        int targetHeight = (originalHeight * targetWidth) / originalWidth;
//
//        Image scaledImage = image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaledImage);
//    }
//
//    private String selectPetType() {
//        String[] petTypes = {"Dog", "Cat", "Bird", "Monkey"};
//        return (String) JOptionPane.showInputDialog(
//                this,
//                "Choose your pet type:",
//                "Pet Selection",
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                petTypes,
//                petTypes[0]);
//    }
//
//    private String getPetName() {
//        try {
//            String name = JOptionPane.showInputDialog(this, "Enter a name for your pet:");
//            if (name == null || name.trim().isEmpty()) {
//                throw new IllegalArgumentException("Pet name cannot be empty!");
//            }
//            return name.trim();
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
//            return getPetName();
//        }
//    }
//
//    private Pet<String> createPet(String type, String name) {
//        if (type == null || type.isEmpty()) {
//            throw new IllegalArgumentException("Pet type cannot be null or empty!");
//        }
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Pet name cannot be null or empty!");
//        }
//        return new Pet<>(name, type, 100, 80);
//    }
//
//    private void updatePetInfo() {
//        petInfoLabel.setText(pet.displayInfo());
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(PetSimulator::new);
//    }
//}
//
//class Pet<T> {
//    private String name;
//    private String type;
//    private int health;
//    private int happiness;
//    private int hunger;
//
//    public Pet(String name, String type, int health, int happiness) {
//        this.name = name;
//        this.type = type;
//        this.health = health;
//        this.happiness = happiness;
//        this.hunger = 0;
//    }
//
//    public void performAction(T action) {
//        if (action instanceof String) {
//            String actionStr = (String) action;
//            switch (actionStr.toLowerCase()) {
//                case "feed":
//                    feed();
//                    break;
//                case "play":
//                    play();
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown action: " + action);
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid action type!");
//        }
//    }
//
//    public void feed() {
//        hunger = Math.max(hunger - 10, 0);
//        happiness += 5;
//        checkHealth();
//    }
//
//    public void play() {
//        happiness += 15;
//        hunger += 5;
//        checkHealth();
//    }
//
//    private void checkHealth() {
//        if (happiness <= 0) {
//            health -= 10;
//        }
//        if (hunger >= 50) {
//            health -= 20;
//        }
//    }
//
//    public String displayInfo() {
//        return "<html><body><center>Name: " + name +
//                "<br>Type: " + type +
//                "<br>Health: " + health + "%" +
//                "<br>Happiness: " + happiness + "%" +
//                "<br>Hunger: " + hunger + "%</center></body></html>";
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public int getHealth() { // Added this method
//        return health;
//    }
//
//    public int getHappiness() { // Added this method
//        return happiness;
//    }
//
//    public int getHunger() { // Added this method
//        return hunger;
//    }
//
//    public boolean isHungry() {
//        return hunger > 0;
//    }
//
//    public String getEmotion() {
//        if (health > 70 && happiness > 70) {
//            return "happy";
//        } else if (health > 40 && happiness > 40) {
//            return "neutral";
//        } else {
//            return "sad";
//        }
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PetSimulator extends JFrame {
    private Pet<String> pet;
    private JLabel petImageLabel;
    private JLabel petInfoLabel;
    private Map<String, ImageIcon> petImages;
    private Timer animationTimer;

    public PetSimulator() {
        setTitle("Visual Pet Simulator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(24, 57, 43));

        String petType = selectPetType();
        if (petType == null) System.exit(0);

        String petName = getPetName();
        pet = createPet(petType, petName);
        loadPetImages(petType);

        petImageLabel = new JLabel(scaleImage(petImages.get("neutral")));
        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        petInfoLabel = new JLabel(pet.displayInfo());
        petInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        petInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        petInfoLabel.setForeground(new Color(238, 149, 158));

        JPanel actionPanel = new JPanel();
        JButton feedButton = new JButton("Feed " + petName);
        JButton playButton = new JButton("Play with " + petName);

        Color buttonColor = new Color(238, 149, 158);
        feedButton.setBackground(buttonColor);
        feedButton.setForeground(Color.WHITE);
        playButton.setBackground(buttonColor);
        playButton.setForeground(Color.WHITE);
        feedButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setFont(new Font("Arial", Font.BOLD, 14));

        feedButton.addActionListener(e -> {
            if (pet.isHungry()) {
                pet.performAction("feed");
                animatePet("eating");
                updatePetInfo();
            } else {
                JOptionPane.showMessageDialog(this, pet.getName() + " is not hungry right now!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        playButton.addActionListener(e -> {
            if (pet.isTired()) {
                JOptionPane.showMessageDialog(this, pet.getName() + " is too tired to play!", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                pet.performAction("play");
                animatePet("playing");
                updatePetInfo();
            }
        });

        actionPanel.add(feedButton);
        actionPanel.add(playButton);

        add(petImageLabel, BorderLayout.CENTER);
        add(petInfoLabel, BorderLayout.NORTH);
        add(actionPanel, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                petImageLabel.setIcon(scaleImage(petImages.get(pet.getEmotion())));
            }
        });

        setVisible(true);
    }

    private void loadPetImages(String petType) {
        petImages = new HashMap<>();
        try {
            petImages.put("neutral", new ImageIcon("Project/out/images/" + petType + "_neutral.jpg"));
            petImages.put("eating", new ImageIcon("Project/out/images/" + petType + "_eating.jpg"));
            petImages.put("playing", new ImageIcon("Project/out/images/" + petType + "_playing.jpg"));
            petImages.put("happy", new ImageIcon("Project/out/images/" + petType + "_happy.jpg"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading images for " + petType + ": " + e.getMessage(),
                    "Image Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void animatePet(String action) {
        String emotion = pet.getEmotion();
        petImageLabel.setIcon(scaleImage(petImages.get(action)));

        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new Timer(1000, e -> {
            petImageLabel.setIcon(scaleImage(petImages.get(emotion)));
            animationTimer.stop();
        });

        animationTimer.setRepeats(false);
        animationTimer.start();
    }

    private ImageIcon scaleImage(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        }

        Image image = imageIcon.getImage();
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        int targetWidth = getWidth() / 2;
        int targetHeight = (originalHeight * targetWidth) / originalWidth;

        Image scaledImage = image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private String selectPetType() {
        String[] petTypes = {"Dog", "Cat", "Bird", "Monkey"};
        return (String) JOptionPane.showInputDialog(
                this,
                "Choose your pet type:",
                "Pet Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                petTypes,
                petTypes[0]);
    }

    private String getPetName() {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter a name for your pet:");
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Pet name cannot be empty!");
            }
            return name.trim();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
            return getPetName();
        }
    }

    private Pet<String> createPet(String type, String name) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Pet type cannot be null or empty!");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Pet name cannot be null or empty!");
        }
        return new Pet<>(name, type, 100, 80);
    }

    private void updatePetInfo() {
        petInfoLabel.setText(pet.displayInfo());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetSimulator::new);
    }
}

class Pet<T> {
    private String name;
    private String type;
    private int health;
    private int happiness;
    private int hunger;

    public Pet(String name, String type, int health, int happiness) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.happiness = happiness;
        this.hunger = 0;
    }

    public void performAction(T action) {
        if (action instanceof String) {
            String actionStr = (String) action;
            switch (actionStr.toLowerCase()) {
                case "feed":
                    feed();
                    break;
                case "play":
                    play();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } else {
            throw new IllegalArgumentException("Invalid action type!");
        }
    }

    public void feed() {
        hunger = Math.max(hunger - 10, 0);
        happiness = Math.min(happiness + 5, 100);
        health = Math.min(health + 5, 100); // Health improves slightly on feeding
        checkHealth();
    }

    public void play() {
        happiness = Math.min(happiness + 15, 100);
        hunger = Math.min(hunger + 5, 100);
        health = Math.min(health + 5, 100); // Health improves slightly on playing
        checkHealth();
    }

    private void checkHealth() {
        if (happiness <= 0) {
            health = Math.max(health - 10, 0);
        }
        if (hunger >= 50) {
            health = Math.max(health - 20, 0);
        }
    }

    public boolean isHungry() {
        return hunger > 20;
    }

    public boolean isTired() {
        return hunger > 80;
    }

    public String displayInfo() {
        return "<html><body><center>Name: " + name +
                "<br>Type: " + type +
                "<br>Health: " + health + "%" +
                "<br>Emotions: " + getEmotion() +
                "<br>Hunger: " + hunger + "%</center></body></html>";
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHunger() {
        return hunger;
    }

    public String getEmotion() {
        if (health > 70 && happiness > 70) {
            return "happy";
        } else {
            return "neutral";
        }
    }
}

