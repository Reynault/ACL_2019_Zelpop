package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.global.Position;

public class AttackProjectile extends Attack {

    @Override
    public void attack(Maze maze, Entity entity) {
        super.attack(maze, entity);
        double damage = entity.getDmg();

        Position pos = entity.getPosition();
        int x = pos.getX();
        int y = pos.getY();

        // Fetching targeted entity (In this case (AttackSimple), the entity in front of the attacker)
        Entity victim = maze.getEntity(x, y);
        if (victim != null) {
            maze.attackEntity(entity, victim, damage);
        } else {
            maze.destroy(x, y, damage);
        }
    }
}
