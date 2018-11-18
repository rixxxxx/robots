package rsa;

import robocode.Robot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.math.*;

import java.awt.*;
import java.util.Random;


public class Intelllijrobot extends Robot {
    int dist = 50; // distance to move when we're hit

    /**
     * run:  Fire's main run function
     */
    public void run() {
        // Set colors
        setBodyColor(Color.orange);
        setGunColor(Color.blue);
        setRadarColor(Color.red);
        setScanColor(Color.red);
        setBulletColor(Color.red);

        // Spin the gun around slowly... forever
        while (true) {
            ahead((int)Math.floor((Math.random() * 999) + 1));
            turnGunRight(120);
            back((int)Math.floor((Math.random() * 999) + 1));
            turnGunRight(120);
        }
    }

    /**
     * onScannedRobot:  Fire!
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // If the other robot is close by, and we have plenty of life,
        // fire hard!
        if (e.getDistance() < 20 && getEnergy() > 60)
        {
            fire(8);
        }
        else if (e.getDistance() < 50 && getEnergy() > 50) {
            fire(3);
        } // otherwise, fire 1.
        else {
            fire(1);
        }
        // Call scan again, before we turn the gun
        scan();
    }

    /**
     * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
     */
    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

        ahead(dist);
        dist *= -1.0;
        scan();
    }

    /**
     * onHitRobot:  Aim at it.  Fire Hard!
     */
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

        turnGunRight(turnGunAmt);
        fire(3);
    }
}