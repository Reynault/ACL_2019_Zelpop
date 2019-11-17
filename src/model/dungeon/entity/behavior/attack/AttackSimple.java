package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.Hero;
import model.global.Position;

public class AttackSimple implements Attack {


    @Override
    public void attack(Maze maze, Entity entity) {
        int damage = entity.getDmg();

        // Fetching targeted entity (In this case (AttackSimple), the entity in front of the attacker)
        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();

        switch (pos.getCmd()){
            case UP:
                y --;
                break;
            case LEFT:
                x --;
                break;
            case RIGHT:
                x ++;
                break;
            case DOWN:
                y++;
                break;
        }

        Entity victim =  maze.getEntity(x , y);

        if(victim != null) {
            victim.takeDamage(entity.getDmg());
            if (!victim.isAlive()) {
                maze.killEntity(victim, (Hero) entity);
            }
        }
    }
}
