package model.dungeon.entity.behavior.attack;

import model.Pair;
import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.Hero;
import model.global.Cmd;
import model.global.Position;

public class AttackSimple implements Attack {


    @Override
    public void attack(Maze maze, Entity entity) {
        int damage = entity.getDmg();

        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();

        if(pos.getCmd() != Cmd.IDLE) {
            Pair val = maze.getPositionByDirection(x, y, pos.getCmd());

            // Fetching targeted entity (In this case (AttackSimple), the entity in front of the attacker)
            Entity victim = maze.getEntity(val.getX(), val.getY());

            if (victim != null) {
                maze.attackEntity(entity, victim, damage);
            }
        }
    }
}
