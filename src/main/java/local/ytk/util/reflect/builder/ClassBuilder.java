package local.ytk.util.reflect.builder;

import local.ytk.util.reflect.ClassType;
import local.ytk.util.reflect.ExtensionStatus;
import local.ytk.util.reflect.Visibility;
//import org.jetbrains.annotations.ApiStatus;
//import org.jetbrains.annotations.Nullable;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;

//@ApiStatus.Experimental
public sealed class ClassBuilder {
    
    public static non-sealed class ClassClassBuilder extends ClassBuilder {
        protected static final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        protected final String name;
        protected final ClassBuilderLoader loader;
        protected final StringBuilder builder;
        
        protected final ElementModifiers elementModifiers;
        
        public ClassClassBuilder(String name) {
            this(name, ClassLoader.getSystemClassLoader(), new ElementModifiers(ClassType.CLASS, null, Visibility.PUBLIC, ExtensionStatus.DEFAULT));
        }
        public ClassClassBuilder(String name, ClassLoader loader, ElementModifiers modifiers) {
            if (compiler == null) throw new IllegalCallerException("No compiler available. Make sure you are using a JDK, not a JRE.");
            this.name = name;
            this.loader = new ClassBuilderLoader(name, loader);
            this.builder = new StringBuilder(1024);
            this.elementModifiers = modifiers;
        }
        
        public ClassBuilder defineSubclass(ClassBuilder builder) {
            return null;
        }
        
        protected byte[] compile() throws IOException {
            Runtime.getRuntime().exec(new String[]{"javac", ""}, null, new File(""));
//            compiler.getTask();
            return new byte[] {};
        }
        
        public Class<?> build() throws IOException {
            return loader.buildClass();
        }
        
        public class ClassBuilderLoader extends ClassLoader {
            public ClassBuilderLoader(String name, ClassLoader parent) {
                super(name, parent);
            }
            
            public Class<?> buildClass() throws IOException {
                final byte[] compiledBytes = compile();
                return defineClass(name, compiledBytes, 0, compiledBytes.length);
            }
        }
        
    }
}
