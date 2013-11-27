package uol.bt.commons.test.helper;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuntimeDroolsException;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsFactory {

    private static KnowledgeBase lastKnowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

    public static KnowledgeBase newKnowledgeBaseFromFile(String...drls) {
	final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

	for (String drl : drls) {
	    builder.add(ResourceFactory.newClassPathResource(drl), ResourceType.DRL);
	}

	return newKnowledgeBase(builder);
    }

    public static KnowledgeBase newKnowledgeBaseFromString(String...drls) {
        final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        for (String drl : drls) {
            builder.add(ResourceFactory.newByteArrayResource(drl.getBytes()), ResourceType.DRL);
        }

        return newKnowledgeBase(builder);
    }

    private static KnowledgeBase newKnowledgeBase(KnowledgeBuilder builder) {
        if (builder.hasErrors()) {
            throw new RuntimeDroolsException(builder.getErrors().toString());
        }

        final KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase();
        base.addKnowledgePackages(builder.getKnowledgePackages());

        synchronized (lastKnowledgeBase) {
            lastKnowledgeBase = base;
        }

        return base;
    }

    public static StatefulKnowledgeSession newStatefulKnowledgeSession() {
	return lastKnowledgeBase.newStatefulKnowledgeSession();
    }
}
