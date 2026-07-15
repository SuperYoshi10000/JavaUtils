package local.ytk.util.tuple;

import org.apache.commons.lang3.tuple.Pair;

public abstract class Either<A, B> extends Pair<A, B> {
    public static <A, B> Either<A, B> left(A value) {
        return new Left<>(value);
    }
    
    public static <A, B> Either<A, B> right(B value) {
        return new Right<>(value);
    }
    
    @Override
    public A getLeft() {
        return null;
    }
    
    @Override
    public B getRight() {
        return null;
    }
    
    public boolean isLeft() {
        return false;
    }
    
    public boolean isRight() {
        return false;
    }
    
    @Override
    public B setValue(B value) {
        throw new UnsupportedOperationException("Either is immutable");
    }
    
    public static class Left<A, B> extends Either<A, B> {
        private final A value;
        public Left(A value) {
            this.value = value;
        }
        
        @Override
        public A getLeft() {
            return value;
        }
        
        @Override
        public boolean isLeft() {
            return true;
        }
    }
    public static class Right<A, B> extends Either<A, B> {
        private final B value;
        public Right(B value) {
            this.value = value;
        }
        
        @Override
        public B getRight() {
            return value;
        }
        
        @Override
        public boolean isRight() {
            return true;
        }
    }
}
