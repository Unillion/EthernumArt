package net.unillion.ea.energy;

public class PlayerEnergy {
    private int energy;
    private int maxEnergy;
    private int recoveryRate;

    public PlayerEnergy(int initialEnergy, int maxEnergy, int recoveryRate) {
        this.energy = initialEnergy;
        this.maxEnergy = maxEnergy;
        this.recoveryRate = recoveryRate;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = Math.max(1, maxEnergy);
        if (energy > this.maxEnergy) energy = this.maxEnergy;
    }

    public void strengthenRecovery(int amount) {
        this.recoveryRate += amount;
    }

    public void changeEnergy(int amount) {
        this.energy = Math.min(maxEnergy, Math.max(0, energy + amount));
    }

    public void recover() {
        changeEnergy(recoveryRate);
    }
}
