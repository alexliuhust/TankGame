package common;

import tank.Bullet;
import tank.Tank;
import terrain.*;

import java.awt.*;

public class Collision {

    public static void detectCollisionsForTank(Tank tank, Tank enemy) {
        detectTankOrTerrainCollisionsForTank(
                tank, enemy.x, enemy.y, enemy.T_WIDTH, enemy.T_HEIGHT, false);
        for (Support support : tank.tf.supports) {
            detectSupportCollisionsForTank(tank, support);
        }
        for (IronWall iron : tank.tf.ironWalls) {
            detectTankOrTerrainCollisionsForTank(
                    tank, iron.x, iron.y, IronWall.IronWall_WIDTH, IronWall.IronWall_HEIGHT, false);
        }
        for (River river : tank.tf.rivers) {
            detectTankOrTerrainCollisionsForTank(
                    tank, river.x, river.y, River.River_WIDTH, River.River_HEIGHT, false);
        }
        for (BrickWall brick : tank.tf.brickWalls) {
            detectTankOrTerrainCollisionsForTank(
                    tank, brick.x, brick.y, BrickWall.BrickWall_WIDTH, BrickWall.BrickWall_HEIGHT, false);
        }

        tank.inGrass = false;
        for (Grass grass : tank.tf.grasses) {
            if (detectTankOrTerrainCollisionsForTank(
                    tank, grass.x, grass.y, Grass.Grass_WIDTH, Grass.Grass_HEIGHT, true)){
                tank.inGrass = true;
                break;
            }
        }
        if (tank.inGrass) {
            tank.SPEED = tank.ORI_SPEED / 2;
        }
        else tank.SPEED = tank.ORI_SPEED;
    }

    private static boolean detectTankOrTerrainCollisionsForTank(Tank t, int ox, int oy, int W, int H, boolean isGrass) {
        if (isGrass) {
            Rectangle rect1 = new Rectangle(t.x, t.y, t.T_WIDTH, t.T_HEIGHT);
            Rectangle rect2 = new Rectangle(ox, oy, W, H);
            return rect1.intersects(rect2);
        }

        else {
            // Left:
            if (t.x <= ox + W && t.x >= ox + W - t.SPEED && t.y > oy - t.T_HEIGHT && t.y < oy + H) {
                t.isBlock[0] = true;
                t.x = ox + W;
            }
            // Right:
            if (t.x >= ox - t.T_WIDTH && t.x <= ox - t.T_WIDTH + t.SPEED && t.y > oy - t.T_HEIGHT && t.y < oy + H) {
                t.isBlock[1] = true;
                t.x = ox - t.T_WIDTH;
            }
            // Up:
            if (t.y <= oy + H && t.y >= oy + H - t.SPEED && t.x > ox - t.T_WIDTH && t.x < ox + W) {
                t.isBlock[2] = true;
                t.y = oy + H;
            }
            // Down:
            if (t.y >= oy - t.T_HEIGHT && t.y <= oy - t.T_HEIGHT + t.SPEED && t.x > ox - t.T_WIDTH && t.x < ox + W) {
                t.isBlock[3] = true;
                t.y = oy - t.T_HEIGHT;
            }
        }
        return false;
    }

    private static void detectSupportCollisionsForTank(Tank t, Support support) {
        Rectangle r1 = new Rectangle(t.x, t.y, t.T_WIDTH, t.T_HEIGHT);
        Rectangle r2 = new Rectangle(support.x, support.y, Support.Support_WIDTH, Support.Support_HEIGHT);
        if (r1.intersects(r2)) {
            support.getPickedUp(t);
        }
    }

    public static void detectCollisionsForBullet(Bullet b) {
        if (collideWithTanks(b, b.tf.tank1) || collideWithTanks(b, b.tf.tank2)) {
            b.bringExplosionImage();
            return;
        }

        for (IronWall ironWall : b.tf.ironWalls) {
            if (collideWithIronWalls(b, ironWall)) {
                b.bringExplosionImage();
                return;
            }
        }

        for (BrickWall brick : b.tf.brickWalls) {
            if (collideWithBrickWalls(b, brick)) {
                b.bringExplosionImage();
                return;
            }
        }
    }

    public static boolean collideWithTanks(Bullet b, Tank tank) {
        boolean ans = false;
        Rectangle rect1 = new Rectangle(b.x, b.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle rect2 = new Rectangle(tank.x, tank.y, tank.T_WIDTH, tank.T_HEIGHT);

        if (rect1.intersects(rect2)) {
            if (b.fromTank.player != tank.player) {
                b.live = false;
                tank.getHit(b);
                ans = true;
            }
        }
        return ans;
    }

    private static boolean collideWithIronWalls(Bullet b, IronWall ironWall) {
        Rectangle rect1 = new Rectangle(b.x, b.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle rect2 = new Rectangle(ironWall.x, ironWall.y, IronWall.IronWall_WIDTH, IronWall.IronWall_HEIGHT);
        if (rect1.intersects(rect2)) {
            b.live = false;
            return true;
        }
        return false;
    }

    private static boolean collideWithBrickWalls(Bullet b, BrickWall brickWall) {
        Rectangle rect1 = new Rectangle(b.x, b.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle rect2 = new Rectangle(brickWall.x, brickWall.y, BrickWall.BrickWall_WIDTH, BrickWall.BrickWall_HEIGHT);
        if (rect1.intersects(rect2)) {
            b.live = false;
            brickWall.getHit(b);
            return true;
        }
        return false;
    }

}
