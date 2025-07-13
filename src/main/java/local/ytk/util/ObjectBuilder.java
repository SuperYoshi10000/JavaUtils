package local.ytk.util;

import local.ytk.util.annotation.Static;
import local.ytk.util.function.ItemSelector;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

@Static
public class ObjectBuilder {
    public static final Object NULL_PLACEHOLDER = placeholder();
    public static final Constructor<Object> OBJECT_CONSTRUCTOR;
    public static final ItemSelector<Constructor<?>> NO_ARGS;
    
    static {
        try {
            OBJECT_CONSTRUCTOR = Object.class.getConstructor();
            NO_ARGS = c -> Arrays.stream(c).min(Comparator.comparingInt(Constructor::getParameterCount)).orElse(OBJECT_CONSTRUCTOR);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T build(Supplier<T> builder){
        return builder.get();
    }
    public static <T> T build(T value, Consumer<T> builder){
        builder.accept(value);
        return value;
    }
    public static <T, U> U build(T value, Function<T, U> builder){
        return builder.apply(value);
    }
    
    public static Object placeholder() {
        return new Symbol();
    }
    
    public interface Builder<T, I> {
        T build();
        
        default Builder<T, I> then(Consumer<T> action) {
            return new ActionBuilder<>(this, action);
        }
        default Builder<T, I> then(Consumer<T>... actions) {
            return new ActionBuilder<>(this, actions);
        }
        default <U> Builder<U, I> map(Function<T, U> mapper) {
            return new MappingBuilder<>(this, mapper);
        }
        
        Builder<T, I> add(I item);
        default Builder<T, I> addAll(Collection<? extends I> items) {
            for (I item : items) {
                add(item);
            }
            return this;
        }
        /** Adds an item using its builder. Automatically calls <code>builder.build()</code>. */
        default <J> Builder<T, I> add(Builder<I, J> itemBuilder) {
            return add(itemBuilder.build());
        }
    }
    static abstract class WrapperBuilder<T, U, I> implements Builder<U, I> {
        protected final Builder<T, I> builder;
        
        WrapperBuilder(Builder<T, I> builder) {
            this.builder = Objects.requireNonNull(builder, "Builder cannot be null");
        }
        
        @Override
        public Builder<U, I> add(I item) {
            builder.add(item);
            return this;
        }
        
        @Override
        public abstract U build();
    }
    static class ActionBuilder<T, I> extends WrapperBuilder<T, T, I> {
        Consumer<T>[] actions;
        @SafeVarargs
        ActionBuilder(Builder<T, I> builder, Consumer<T>... action) {
            super(builder);
            this.actions = Objects.requireNonNull(action, "Actions cannot be null");
        }
        
        @Override
        public T build() {
            T result = builder.build();
            for (Consumer<T> action : actions) {
                action.accept(result);
            }
            return result;
        }
    }
    static class MappingBuilder<T, U, I> extends WrapperBuilder<T, U, I> {
        Function<T, U> mapper;
        
        MappingBuilder(Builder<T, I> builder, Function<T, U> mapper) {
            super(builder);
            this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
        }
        
        @Override
        public U build() {
            T instance = builder.build();
            return mapper.apply(instance);
        }
    }
    static class ItemMappingBuilder<T, I, J> implements Builder<T, J> {
        private final Builder<T, I> builder;
        private final Function<J, I> itemMapper;
        
        ItemMappingBuilder(Builder<T, I> builder, Function<J, I> itemMapper) {
            this.builder = Objects.requireNonNull(builder, "Builder cannot be null");
            this.itemMapper = Objects.requireNonNull(itemMapper, "Item mapper cannot be null");
        }
        
        @Override
        public T build() {
            return builder.build();
        }
        
        @Override
        public Builder<T, J> add(J item) {
            builder.add(itemMapper.apply(item));
            return this;
        }
    }
    
    
    public interface ListBasedBuilder<T, I> extends Builder<T, I> {
        T build();
        List<I> getList();
        
        @Override
        default ListBasedBuilder<T, I> add(I item) {
            getList().add(item);
            return this;
        }
        default ListBasedBuilder<T, I> addAll(Collection<? extends I> items) {
            getList().addAll(items);
            return this;
        }
    }
    public static abstract class AbstractListBasedBuilder<T, I> implements ListBasedBuilder<T, I> {
        protected final List<I> list;
        public AbstractListBasedBuilder(List<I> list) {
            this.list = Objects.requireNonNull(list, "List cannot be null");
        }
        public AbstractListBasedBuilder() {
            this.list = new ArrayList<>();
        }
        @SafeVarargs
        public AbstractListBasedBuilder(I... items) {
            this.list = new ArrayList<>(List.of(items));
        }
        @Override
        public List<I> getList() {
            return list;
        }
        @Override
        public abstract T build();
        protected T collect(Function<List<I>, T> collector) {
            return collector.apply(list);
        }
        protected T collect(Supplier<T> supplier, BiConsumer<T, I> collector) {
            return list.stream().collect(supplier, collector, (a, b) -> {});
        }
        protected T collect(T initial, BiConsumer<T, I> collector) {
            return list.stream().collect(() -> initial, collector, (a, b) -> {});
        }
        // The following methods are for more complex collection operations, and to use the Java Stream API's collect method.
        protected T collect(Supplier<T> supplier, BiConsumer<T, I> collector, BiConsumer<T, T> combiner) {
            return list.stream().collect(supplier, collector, combiner);
        }
        protected T collect(T initial, BiConsumer<T, I> collector, BiConsumer<T, T> combiner) {
            return list.stream().collect(() -> initial, collector, combiner);
        }
        protected T collect(Collector<I, T, T> collector) {
            return list.stream().collect(collector);
        }
    }
}
