package local.ytk.util;

import java.util.Objects;

public abstract sealed class Symbol {
    public Symbol() {}
    
    public Symbol dummy() {
        return new Symbol();
    }
    public Symbol unique() {
        return new UniqueSymbol(null);
    }
    public Symbol unique(String name) {
        return new UniqueSymbol(name);
    }
    public Symbol named(String name) {
        return new NamedSymbol(name);
    }
    public Symbol value(T value) {
        return new ValueSymbol(value);
    }
    
    @Override
    public Symbol clone() {
        return new Symbol();
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public String toString() {
        return "Symbol()";
    }
    
    private static final class UniqueSymbol extends Symbol {
        final String name;
        
        public UniqueSymbol(String name) {
            this.name = name;
        }
        
        @Override
        public UniqueSymbol clone() {
            return new UniqueSymbol(name);
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
        
        @Override
        public int hashCode() {
            return System.identityHashCode(name);
        }
        
        @Override
        public String toString() {
            return "Symbol(%s)".formatted(name != null ? name : "");
        }
    }
    private static final class NamedSymbol extends Symbol {
        final String name;
        
        public NamedSymbol(String name) {
            this.name = name;
        }
        
        @Override
        public NamedSymbol clone() {
            return new NamedSymbol(name);
        }
        
        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof NamedSymbol namedSymbol && Objects.equals(name, namedSymbol.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
        
        @Override
        public String toString() {
            return "Symbol(%s)".formatted(name);
        }

        public String name() {
            return name;
        }
    }
    private static final class ValueSymbol<T> extends Symbol {
        final T value;
        
        public ValueSymbol(T value) {
            this.value = value;
        }
        
        @Override
        public ValueSymbol clone() {
            return new ValueSymbol<>(value);
        }
        
        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof ValueSymbol ValueSymbol && Objects.equals(value, ValueSymbol.value);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
        
        @Override
        public String toString() {
            return "Symbol(%s)".formatted(value);
        }

        public String value() {
            return value;
        }
    }
}
