package uol.affiliated.commons.test;

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.affiliated.commons.test.exception.StepException;

import com.google.common.base.Preconditions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;


/**
 * @author cad_rpereira
 * 
 */

public class CucumberTestUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CucumberTestUtil.class);
	
	public static void given(TestStep step)
	{
		doStep(step, Dado.class);
	}
	
	public static void when(TestStep step) {
		doStep(step, Quando.class);
	}
	
	public static void then(TestStep step) {
		doStep(step, Entao.class);
	}
	
	private static void doStep(TestStep step, Class<? extends Annotation> clazz) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		String methodName = stack[3].getMethodName();
		Method method;
		try {
			method = ReflectionUtil.getMethod(Class.forName(stack[3].getClassName()), methodName);
		} catch (ClassNotFoundException e) {
			method = null;
		}
		Preconditions.checkNotNull(method, String.format("O metodo [%s] nao foi encontrado para a classe [%s]", methodName, stack[3].getClassName()));
		Annotation annotation = method.getAnnotation(clazz);
		Preconditions.checkNotNull(annotation, String.format("O metodo [%s] nao possui a anotacao [%s]", methodName, clazz));
		String stepName = clazz.getSimpleName(),
			   stepDesc;
		try {
			stepDesc = clazz.getMethod("value").invoke(annotation).toString();
			stepDesc = stepDesc.replace("^", "").replace("$", "").replaceAll("\\<\\(\\.\\+\\)\\>", "<parametro>");
		} catch (Exception e) {
			stepDesc = stepName;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter printer = new PrintWriter(baos);
		printer.println(String.format("********************* %s STEP *************************", stepName));
		printer.println(String.format("> %s %s [%s]", stepName, stepDesc, methodName));
		try {
			step.execute(printer);
		} catch(StepException e) {
			System.out.println(String.format("***** FALHA NA EXECUCAO DO STEP: [%s] *****", e.getExceptionMessage()));
		}
		printer.println(String.format("< %s %s [%s]", stepName, stepDesc, methodName));
		printer.flush();
		printer.close();
		String out = new String(baos.toByteArray(), Charset.forName("UTF-8"));
		LOGGER.info(out);
	}
}