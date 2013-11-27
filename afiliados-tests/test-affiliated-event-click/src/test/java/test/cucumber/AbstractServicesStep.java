package test.cucumber;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractServicesStep {
	
    protected @Autowired LocalServiceStepsImpl localServiceStepsImpl;
    
}
