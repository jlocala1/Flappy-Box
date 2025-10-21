package hw1;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the Flappy Box game.
 */
public class Game {
  private FlappyBox flappyBox;
  private List<Pipe> pipes;
  private int score;
  private boolean isGameOver;
  private List<Pipe> passedPipes = new ArrayList<Pipe>(); // helper arrayList for updateScore

  /**
   * Public constructor to initialize the game.
   */
  public Game() {
    setUpCanvas();
    setUpGameObjects();
    score = 0;
    isGameOver = false;
  }

  // Set up the canvas for the game.
  private void setUpCanvas() {
    StdDraw.setCanvasSize(GameConstant.CANVAS_WIDTH, GameConstant.CANVAS_HEIGHT);
    StdDraw.setXscale(0, GameConstant.CANVAS_WIDTH);
    StdDraw.setYscale(0, GameConstant.CANVAS_HEIGHT);
    StdDraw.enableDoubleBuffering();
  }

  // Set up the game objects.
  private void setUpGameObjects() {
    // use logic from demos
    flappyBox = new FlappyBox(100, GameConstant.CANVAS_HEIGHT / 2);
    pipes = new ArrayList<>();
    pipes.add(
            new Pipe(
                    GameConstant.CANVAS_WIDTH,
                    GameConstant.CANVAS_HEIGHT - GameConstant.BOX_SPACE / 2.0
            )
    );
  }

  /**
   * Start the game loop.
   */
  public void runGameLoop() {
    //use logic from demos
    while (!isGameOver) {
      StdDraw.clear(GameConstant.CANVAS_COLOR);
      moveGameObjects();

      // end game if flappy box collides with another box, touches bottom of canvas, or goes above canvas
      if (handleCollisions() || flappyBox.getY() <= GameConstant.BOX_LENGTH
              || flappyBox.getY() >= GameConstant.CANVAS_HEIGHT) {
        isGameOver = true;
      }
      //continue to run game
      updateScore();
      recyclePipes();
      drawGameObjects();
      displayScore();
      StdDraw.show();
      StdDraw.pause(GameConstant.FRAME_DELAY);
    }

    // end game
    displayGameOver();

    StdDraw.pause(3000); //added a short pause before closing so that the message can be seen
    StdDraw.close();
  }

  // Move the flappy box and the pipes.
  private void moveGameObjects() {
    // use logic from demos
    if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
      flappyBox.jump();
    }

    flappyBox.move();
    for (Pipe pipe : pipes) {
      pipe.move();
    }
  }

  // Return true if the flappy box collides with any pipe.
  private boolean handleCollisions() {
    for (Pipe pipe : pipes) {

      // collides is true if the flappy box intersects the pipe
      if (pipe.intersects(flappyBox)) {
        return true;
      }

    }
    return false;
  }

  // Update the score if the flappy box passes a pipe.
  private void updateScore() {
    for (int i = 0; i < pipes.size(); i++) {
      Pipe pipe = pipes.get(i);
      // increment the score if flappyBox X coordinate passes the right side of the pipe
      if (!(passedPipes.contains(pipe)) && pipe.right() < flappyBox.getX()) {
        // add the passed pipe to arrayList to keep track
        passedPipes.add(pipe);
        score += 1;
      }
    }

  }

  // Draw the flappy box and the pipes.
  private void drawGameObjects() {
    flappyBox.draw();
    for (Pipe pipe : pipes) {
      pipe.draw();
    }
  }

  // Remove the pipes that are out of the canvas and add new pipes.
  private void recyclePipes() {
    //use logic from demos
    if (pipes.get(0).right() <= 0) {
      pipes.remove(0);
    }

    if (pipes.get(pipes.size() - 1).right() <= GameConstant.CANVAS_WIDTH / 2.0) {
      double x = pipes.get(pipes.size() - 1).right() + GameConstant.CANVAS_WIDTH / 2.0;
      pipes.add(
              new Pipe(x,
                      GameConstant.CANVAS_HEIGHT - GameConstant.BOX_SPACE / 2.0
              )
      );
    }
  }

  // Display the current score on the screen
  private void displayScore() {
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 32));
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.textLeft(30, GameConstant.CANVAS_HEIGHT - 30, "Score: " + score);
  }

  // Display the game over screen.
  private void displayGameOver() {
    StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 32));
    StdDraw.setPenColor(GameConstant.TEXT_COLOR);
    StdDraw.text(GameConstant.CANVAS_WIDTH / 2.0, GameConstant.CANVAS_HEIGHT / 2.0, "Game Over!");
    StdDraw.show();
  }

  /**
   * Main method.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Game game = new Game();
    game.runGameLoop();
  }
}
