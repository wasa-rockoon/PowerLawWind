package net.sf.openrocket.example;

import java.util.ArrayList;
import java.util.List;

import net.sf.openrocket.models.wind.PowerLawWindModel;
import net.sf.openrocket.simulation.SimulationConditions;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.extension.AbstractSimulationExtension;
import net.sf.openrocket.simulation.extension.SimulationExtension;
import net.sf.openrocket.unit.DegreeUnit;
import net.sf.openrocket.unit.UnitGroup;
import net.sf.openrocket.util.BugException;

/**
 * The actual simulation extension.  A new instance is created for
 * each simulation it is attached to.
 *
 * This class contains the configuration and is called before the
 * simulation is run.  It can do changes to the simulation, such
 * as add simulation listeners.
 *
 * All configuration should be stored in the config variable, so that
 * file storage will work automatically.
 */
public class PowerLawWind extends AbstractSimulationExtension {

    @Override
    public String getName() {
        return "Power Law Wind Profile";
    }

    @Override
    public String getDescription() {
        // This description is shown when the user clicks the info-button on the extension
        return "This extension provides different wind speeds according to altitude following the wind profile power law.";
    }


    @Override
    public void initialize(SimulationConditions conditions) throws SimulationException {
        PowerLawWindModel windModel =
            new PowerLawWindModel(
                conditions.getSimulation().getOptions().getWindSpeedAverage(),
                conditions.getSimulation().getOptions().getWindDirection(),
                getRefAlt(), getSurfaceC());
        // windModel.setStandardDeviation(
        //     conditions.getSimulation().getOptions().getWindSpeedDeviation());
        // windModel.setTurbulenceIntensity(
        //     conditions.getSimulation().getOptions()
        //     .getWindTurbulenceIntensity());
        conditions.setWindModel(windModel);
    }

    public double getRefAlt() {
        return config.getDouble("ref_alt", 1.0);
    }
    public double getSurfaceC() {
        return config.getDouble("surface_c", 5.0);
    }

    public void setRefAlt(double refAlt) {
        config.put("ref_alt", refAlt);
        fireChangeEvent();
    }
    public void setSurfaceC(double surfaceC) {
        config.put("surface_c", surfaceC);
        fireChangeEvent();
    }
}
