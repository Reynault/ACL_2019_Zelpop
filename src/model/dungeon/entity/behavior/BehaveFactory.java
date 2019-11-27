package model.dungeon.entity.behavior;

import model.dungeon.entity.behavior.attack.AttackHero;
import model.dungeon.entity.behavior.attack.AttackSimple;
import model.dungeon.entity.behavior.check.CheckHero;
import model.dungeon.entity.behavior.check.CheckSimple;
import model.dungeon.entity.behavior.move.MoveGobelin;
import model.dungeon.entity.behavior.move.MoveHero;
import model.dungeon.entity.behavior.move.MoveRandom;

public class BehaveFactory {
    public static Behavior getHeroBehavior() {
        return new Behavior(
                new AttackHero(),
                new CheckHero(),
                new MoveHero()
        );
    }

    public static Behavior getSimpleBehavior() {
        return new Behavior(
                new AttackSimple(),
                new CheckSimple(),
                new MoveRandom()
        );
    }

    public static Behavior getGobelinBehavior() {
        return new Behavior(
                new AttackSimple(),
                new CheckSimple(),
                new MoveGobelin()
        );
    }
}
