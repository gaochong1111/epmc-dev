/****************************************************************************

    ePMC - an extensible probabilistic model checker
    Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 *****************************************************************************/

package epmc.jani.model;

import epmc.prism.exporter.processor.PRISMExporter_ProcessorStrict;
import epmc.prism.exporter.processor.PRISMExporter_ProcessorRegistrar;

public class PRISMExporter_ConstantsProcessor implements PRISMExporter_ProcessorStrict {

    private Constants constants = null;

    @Override
    public PRISMExporter_ProcessorStrict setElement(Object obj) {
        assert obj != null;
        assert obj instanceof Constants; 

        constants = (Constants) obj;
        return this;
    }

    @Override
    public String toPRISM() {
        assert constants != null;

        StringBuilder prism = new StringBuilder();

        for (Constant constant : constants) {
            prism.append(PRISMExporter_ProcessorRegistrar.getProcessor(constant)
                    .toPRISM());
        }

        return prism.toString();
    }

    @Override
    public void validateTransientVariables() {
        assert constants != null;

        for (Constant constant : constants) {
            PRISMExporter_ProcessorRegistrar.getProcessor(constant)
                .validateTransientVariables();
        }
    }

    @Override
    public boolean usesTransientVariables() {
        assert constants != null;

        boolean usesTransient = false;
        for (Constant constant : constants) {
            usesTransient |= PRISMExporter_ProcessorRegistrar.getProcessor(constant)
                    .usesTransientVariables();
        }

        return usesTransient;
    }	
}
