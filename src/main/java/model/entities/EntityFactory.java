package model.entities;

import javafx.scene.paint.Color;
import model.entities.characters.ai.BossMonsterAI;
import model.entities.characters.ai.StupidMonsterAI;
import model.entities.characters.monsters.Monster;
import model.entities.characters.monsters.MonsterBuilder;
import model.entities.characters.monsters.MonsterType;
import model.entities.characters.players.Player;
import model.entities.characters.players.PlayerBuilder;
import model.entities.characters.players.PlayerType;

public class EntityFactory {
    public Player createPlayer(PlayerType playerType) {
        switch (playerType) {
            case NORMAL:
                return new PlayerBuilder("Normal Player").build();
            case GOD_OF_HYPERDEATH:
                new PlayerBuilder("Asriel Dreemurr - God Of HyperDeath")
                        .setHealthPoints(1_000_000)
                        .setStrength(1_000_000)
                        .setColor(Color.LIGHTGREEN)
                        .build();
            case HARD:
                return new PlayerBuilder("Tarik")
                        .setHealthPoints(200)
                        .setStrength(10)
                        .setSpeed(0.5)
                        .setColor(Color.HOTPINK)
                        .build();
            case IMPOSSIBLE:
                return new PlayerBuilder("Tom")
                        .setHealthPoints(1)
                        .setStrength(0)
                        .setSpeed(2)
                        .build();
        }

        throw new IllegalArgumentException();
    }

    public Monster createMonster(MonsterType monsterType) {
        switch(monsterType) {
            case BLOB:
                return new MonsterBuilder("Blob")
                        .setHealthPoints(50)
                        .setStrength(5)
                        .setSpeed(0.50)
                        .setSize(50)
                        .setColor(Color.CYAN)
                        .setMonsterAI(new StupidMonsterAI())
                        .build();
            case CHIMERE:
                return new MonsterBuilder("Chimere")
                        .setHealthPoints(100)
                        .setStrength(20)
                        .setSpeed(0.75)
                        .setSize(150)
                        .setColor(Color.RED)
                        .build();
            case ALIEN:
                return new MonsterBuilder("Alien")
                        .setHealthPoints(200)
                        .setStrength(50)
                        .setSize(100)
                        .setColor(Color.BLACK)
                        .build();
            case THE_BOSS:
                return new MonsterBuilder("Sett")
                        .setHealthPoints(300)
                        .setStrength(70)
                        .setSize(200)
                        .setColor(Color.PURPLE)
                        .setMonsterAI(new BossMonsterAI())
                        .setBoss(true)
                        .build();
            case SWARM:
                return new MonsterBuilder("Swarm")
                        .setHealthPoints(69)
                        .setStrength(10)
                        .setSpeed(0.75)
                        .setSize(40)
                        .setColor(Color.GREY)
                        .build();
        }

        throw new IllegalArgumentException();
    }
}
