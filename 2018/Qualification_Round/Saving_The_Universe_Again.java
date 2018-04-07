/*
An alien robot is threatening the universe, using a beam that will destroy all algorithms knowledge. We have to stop it!

Fortunately, we understand how the robot works. It starts off with a beam with a strength of 1, and it will run a program that is a series of instructions, which will be executed one at a time, in left to right order. Each instruction is of one of the following two types:

C (for "charge"): Double the beam's strength.
S (for "shoot"): Shoot the beam, doing damage equal to the beam's current strength.
For example, if the robot's program is SCCSSC, the robot will do the following when the program runs:

Shoot the beam, doing 1 damage.
Charge the beam, doubling the beam's strength to 2.
Charge the beam, doubling the beam's strength to 4.
Shoot the beam, doing 4 damage.
Shoot the beam, doing 4 damage.
Charge the beam, increasing the beam's strength to 8.
In that case, the program would do a total of 9 damage.
*/

import java.lang.Math; // headers MUST be above the first class

// one class needs to have a main() method
public class Solution
{
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
    int testNumber = Integer.parseInt(args[0]);
    for (int i = 1; i < testNumber * 2; i = i+2) {
      int turnCount = 0;
      int switchCount = 0;
      int shieldStrength = Integer.parseInt(args[i]);
      String shotCode = args[i + 1];
      boolean shipSurvives = getShieldStrength(shotCode, shieldStrength);
      boolean impossible = false;

      while (!shipSurvives && !impossible) {
        String newShotCode = switchCharacters(shotCode);
        if (newShotCode.equals(shotCode)) {
          impossible = true;
        }
        switchCount++;
        System.out.println("Case #" + (i + 2) / 2 + " current code: " + shotCode);
        shipSurvives = getShieldStrength(shotCode, shieldStrength);
      }

      if (!impossible) {
        System.out.println("Case #" + (i + 2) / 2 + ": " + switchCount);
      } else {
        System.out.println("Case #" + (i + 2) / 2 + ": IMPOSSIBLE");
      }
    }
  }

  public static boolean getShieldStrength(String shotCode, int shieldStrength) {
    boolean survives = true;
    int power = 1;
    for (int j = 0; j < shotCode.length(); j++) {
        char action = shotCode.charAt(j);
        if (action == 'C') {
          power = power * 2;
        } else if (action == 'S') {
          shieldStrength = shieldStrength - power;
          if (shieldStrength < 0) {
            survives = false;
          }
        }
      }
    System.out.println("does ship survive current attack?..." + survives);
    return survives;
  }

  public static String switchCharacters(String shotCode) {
    char[] codeArray = shotCode.toCharArray();
    int j = codeArray.length - 1;
    boolean switched = false;
    while (j > 0 && !switched) {
      if (codeArray[j] == 'S' && codeArray[j - 1] == 'C') {
        char temp = codeArray[j - 1];
        codeArray[j - 1] = codeArray[j];
        codeArray[j] = temp;
        shotCode = new String(codeArray);
        switched = true;
      }
      j--;
    }
    return shotCode;
  }

}
