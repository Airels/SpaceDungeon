package model.entities.characters.monsters;

import model.Coordinates;
import model.Fight;
import model.Game;
import model.entities.characters.Character;
import model.entities.characters.players.Player;
import model.entities.characters.ai.AI;
import sounds.observers.SoundBossObserver;
import sounds.observers.SoundMonsterObserver;

public class Monster extends Character {
    private final AI monsterAI;
    private final boolean isBoss;

    public Monster(Coordinates coords, String name, double size, int healthPoints, int strength, double speed, AI monsterAI, boolean isBoss) {
        super(
                coords,
                name,
                size,
                healthPoints,
                strength,
                speed
        );

        this.monsterAI = monsterAI;
        this.isBoss = isBoss;

        addObserver(SoundMonsterObserver.getInstance());
        if (isBoss) addObserver(SoundBossObserver.getInstance());
    }

    @Override
    public void action() {
        // No actions
    }

    @Override
    public void attack() {
        Player player = Game.getInstance().getPlayer();

        Fight fight = new Fight(this, player);
        fight.doAttack();
    }

    @Override
    public void deathAction() {
        Game game = Game.getInstance();
        game.deleteEntity(this);

        if (isBoss)
            game.showNotification("YOU WON! Now go pay for a real game!");

        notify(1);
    }

    public AI getMonsterAI() {
        return monsterAI;
    }
}
