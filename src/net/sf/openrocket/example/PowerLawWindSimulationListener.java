package net.sf.openrocket.example;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

/**
 * The simulation listener that is attached to the simulation.
 * It is instantiated when the simulation run is started and the
 * methods are called at each step of the simulation.
 */
public class PowerLawWindSimulationListener extends AbstractSimulationListener {

    private double multiplier;

    public PowerLawWindSimulationListener() {
        super();
    }
}
