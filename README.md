# Turtle-Graphics-OOP-Project
Educational Turtle Graphics program in Java, demonstrating object oriented design and basic GUI

---

# 🐢 Turtle Graphics Java Project with LBUGraphics

A Java-based Turtle Graphics simulation that supports both **console-based drawing** on a 2D grid and **graphical rendering** via the **[LBUGraphics library](https://lbu-oop.github.io/OOPturtleGraphicsLibrary/uk/ac/leedsbeckett/oop/LBUGraphics.html)**.

---

## 📖 Overview

This project simulates the classic Turtle Graphics system — allowing a virtual turtle to move across a 2D canvas, drawing lines, changing direction, and toggling its pen state.  

Built in Java as part of an academic assignment, the project integrates:
- A **custom implementation of Turtle movement logic**
- **Console-based grid rendering** using a 2D character array
- **Graphical rendering via the LBUGraphics library**, showcasing object-oriented principles like inheritance and stateful object design.

---

## 🛠️ Technologies Used

- **Java SE 21**
- **[LBUGraphics](https://lbu-oop.github.io/OOPturtleGraphicsLibrary/uk/ac/leedsbeckett/oop/LBUGraphics.html)** library (custom Java GUI library for Leeds Beckett OOP module)

---

## 🎯 Project Features

- Control a turtle on a virtual canvas using simple numbered commands
- Draw lines on a **console-based grid** and a **graphical canvas simultaneously**
- Move forward, turn, and toggle the pen up/down
- Display the canvas state via console output
- Extends `LBUGraphics` to seamlessly integrate GUI rendering with custom logic
- Validates movement boundaries to avoid out-of-bounds errors

---

## 📦 Class Structure

### `TurtleGraphics.java`
- **Extends:** `LBUGraphics`
- Manages turtle’s position, direction, pen state, and floor (2D array)
- Updates both the console grid and LBUGraphics GUI window

### `MainClass.java`
- Handles user input and program control loop
- Presents a console-based menu for interacting with the TurtleGraphics instance

---

## 📜 Command List

| Command | Description                           |
|:-----------|:------------------------------------|
| `1` | Pen Up                                    |
| `2` | Pen Down                                  |
| `3` | Turn Right (90° clockwise)                |
| `4` | Turn Left (90° counterclockwise)          |
| `5` | Move forward `x` steps                    |
| `6` | Display the console grid                  |

---

## 📊 Example Console Session

```text
1 - Pen Up
2 - Pen Down
3 - Turn Right
4 - Turn Left
5 - Move forward 10 steps
6 - Display floor
9 - End program
```
