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

package epmc.operator;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import epmc.expression.standard.ExpressionOperator;
import epmc.jani.exporter.operatorprocessor.OperatorProcessor;
import epmc.jani.exporter.processor.JANIExporter_ProcessorRegistrar;
import epmc.jani.model.UtilModelParser;

/**
 * @author Andrea Turrini
 *
 */
public class JANIExporter_OperatorIteProcessor implements OperatorProcessor {
    private static final String OP = "op";
    private static final String ITE = "ite";
    private static final String IF = "if";
    private static final String THEN = "then";
    private static final String ELSE = "else";
    
    private ExpressionOperator expressionOperator = null;
    
    @Override
    public OperatorProcessor setExpressionOperator(ExpressionOperator expressionOperator) {
        assert expressionOperator != null;
        assert expressionOperator.getOperator().equals(OperatorIte.ITE);
    
        this.expressionOperator = expressionOperator;

        return this;
    }

    @Override
    public JsonValue toJSON() {
        assert expressionOperator != null;
        
        JsonObjectBuilder builder = Json.createObjectBuilder();

        builder.add(OP, ITE);
        builder.add(IF, JANIExporter_ProcessorRegistrar.getProcessor(expressionOperator.getOperand1())
                .toJSON());
        builder.add(THEN, JANIExporter_ProcessorRegistrar.getProcessor(expressionOperator.getOperand2())
                .toJSON());
        builder.add(ELSE, JANIExporter_ProcessorRegistrar.getProcessor(expressionOperator.getOperand3())
                .toJSON());
        
        UtilModelParser.addPositional(builder, expressionOperator.getPositional());
        
        return builder.build();
    }
}    
