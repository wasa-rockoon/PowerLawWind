package net.sf.openrocket.example;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.gui.SpinnerEditor;
import net.sf.openrocket.gui.adaptors.DoubleModel;
import net.sf.openrocket.gui.components.BasicSlider;
import net.sf.openrocket.gui.components.UnitSelector;
import net.sf.openrocket.plugin.Plugin;
import net.sf.openrocket.simulation.extension.AbstractSwingSimulationExtensionConfigurator;
import net.sf.openrocket.unit.UnitGroup;

/**
 * The Swing configuration dialog for the extension.
 *
 * The abstract implementation provides a ready JPanel using MigLayout
 * to which you can build the dialog.
 */
@Plugin
public class PowerLawWindConfigurator
    extends AbstractSwingSimulationExtensionConfigurator<PowerLawWind> {

    public PowerLawWindConfigurator() {
        super(PowerLawWind.class);
    }

    @Override
    protected JComponent getConfigurationComponent(PowerLawWind extension, Simulation simulation, JPanel panel) {

        // Reference altitude

        panel.add(new JLabel("Reference altitude:"));

        DoubleModel refAltModel =
            new DoubleModel(extension, "RefAlt",
                            UnitGroup.UNITS_DISTANCE, 1.0);

        JSpinner refAltSpin = new JSpinner(refAltModel.getSpinnerModel());
        refAltSpin.setEditor(new SpinnerEditor(refAltSpin));
        panel.add(refAltSpin, "w 75lp!");

        UnitSelector refAltUnit = new UnitSelector(refAltModel);
        panel.add(refAltUnit, "w 25");

        BasicSlider refAltSlider =
            new BasicSlider(refAltModel.getSliderModel(0.1, 10.0));
        panel.add(refAltSlider, "w 75lp, wrap");


        // Surface coefficient
        panel.add(new JLabel("Surface coefficient:"));

        DoubleModel surfaceCModel =
            new DoubleModel(extension, "SurfaceC",
                            UnitGroup.UNITS_COEFFICIENT, 1.0);

        JSpinner surfaceCSpin = new JSpinner(surfaceCModel.getSpinnerModel());
        surfaceCSpin.setEditor(new SpinnerEditor(surfaceCSpin));
        panel.add(surfaceCSpin, "w 75lp!");

        BasicSlider surfaceCSlider =
            new BasicSlider(surfaceCModel.getSliderModel(0.1, 10.0));
        panel.add(surfaceCSlider, "w 75lp, wrap");


        return panel;
    }
}
