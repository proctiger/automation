package uol.affiliated.commons.test;

import java.io.PrintWriter;

import uol.affiliated.commons.test.exception.StepException;

public interface TestStep {
	public void execute(PrintWriter printer) throws StepException;
}
