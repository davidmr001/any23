/*
 * Copyright 2008-2010 Digital Enterprise Research Institute (DERI)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.deri.any23.validator.rule;

import org.deri.any23.validator.DOMDocument;
import org.deri.any23.validator.Fix;
import org.deri.any23.validator.Rule;
import org.deri.any23.validator.RuleContext;
import org.w3c.dom.Node;

import java.util.List;

/**
 * Fixes the misuse of the meta name attribute.
 *
 * @see org.deri.any23.validator.rule.MetaNameMisuseRule     
 * @author Michele Mostarda (mostarda@fbk.eu)
 * @author Davide Palmisano (palmisano@fbk.eu)
 */
public class MetaNameMisuseFix implements Fix {

    public String getHRName() {
        return "meta-name-misuse-fix";
    }

    @SuppressWarnings("unchecked")
    public void execute(Rule rule, RuleContext context, DOMDocument document) {
        List<Node> nodes = (List<Node>) context.getData(MetaNameMisuseRule.ERRORED_META_NODES);
        for(Node node : nodes) {
            final String nameValue = node.getAttributes().getNamedItem("name").getTextContent();
            node.getAttributes().removeNamedItem("name");
            Node propertyNode = document.getOriginalDocument().createAttribute("property");
            propertyNode.setNodeValue(nameValue);
            node.getAttributes().setNamedItem(propertyNode);

        }
    }

}
