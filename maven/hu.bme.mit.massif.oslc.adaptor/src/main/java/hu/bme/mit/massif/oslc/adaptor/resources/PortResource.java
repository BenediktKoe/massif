/*******************************************************************************
 * Copyright (c) 2012, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *     Russell Boykin       - initial API and implementation
 *     Alberto Giammaria    - initial API and implementation
 *     Chris Peters         - initial API and implementation
 *     Gianluca Bernardini  - initial API and implementation
 *	   Sam Padgett	       - initial API and implementation
 *     Michael Fiedler     - adapted for OSLC4J
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 *
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/

package hu.bme.mit.massif.oslc.adaptor.resources;

import hu.bme.mit.massif.oslc.adaptor.SimulinkAdaptorConstants;
import hu.bme.mit.massif.oslc.adaptor.servlet.ServletListener;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

// Start of user code imports
// End of user code

@OslcNamespace(SimulinkAdaptorConstants.SIMULINK_NAMSPACE)
@OslcName(SimulinkAdaptorConstants.PORTRESOURCE)
@OslcResourceShape(title = "PortResource Resource Shape", describes = SimulinkAdaptorConstants.TYPE_PORTRESOURCE)
public class PortResource extends SimulinkElementResource

{

    private Link          container    = new Link();
    private HashSet<Link> connectionss = new HashSet<Link>();
    private Link          portBlock    = new Link();

    public PortResource() throws URISyntaxException {
        super();

        // Start of user code constructor1
        // End of user code
    }

    public PortResource(final URI about) throws URISyntaxException {
        super(about);

        // Start of user code constructor2
        // End of user code
    }

    public static URI constructURI(final String serviceProviderId, final String portRes) {
        String basePath = ServletListener.getServicesBase();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("serviceProviderId", serviceProviderId);

        pathParameters.put("portRes", portRes);
        String instanceURI = "/simu/{serviceProviderId}/portResources/{portRes}";

        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }

    public String toString() {
        String result = "";
        // Start of user code toString_init
        // End of user code

        result = getAbout().toString();

        // Start of user code toString_finalize
        // End of user code

        return result;
    }

    public String toHtml() {
        String result = "";
        // Start of user code toHtml_init
        // End of user code

        result = "<a href=\"" + getAbout() + "\">" + toString() + "</a>";

        // Start of user code toHtml_finalize
        // End of user code

        return result;
    }

    public void addConnections(final Link connections) {
        this.connectionss.add(connections);
    }

    @OslcName("container")
    @OslcPropertyDefinition(SimulinkAdaptorConstants.SIMULINK_NAMSPACE + "container")
    @OslcDescription("")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcRange(SimulinkAdaptorConstants.TYPE_BLOCKRESOURCE)
    @OslcReadOnly(false)
    @OslcTitle("container")
    public Link getContainer() {
        return container;
    }

    @OslcName("connections")
    @OslcPropertyDefinition(SimulinkAdaptorConstants.SIMULINK_NAMSPACE + "connections")
    @OslcDescription("The connected ports.")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcRange(SimulinkAdaptorConstants.TYPE_PORTBLOCKRESOURCE)
    @OslcReadOnly(false)
    @OslcTitle("connections")
    public HashSet<Link> getConnectionss() {
        return connectionss;
    }

    @OslcName("portBlock")
    @OslcPropertyDefinition(SimulinkAdaptorConstants.SIMULINK_NAMSPACE + "portBlock")
    @OslcDescription("")
    @OslcOccurs(Occurs.ZeroOrOne)
    @OslcValueType(ValueType.Resource)
    @OslcRange(SimulinkAdaptorConstants.TYPE_PORTBLOCKRESOURCE)
    @OslcReadOnly(false)
    @OslcTitle("portBlock")
    public Link getPortBlock() {
        return portBlock;
    }

    public void setContainer(final Link container) {
        this.container = container;
    }

    public void setConnectionss(final HashSet<Link> connections) {
        this.connectionss.clear();
        if (connections != null) {
            this.connectionss.addAll(connections);
        }

    }

    public void setPortBlock(final Link portBlock) {
        this.portBlock = portBlock;
    }

    static public String containerToHtmlForCreation(final HttpServletRequest httpServletRequest,
            final String serviceProviderId) {
        String s = "";

        // Start of user code containerasHtmlForCreation_init
        // End of user code

        s = s + "<label for=\"container\">container: </LABEL>";

        // Start of user code containerasHtmlForCreation_mid
        // End of user code

        // Start of user code containerasHtmlForCreation_finalize
        // End of user code

        return s;
    }

    static public String connectionssToHtmlForCreation(final HttpServletRequest httpServletRequest,
            final String serviceProviderId) {
        String s = "";

        // Start of user code connectionssasHtmlForCreation_init
        // End of user code

        s = s + "<label for=\"connections\">connections: </LABEL>";

        // Start of user code connectionssasHtmlForCreation_mid
        // End of user code

        // Start of user code connectionssasHtmlForCreation_finalize
        // End of user code

        return s;
    }

    static public String portBlockToHtmlForCreation(final HttpServletRequest httpServletRequest,
            final String serviceProviderId) {
        String s = "";

        // Start of user code portBlockasHtmlForCreation_init
        // End of user code

        s = s + "<label for=\"portBlock\">portBlock: </LABEL>";

        // Start of user code portBlockasHtmlForCreation_mid
        // End of user code

        // Start of user code portBlockasHtmlForCreation_finalize
        // End of user code

        return s;
    }

    public String containerToHtml() {
        String s = "";

        // Start of user code containertoHtml_init
        // End of user code

        s = s + "<label for=\"container\"><strong>container</strong>: </LABEL>";

        // Start of user code containertoHtml_mid
        // End of user code

        try {
            s = s + (new BlockResource(container.getValue())).toHtml();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start of user code containertoHtml_finalize
        // End of user code

        return s;
    }

    public String connectionssToHtml() {
        String s = "";

        // Start of user code connectionsstoHtml_init
        // End of user code

        s = s + "<label for=\"connections\"><strong>connections</strong>: </LABEL>";

        // Start of user code connectionsstoHtml_mid
        // End of user code

        try {
            s = s + "<ul>";
            Iterator<Link> itr = connectionss.iterator();
            while (itr.hasNext()) {
                s = s + "<li>";
                s = s + (new PortBlockResource(itr.next().getValue())).toHtml();
                s = s + "</li>";
            }
            s = s + "</ul>";
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start of user code connectionsstoHtml_finalize
        // End of user code

        return s;
    }

    public String portBlockToHtml() {
        String s = "";

        // Start of user code portBlocktoHtml_init
        // End of user code

        s = s + "<label for=\"portBlock\"><strong>portBlock</strong>: </LABEL>";

        // Start of user code portBlocktoHtml_mid
        // End of user code

        try {
            s = s + (new PortBlockResource(portBlock.getValue())).toHtml();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start of user code portBlocktoHtml_finalize
        // End of user code

        return s;
    }

}