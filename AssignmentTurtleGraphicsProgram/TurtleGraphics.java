import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import uk.ac.leedsbeckett.oop.LBUGraphics;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics;


public class TurtleGraphics extends LBUGraphics {


    BufferedImage loadImage = null; //to store the saved image and then load it up
    boolean unsaved_changes =false;
    private ArrayList<String> commandHistory = new ArrayList<>();


    public TurtleGraphics() {
        JFrame MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setLayout(new FlowLayout());
        MainFrame.add(this);
        MainFrame.pack();
        MainFrame.setVisible(true);

    }

    public void Turtle_About()
    {
        super.clear();
        super.about(); //calling the about() method from the LBUGraphics package (since it is the parent class)
        unsaved_changes = true;
        displayMessage("Adam's Turtle Graphics");
    }

    public void Turtle_PenUp()
    {
        super.setPenState(false);
        unsaved_changes = true;
        displayMessage("Pen is up !");
    }

    public void Turtle_PenDown()
    {
        super.setPenState(true);
        unsaved_changes = true;
        displayMessage("Pen is down !");
    }

    public void Turtle_TurnLeft(String amount)
    {
        String[] turtle_split = amount.split(" "); //To split the string setting the degrees into index [1]
        if(turtle_split.length > 1) // to see if the user input a degree value
        {try
            {
                int degrees = Integer.parseInt(turtle_split[1]); // Convert index [1] into an integer since it is the degree value inputted

                if (degrees < 0) // check if degrees is negative
                {
                    throw new IllegalArgumentException("Degrees value cannot be negative");
                }
                super.left(degrees);
                unsaved_changes = true;
                displayMessage("Turtle has turned left by " + degrees + "°");
            }catch(NumberFormatException e)
            {
                displayMessage("Invalid integer inputted, input should look like this : left 75");
            }catch(IllegalArgumentException e)
            {
                displayMessage(e.getMessage()); // This will get the message from the exception object created in the if statement above
            }
        }else
        {
            super.left(); // This will automatically rotate 90 degrees if the user does not put their own value
            unsaved_changes = true;
            displayMessage("Turtle has turned left by 90°");
        }
    }

    public void Turtle_TurnRight(String amount)
    {
        String[] turtle_split = amount.split(" ");
        if(turtle_split.length > 1)
        {try
        {
            int degrees = Integer.parseInt(turtle_split[1]); // Convert index [1] into an integer since it is the degree value inputted

            if (degrees < 0)
            {
                throw new IllegalArgumentException("Degrees value cannot be negative");
            }
            super.right(degrees);
            unsaved_changes = true;
            displayMessage("Turtle has turned right by " + degrees + "°");
        }catch(NumberFormatException e)
        {
            displayMessage("Invalid integer inputted, input should look like this : right 75");
        }catch (IllegalArgumentException e)
        {
            displayMessage(e.getMessage());
        }
        }else
        {
            super.right(); // This will automatically rotate 90 degrees if the user does not put their own value
            unsaved_changes = true;
            displayMessage("Turtle has turned right by 90°");
        }
    }

