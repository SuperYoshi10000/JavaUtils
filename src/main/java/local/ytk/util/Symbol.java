package local.ytk.util;

import java.util.Objects;

public sealed class Symbol implements Comparable<Symbol> {
    public Symbol() {}
    
    public Symbol dummy() {
        return new Symbol();
    }
    public Symbol unique() {
        return new Symbol();
    }
    public Symbol unique(String name) {
        return new UniqueSymbol(name);
    }
    public Symbol named(String name) {
        return new NamedSymbol(name);
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
    
    @Override
    public int compareTo(Symbol o) {
        return 0;
    }
    
    private static sealed class UniqueSymbol extends Symbol {
        final String name;
        
        public UniqueSymbol(String name) {
            this.name = name;
        }
        
        @Override
        public UniqueSymbol clone() {
            return new UniqueSymbol(name);
        }
        
        @Override
        public String toString() {
            return "Symbol(%s)".formatted(name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
    private static final class NamedSymbol extends UniqueSymbol {
        public NamedSymbol(String name) {
            super(name);
        }
        
        @Override
        public NamedSymbol clone() {
            return new NamedSymbol(name);
        }
        
        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof NamedSymbol namedSymbol && Objects.equals(name, namedSymbol.name);
        }
    }
}
