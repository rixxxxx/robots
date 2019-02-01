package rixsFirstRobot;
import robocode.*;
import robocode.Robot;

import java.awt.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * RixsFirstRobot - a robot by (your name here)
 */
public class RixsFirstRobot extends Robot
{
	/**
	 * run: MyfirstRobot's default behavior
	 */
	public void run() {

		setBodyColor(new Color(102, 0, 102));
		setRadarColor(new Color(48, 0, 48));
		setScanColor(new Color(10, 10, 10));

		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(2);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}

	private void goTo(double x, double y) {
		/* Calculate the difference bettwen the current position and the target position. */
		x = x - getX();
		y = y - getY();

		/* Calculate the angle relative to the current heading. */
		double goAngle = Utils.normalRelativeAngle(Math.atan2(x, y) - getHeadingRadians());

		/*
		 * Apply a tangent to the turn this is a cheap way of achieving back to front turn angle as tangents period is PI.
		 * The output is very close to doing it correctly under most inputs. Applying the arctan will reverse the function
		 * back into a normal value, correcting the value. The arctan is not needed if code size is required, the error from
		 * tangent evening out over multiple turns.
		 */
		setTurnRightRadians(Math.atan(Math.tan(goAngle)));

		/*
		 * The cosine call reduces the amount moved more the more perpendicular it is to the desired angle of travel. The
		 * hypot is a quick way of calculating the distance to move as it calculates the length of the given coordinates
		 * from 0.
		 */
		setAhead(Math.cos(goAngle) * Math.hypot(x, y));
	}
}
