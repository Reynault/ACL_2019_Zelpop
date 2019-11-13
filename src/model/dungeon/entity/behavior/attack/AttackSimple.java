package model.dungeon.entity.behavior.attack;

import model.dungeon.Maze;
import model.dungeon.entity.Entity;
import model.dungeon.entity.Hero;

public class AttackSimple implements Attack {


    @Override
    public void attack(Maze maze, Entity entity) {

        //****
        int damage = entity.getDmg();

        //****
        Entity entity1 =  maze.getEntity(0 , 0);

        //****
        entity1.takeDamage();

        if(!entity1.isAlive()){
            maze.killEntity(entity1, (Hero) entity);
        }else {

        }
    }
}
