package ALsFirstRobot;

import java.awt.Color;

import robocode.*;

public class RobotAnnalena extends Robot {
	public void run() {
		
		setBodyColor(Color.green);
		setBulletColor(Color.PINK);
		
        while (true) {
        	ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }
 
    public void onScannedRobot(ScannedRobotEvent e) {
        if(e.getDistance() < 30 && getEnergy() > 65) {
        	fire(4);
        } else {
        	fire(1);        	
        }
        scan();
    }
    
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
    	// TODO Auto-generated method stub
    	turnLeft(90);
    	ahead(200);  
    	scan();
    }
    
    @Override
    public void onHitWall(HitWallEvent event) {
    	// TODO Auto-generated method stub
    	turnLeft(180);   
    	ahead(100);
    	scan();
    }
    
}
