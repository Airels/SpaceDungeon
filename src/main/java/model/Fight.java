package model;

import model.entities.characters.Character;

public class Fight {
    private final Character attacker;
    private final Character target;

    public Fight(Character entity1, Character entity2) {
        attacker = entity1;
        target = entity2;
    }

    public void doAttack() {
        double distance = target.getCoords().getDistance(attacker.getCoords());
        if (!(distance < target.getActionRange() || distance < attacker.getActionRange())) return;

        target.doDamages(attacker.getStrength());

        doKnockback();

        System.out.println(attacker + ": " + attacker.getHealthPoints() + " - " + target + ": " + target.getHealthPoints());
    }

    private void doKnockback() {
        double distanceX = target.getCoords().getX() - attacker.getCoords().getX();
        double distanceY = target.getCoords().getY() - attacker.getCoords().getY();

        if (distanceX >= distanceY) {
            if (distanceX > 0) target.doKnockback(Direction.RIGHT);
            else target.doKnockback(Direction.LEFT);
        } else {
            if (distanceY > 0) target.doKnockback(Direction.DOWN);
            else target.doKnockback(Direction.LEFT);
        }
    }
}
