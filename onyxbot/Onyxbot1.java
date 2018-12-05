/*
 *
 * First attempt for a robot.
 * Used functions are:
 * OnScannedRobot - to detect other robots
 * OnHitWall - to move when hitting a wall
 * OnHitByBullet - to evade additional hits after being hit
 * 
 */

package onyxbots;

import robocode.*;


public class Onyxbot1 extends Robot {
	public void run() {
		//main loop
		while(true){
			/*
			//move 100px
			ahead(100);
			//scan around the robot
		    turnGunRight(360);
		    //tun Left by 30 degrees
		    turnLeft(30);
		    ahead(50);
		    turnRight(30);
		    ahead(50);
		    //go back 75px
		    back(75);
		    turnGunRight(360);
		     */
			
			//trial of a smarter behaviour
			while(true) {
				ahead(100);
				turnGunRight(360);
				back(50);
				turnRight(90);		
			}
		}
    }
	
	public void onScannedRobot(ScannedRobotEvent e){
	    double distance = e.getDistance(); //distance of found robot
	    //check the distance and adjust fire strength accordingly
	    if(distance > 800)
	        fire(5);
	    else if(distance > 600 && distance <= 800)
	        fire(4);
	    else if(distance > 400 && distance <= 600)
	        fire(3);
	    else if(distance > 200 && distance <= 400)
	        fire(2);
	    else if(distance < 200)
	        fire(1);
	}
	
    public void onHitWall(HitWallEvent e){
        double bearing = e.getBearing();
        turnRight(-bearing);
        ahead(100);
    }
    
    
    //When being hit by a bullet, try to evade the next
    public void onHitByBullet(HitByBulletEvent e){
        double energy = getEnergy();
        double bearing = e.getBearing(); //direction from where the bullet came from
        if(energy < 100){ //run away if low energy
            turnRight(-bearing);
            ahead(200);
        }
        else
            turnGunRight(360); // scan
    }
}