    public void Turtle_MoveForward(String amount)
    {
        String[] turtle_split = amount.split(" ");

        if (turtle_split.length > 1)
        {
            try {
                if (turtle_split[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("No distance value provided after the move command");
                }

                int distance = Integer.parseInt(turtle_split[1]);

                if (distance < 0) {
                    throw new IllegalArgumentException("Distance cannot be negative");
                }

                if ( distance >=1 && distance <=394)
                {
                    super.forward(distance);
                    unsaved_changes = true;
                    displayMessage("Turtle has moved forward " + distance + " pixels");
                }else
                {
                    displayMessage("please enter a sensible value (range 1-394)");
                }

            }
            catch (NumberFormatException e) {
                displayMessage("Invalid Integer inputted, input should look like this: move 75");
            }
            catch (IllegalArgumentException e) {
                displayMessage(e.getMessage());
            }
        }
        else
        {
            displayMessage("distance has to be integer and cannot be empty, input should look like this: move 75");
        }
    }


    public void Turtle_MoveBackwards(String amount)
    {
        String[] turtle_split = amount.split(" ");

        if (turtle_split.length > 1)
        {
            try {
                if (turtle_split[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("No distance value provided after the move command");
                }

                int distance = Integer.parseInt(turtle_split[1]);

                if (distance < 0) {
                    throw new IllegalArgumentException("Distance value written as a positive number");
                }

                if ( distance >=1 && distance <=394)
                {
                    super.forward(-distance);
                    unsaved_changes = true;
                    displayMessage("Turtle has moved backwards " + distance + " pixels");
                }else
                {
                    displayMessage("please enter a sensible value (range 1-394)");
                }

            }
            catch (NumberFormatException e) {
                displayMessage("Invalid Integer inputted, input should look like this: reverse 75");
            }
            catch (IllegalArgumentException e) {
                displayMessage(e.getMessage());
            }
        }
        else
        {
            displayMessage("Command incomplete, input should look like this: reverse 75");
        }
    }

    public void Turtle_PenColour_Black()
    {
        super.setPenColour(Color.black);
        unsaved_changes = true;
        displayMessage("The colour black has been set as the pen colour !");
    }

    public void Turtle_PenColour_Green()
    {
        super.setPenColour(Color.green);
        unsaved_changes = true;
        displayMessage("The colour green has been set as the pen colour !");
    }

    public void Turtle_PenColour_Red()
    {
        super.setPenColour(Color.red);
        unsaved_changes = true;
        displayMessage("The colour red has been set as the pen colour !");
    }

    public void Turtle_PenColour_White()
    {
        super.setPenColour(Color.white);
        unsaved_changes = true;
        displayMessage("The colour white has been set as the pen colour ");
    }

    public void Turtle_ResetPanel()
    {
        super.reset();
        unsaved_changes = true;

        displayMessage("Panel has been reset");
    }

    public void Turtle_Clear()
    {
        super.clear();
        unsaved_changes = true;
        displayMessage("Display has been cleared");
    }

    public void Turtle_saveImage()
    {
        if (!unsaved_changes)
        {
            displayMessage("No changes to save.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to save the current turtle graphics?",
                "Save Image",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION)
        {
            displayMessage("Save cancelled.");
            return;
        }

        try {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            paint(graphics2D);
            graphics2D.dispose();

            File file = new File("TurtleGraphicsImage.jpeg");
            ImageIO.write(image, "jpeg", file);

            displayMessage("Image has been saved as 'TurtleGraphicsImage.jpeg'");
            unsaved_changes = false;
        } catch (IOException e) {
            displayMessage("An error has occurred whilst trying to save your image "+e.getMessage());
        }
    }

    public void Turtle_loadImage()
    {
        try {
            File turtleFile = new File("TurtleGraphicsImage.jpeg");
            BufferedImage loadImage = ImageIO.read(turtleFile);

            if (loadImage == null)
            {
                displayMessage("Error: Image file could not be read or is invalid.");
                return;
            }

            // Draw the image onto the TurtleGraphics panel
            Graphics2D g2d = (Graphics2D) getGraphics();
            g2d.drawImage(loadImage, 0, 0, null);
            g2d.dispose();

            displayMessage("Image has been loaded successfully.");
            unsaved_changes = false;
        } catch (IOException e) {
            displayMessage("Error: Could not load image "+e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (loadImage != null) {
            graphics.drawImage(loadImage, 0, 0, this);
        }

    } // This will allow the image to be displayed onto the panel


    public void Turtle_saveCommands()
    {
        try {
            FileWriter writer = new FileWriter("TurtleCommandHistory.txt");
            for (String command : commandHistory) {
                writer.write(command + "\n");
            }
            writer.close();
            displayMessage("Command history saved to TurtleCommandHistory.txt");
        } catch (IOException e) {
            displayMessage("Error saving command history.");
        }
    }

    public void Turtle_loadCommands()
    {
        try {
            File file = new File("TurtleCommandHistory.txt");
            Scanner scanner = new Scanner(file); // scanner to read the inputs of the user
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                if (command.trim().equalsIgnoreCase("load_command")) {
                    continue;
                }
                processCommand(command);
            }
            scanner.close();
            displayMessage("Command file loaded and executed.");
        } catch (IOException e) {
            displayMessage("Error loading command history "+e.getMessage());
        }
    }

    @Override
    public void about()
    {
        super.reset();
        super.clear();
        Turtle_ResetPanel();
        Turtle_TurnRight("right 90");
        Turtle_MoveForward("move 100");
        Turtle_TurnRight("right 120");
        Turtle_PenDown();
        Turtle_PenColour_Green();
        Turtle_MoveForward("move 100");
        Turtle_TurnRight("right 120");
        Turtle_MoveForward("move 100");
        Turtle_TurnLeft("left 180");
        Turtle_MoveForward("move 50");
        Turtle_TurnLeft("left 60");
        Turtle_PenColour_Red();
        Turtle_MoveForward("move 50");
        Turtle_PenUp();
        Turtle_MoveForward("move 100");
        displayMessage("Adam's Signature");
    }

    public void Turtle_Square(String amount)
    {
        String[] turtle_split = amount.trim().split(" ");

        if (turtle_split.length <= 1 || turtle_split[1].isEmpty()) {
            displayMessage("No distance value provided after the square command. Example: square 75");
            return;
        }

        try {
            int length = Integer.parseInt(turtle_split[1]);

            if (length < 0) {
                throw new IllegalArgumentException("Length value cannot be negative");
            }

            super.clear();
            super.reset();
            super.setPenState(true);
            super.setPenColour(Color.red);
            for (int i = 0; i < 4; i++) { // a for loop to repeat drawing each side at the same angle since it is a square
                super.forward(length);
                super.right();
            }

            super.setPenState(false); // the turtle will dance around the square
            for (int i = 0; i < 4; i++) {
                super.forward(length);
                super.right();
            }

            unsaved_changes = true;
            displayMessage("Square has been drawn.");

        } catch (NumberFormatException e) {
            displayMessage("Invalid integer inputted, input should look like this : square 75");
        } catch (IllegalArgumentException e) {
            displayMessage(e.getMessage());
        }
    }

    public void Turtle_MixColour(String amount) {
        String[] parts = amount.trim().split(" ");

        if (parts.length != 4) {
            displayMessage("Invalid input. Input like : pencolour R G B  | pencolour 255 0 128)");
            return;
        } //double check that the user has put 4 inputs (including the command pencolour)

        try {

            int red  = Integer.parseInt(parts[1]);
            int green = Integer.parseInt(parts[2]); // this is to convert each value into an integer (as i previously did for the degrees and distance values)
            int blue = Integer.parseInt(parts[3]);


            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) { // Range is at 0-255 as it is for the intensity of the 8 bits on screen
                displayMessage("RGB values must be between 0 and 255.");
                return;
            }

            super.setPenColour(new Color(red, green, blue));
            displayMessage("Pen colour set to custom RGB (" + red + ", " + green + ", " + blue + ").");// displays to the user what combination of colours they chose

        } catch (NumberFormatException e) {
            displayMessage("Invalid number format. Please enter integers for R, G, and B "+e.getMessage());
        }
    }

    public void Turtle_Width(String amount)
    {
        String[] turtle_split = amount.split(" ");

        if (turtle_split.length > 1)
        {
            try {
                if (turtle_split[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("No width value provided after the width command");
                }

                int width = Integer.parseInt(turtle_split[1]);

                if (width < 1 || width > 10) {
                    throw new IllegalArgumentException("Width must be between 1 and 10 pixels");
                } // I set a limit to the width that the user can choose so it doesnt get chaotic


                super.setStroke(width); // this will set the width of the pen

                displayMessage("Pen width has been set to " + width + " pixels");

            }
            catch (NumberFormatException e) {
                displayMessage("IInvalid integer inputted, input should look like this : width 75");
            }
            catch (IllegalArgumentException e) {
                displayMessage(e.getMessage());
            }
        }
        else
        {

            displayMessage("Width must be provided as an integer. Example: width 5");
        }
    }

    public void Turtle_Triangle(String amount)
    {
        String[] turtle_split = amount.split(" ");

        if (turtle_split.length > 1)
        {
            try {
                if (turtle_split[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("No size value provided after the triangle command");
                }

                int size = Integer.parseInt(turtle_split[1]);

                if (size <= 0) {
                    throw new IllegalArgumentException("Size must be a positive integer");
                }

                //This is to start drawing the triangle
                super.setPenState(true);
                super.setPenColour(Color.red); // I set it automatically to the colour red

                for (int i = 0; i < 3; i++) {
                    super.forward(size);
                    super.right();
                    super.forward(size);
                    super.right(120);
                } // I iterated drawing each side since they are all equal (just like i did for the square one)

                super.setPenState(false);
                unsaved_changes = true;

                displayMessage("Equilateral triangle has been drawn with side length " + size);

            }
            catch (NumberFormatException e) {
                displayMessage("Invalid integer input, the command should look like this: triangle 75");
            }
            catch (IllegalArgumentException e) {
                displayMessage(e.getMessage());
            }
        }
        else
        {

            displayMessage("Size must be provided as an integer. Example: triangle 75");
        }
    }

    public void Turtle_CustomTriangle(String amount)
    {
        String[] turtle_split = amount.trim().split(" "); //

        if (turtle_split.length == 4) // This will make sure things are written like this triangle 50 80 90 for example
        {
            try {
                int a = Integer.parseInt(turtle_split[1].trim()); // converting each side into an integer
                int b = Integer.parseInt(turtle_split[2].trim());
                int c = Integer.parseInt(turtle_split[3].trim());

                if (a <= 0 || b <= 0 || c <= 0) {
                    throw new IllegalArgumentException("All side lengths must be positive integers");
                } // you can't have a 'zero' distance of a side of a triangle so I added this


                if (a + b <= c || a + c <= b || b + c <= a) {
                    throw new IllegalArgumentException("These sides do not form a valid triangle");
                } // It needs to check if it is possible for the triangle sides to be drawn

                // I used the law of cosines here (rewrote the equation so that CosA in this case angle1,2,3 was the subject of the formula)
                double angleA = Math.acos((b * b + c * c - a * a) / (2.0 * b * c));
                double angleB = Math.acos((a * a + c * c - b * b) / (2.0 * a * c));
                double angleC = Math.PI - angleA - angleB;

                angleA = Math.toDegrees(angleA);
                angleB = Math.toDegrees(angleB);
                angleC = Math.toDegrees(angleC);
                // The angles were in radian which can't be interpreted by the right() method so I had to convert them into degrees after

                super.reset();// restart everything
                Turtle_Width("width 1"); // changes the width back at the end
                super.setPenState(true); // pendown (already been through this previously)
                super.setPenColour(Color.white); // automatically set the colour to white just so that it is more easier for the user


                super.forward(a); // First Side
                super.right((int) (180 - angleC)); // This is for the first side of the custom triangle


                super.forward(b); // Second Side
                super.right((int) (180 - angleA)); // second side


                super.forward(c); // Third side
                // No right turn here since it's the last side to be drawn

                super.setPenState(false); // And then lift the pen once finished
                super.forward(100); // This has no relation with the triangle, it's just to remove the turtle out of the way
                unsaved_changes = true;

                displayMessage("Triangle with  the sides " + a + ", " + b + ", " + c + " has been drawn");

            } catch (NumberFormatException e) {
                displayMessage("Invalid integer input, the command should look like this: triangle 75 50 60");
            } catch (IllegalArgumentException e) {
                displayMessage(e.getMessage());
            }
        }
        else {
            displayMessage("Three sides must be provided in the format: triangle <side1> <side2> <side3>");
        }
    }

    public void Turtle_SetSpeed(String amount)
    {
        String[] split = amount.trim().split(" "); // Split by one or more spaces

        if (split.length != 2) {
            displayMessage("Invalid format. Use: speed 3 (range 1-5)");
            return;
        }

        try {
            int speed = Integer.parseInt(split[1]);

            if (speed < 1 || speed > 5) {
                throw new IllegalArgumentException("Speed must be between 1 (slow) and 5 (fast)");
            }

            super.setTurtleSpeed(speed); // This will set the speed of the turtle with 1 being the fastest and 5 being the slowest
            displayMessage("Turtle speed has been set to " + speed);

        } catch (NumberFormatException e) {
            displayMessage("Speed must be an integer between 1 and 5");
        } catch (IllegalArgumentException e) {
            displayMessage(e.getMessage());
        }
    }


    @Override
    public void processCommand(String command)
    {

        commandHistory.add(command); // So that it saves everything automatically instead of after each switch case statemnt (what I did initially)

        String[] command_part = command.split(" "); // Split the command inputted to filter out index[0]
        String user_command = command_part[0].toLowerCase(); // will take in the first string value as the command as well as make sure that the command is all lowercase to match the case switch statements

        switch(user_command)
        {
            case "about":
                Turtle_About();
                break;
            case "about_me":
                about();
                break;
            case "penup":
                Turtle_PenUp();
                break;
            case "pendown":
                Turtle_PenDown();
                break;
            case "left":
                Turtle_TurnLeft(command); // command is set as the parameter so that the method can filter out the degrees value
                break;
            case "right":
                Turtle_TurnRight(command);
                break;
            case "move":
                Turtle_MoveForward(command);
                break;
            case "reverse":
                Turtle_MoveBackwards(command);
                break;
            case "black":
                Turtle_PenColour_Black();
                break;
            case "green":
                Turtle_PenColour_Green();
                break;
            case "red":
                Turtle_PenColour_Red();
                break;
            case "white":
                Turtle_PenColour_White();
                break;
            case "reset":
                Turtle_ResetPanel();
                break;
            case "clear":
                Turtle_Clear();
                break;
            case "save_image":
                Turtle_saveImage();
                break;
            case "load_image":
                Turtle_loadImage();
                break;
            case "save_command":
                Turtle_saveCommands();
                break;
            case "load_command":
                Turtle_loadCommands();
                break;
            case "square":
                Turtle_Square(command);
                break;
            case "pencolour":
                Turtle_MixColour(command);
                break;
            case "penwidth":
                Turtle_Width(command);
                break;
            case "e_triangle":
                Turtle_Triangle(command);
                break;
            case "triangle":
                Turtle_CustomTriangle(command);
                break;
            case "speed":
                Turtle_SetSpeed(command);
                break;
            default:
                displayMessage("Invalid Command has been inputted, please try again");
                break;
        }
    }
}