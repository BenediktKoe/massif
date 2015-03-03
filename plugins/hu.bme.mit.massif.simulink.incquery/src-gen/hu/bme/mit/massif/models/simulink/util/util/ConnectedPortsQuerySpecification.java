package hu.bme.mit.massif.models.simulink.util.util;

import com.google.common.collect.Sets;
import hu.bme.mit.massif.models.simulink.util.ConnectedPortsMatch;
import hu.bme.mit.massif.models.simulink.util.ConnectedPortsMatcher;
import hu.bme.mit.massif.models.simulink.util.util.ConnectedInPortsQuerySpecification;
import hu.bme.mit.massif.models.simulink.util.util.PortConnectionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeUnary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ConnectedPortsMatcher in a type-safe way.
 * 
 * @see ConnectedPortsMatcher
 * @see ConnectedPortsMatch
 * 
 */
@SuppressWarnings("all")
public final class ConnectedPortsQuerySpecification extends BaseGeneratedEMFQuerySpecification<ConnectedPortsMatcher> {
  private ConnectedPortsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ConnectedPortsQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ConnectedPortsMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ConnectedPortsMatcher.on(engine);
  }
  
  @Override
  public ConnectedPortsMatch newEmptyMatch() {
    return ConnectedPortsMatch.newEmptyMatch();
  }
  
  @Override
  public ConnectedPortsMatch newMatch(final Object... parameters) {
    return ConnectedPortsMatch.newMatch((hu.bme.mit.massif.simulink.OutPort) parameters[0], (hu.bme.mit.massif.simulink.InPort) parameters[1], (hu.bme.mit.massif.simulink.SingleConnection) parameters[2]);
  }
  
  private static class LazyHolder {
    private final static ConnectedPortsQuerySpecification INSTANCE = make();
    
    public static ConnectedPortsQuerySpecification make() {
      return new ConnectedPortsQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ConnectedPortsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.massif.models.simulink.util.connectedPorts";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("outP","inP","firstPC");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("outP", "hu.bme.mit.massif.simulink.OutPort"),new PParameter("inP", "hu.bme.mit.massif.simulink.InPort"),new PParameter("firstPC", "hu.bme.mit.massif.simulink.SingleConnection"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_outP = body.getOrCreateVariableByName("outP");
      	PVariable var_inP = body.getOrCreateVariableByName("inP");
      	PVariable var_firstPC = body.getOrCreateVariableByName("firstPC");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_outP, "outP"),
      				
      		new ExportedParameter(body, var_inP, "inP"),
      				
      		new ExportedParameter(body, var_firstPC, "firstPC")
      	));
      	new TypeUnary(body, var_outP, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "OutPort"), "http://hu.bme.mit.massif/simulink/1.0/OutPort");
      	new TypeUnary(body, var_inP, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "InPort"), "http://hu.bme.mit.massif/simulink/1.0/InPort");
      	new TypeUnary(body, var_firstPC, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "SingleConnection"), "http://hu.bme.mit.massif/simulink/1.0/SingleConnection");
      	new PositivePatternCall(body, new FlatTuple(var_outP, var_inP, var_firstPC), PortConnectionQuerySpecification.instance().getInternalQueryRepresentation());
      	bodies.add(body);
      }
      {
      	PBody body = new PBody(this);
      	PVariable var_outP = body.getOrCreateVariableByName("outP");
      	PVariable var_inP = body.getOrCreateVariableByName("inP");
      	PVariable var_firstPC = body.getOrCreateVariableByName("firstPC");
      	PVariable var_innerIP = body.getOrCreateVariableByName("innerIP");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_outP, "outP"),
      				
      		new ExportedParameter(body, var_inP, "inP"),
      				
      		new ExportedParameter(body, var_firstPC, "firstPC")
      	));
      	new TypeUnary(body, var_outP, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "OutPort"), "http://hu.bme.mit.massif/simulink/1.0/OutPort");
      	new TypeUnary(body, var_inP, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "InPort"), "http://hu.bme.mit.massif/simulink/1.0/InPort");
      	new TypeUnary(body, var_firstPC, getClassifierLiteral("http://hu.bme.mit.massif/simulink/1.0", "SingleConnection"), "http://hu.bme.mit.massif/simulink/1.0/SingleConnection");
      	new PositivePatternCall(body, new FlatTuple(var_outP, var_innerIP, var_firstPC), PortConnectionQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_innerIP, var_inP), ConnectedInPortsQuerySpecification.instance().getInternalQueryRepresentation());
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}