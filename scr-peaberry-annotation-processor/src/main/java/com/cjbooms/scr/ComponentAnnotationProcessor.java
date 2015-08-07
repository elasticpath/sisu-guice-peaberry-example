package com.cjbooms.scr;


import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypesException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.osgi.service.component.annotations.Component;

@SupportedAnnotationTypes("org.osgi.service.component.annotations.Component")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ComponentAnnotationProcessor extends AbstractProcessor {

	public ComponentAnnotationProcessor() {
		super();
	}

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {


		for (Element componentClazz : roundEnv.getElementsAnnotatedWith(Component.class)) {
			if (componentClazz.getKind() != ElementKind.CLASS) {
				continue;
			}
			try {
				PeaberryVariables peaberryVariables = extractSettingsFromScrAnnotations(componentClazz);
				if (peaberryVariables.isInitialialised()) {
					createPeaberryClasses(peaberryVariables);
				}

			} catch (IOException e) {
				processingEnv.getMessager()
						.printMessage(Diagnostic.Kind.ERROR, "Error creating Peaberry classes for: " + componentClazz.getSimpleName());
			}
		}
		return true;
	}

	private void createPeaberryClasses(final PeaberryVariables peaberryVariables) throws IOException {
		Properties props = new Properties();
		URL url = this.getClass().getClassLoader().getResource("velocity.properties");
		assert url != null;
		props.load(url.openStream());

		VelocityEngine ve = new VelocityEngine(props);
		ve.init();

		VelocityContext vc = getVelocityContext(peaberryVariables);

		Template moduleTemplate = ve.getTemplate("PeaberryModule.vm");
		JavaFileObject moduleFileObject = processingEnv.getFiler()
				.createSourceFile(peaberryVariables.serviceInterfaceSimpleName + "PeaberryExportModule");
		writeSourceFile(vc, moduleTemplate, moduleFileObject);

		Template ServiceExportTemplate = ve.getTemplate("PeaberryServiceExport.vm");
		JavaFileObject serviceExportFileObject = processingEnv.getFiler()
				.createSourceFile(peaberryVariables.serviceInterfaceSimpleName + "PeaberryServiceExport");
		writeSourceFile(vc, ServiceExportTemplate, serviceExportFileObject);

	}

	private void writeSourceFile(final VelocityContext context, final Template template, final JavaFileObject javaFileObject) throws IOException {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "creating source file: " + javaFileObject.toUri());
		Writer writer = javaFileObject.openWriter();
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "applying velocity template: " + template.getName());
		template.merge(context, writer);
		writer.close();
	}

	private VelocityContext getVelocityContext(final PeaberryVariables peaberryVariables) {
		VelocityContext vc = new VelocityContext();
		vc.put("packageName", peaberryVariables.packageName);
		vc.put("serviceImplementation", peaberryVariables.serviceImplementation);
		vc.put("serviceImplementationSimpleName", peaberryVariables.serviceImplementationSimpleName);
		vc.put("serviceInterface", peaberryVariables.serviceInterface);
		vc.put("serviceInterfaceSimpleName", peaberryVariables.serviceInterfaceSimpleName);
		return vc;
	}

	private PeaberryVariables extractSettingsFromScrAnnotations(final Element componentClazz) {
		PeaberryVariables peaberryVariables = new PeaberryVariables();
		Component component = componentClazz.getAnnotation(Component.class);

		try {
			Class[] serviceClasses = component.service();
			Class throwsException = serviceClasses[0];
		}
		catch( MirroredTypesException mte ) {
			List<? extends TypeMirror> typeMirrors = mte.getTypeMirrors();
			Element element = ((DeclaredType)typeMirrors.get(0)).asElement();
			peaberryVariables.serviceInterface = element.toString();
			peaberryVariables.serviceInterfaceSimpleName = element.getSimpleName().toString();
		}


		TypeElement classElement = (TypeElement) componentClazz;
		PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "annotated class: " + classElement.getQualifiedName(), componentClazz);

		peaberryVariables.serviceImplementation = classElement.getQualifiedName().toString();
		peaberryVariables.serviceImplementationSimpleName = classElement.getSimpleName().toString();
		peaberryVariables.packageName = packageElement.getQualifiedName().toString();

		return peaberryVariables;
	}

	class PeaberryVariables {
		String packageName = null;
		String serviceImplementation = null;
		String serviceImplementationSimpleName = null;
		String serviceInterface = null;
		String serviceInterfaceSimpleName = null;

		boolean isInitialialised() {
			return packageName != null
					&& serviceImplementation != null
					&& serviceImplementationSimpleName != null
					&& serviceInterface != null
					&& serviceInterfaceSimpleName != null;
		}
	}
}