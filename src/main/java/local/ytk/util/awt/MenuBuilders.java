package local.ytk.util.awt;

import local.ytk.util.ObjectBuilder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class MenuBuilders {
    
    public static MenuBuilder menu() {
        return new MenuBuilder();
    }
    public static MenuBuilder menu(String name) {
        return new MenuBuilder(name);
    }
    public static BarBuilder menuBar() {
        return new BarBuilder();
    }
    
    public static class MenuBuilder extends ObjectBuilder.AbstractListBasedBuilder<Menu, MenuItem> {
        String name;
        public MenuBuilder() {}
        public MenuBuilder(String name) {
            this.name = name;
        }
        @Override
        public Menu build() {
            return collect(name != null ? () -> new Menu(name) : Menu::new, (menu, item) -> {
                if (item == null) menu.addSeparator();
                else menu.add(item);
            });
        }
        
        @Override
        public MenuBuilder add(MenuItem item) {
            super.add(item);
            return this;
        }
        public MenuBuilder addSeparator() {
            return add((MenuItem) null);
        }
        public MenuBuilder add(String name) {
            return add(new MenuItem(name));
        }
        public MenuBuilder add(String name, int key) {
            return add(new MenuItem(name, new MenuShortcut(key)));
        }
        public MenuBuilder add(String name, int key, boolean useShiftModifier) {
            return add(new MenuItem(name, new MenuShortcut(key, useShiftModifier)));
        }
        public MenuBuilder add(String name, ActionListener... actions) {
            MenuItem item = new MenuItem(name);
            for (ActionListener action : actions) {
                item.addActionListener(action);
            }
            return add(item);
        }
        public MenuBuilder add(String name, int key, ActionListener... actions) {
            MenuItem item = new MenuItem(name, new MenuShortcut(key));
            for (ActionListener action : actions) {
                item.addActionListener(action);
            }
            return add(item);
        }
        public MenuBuilder add(String name, int key, boolean useShiftModifier, ActionListener... actions) {
            MenuItem item = new MenuItem(name, new MenuShortcut(key, useShiftModifier));
            for (ActionListener action : actions) {
                item.addActionListener(action);
            }
            return add(item);
        }
    }
    public static class BarBuilder extends ObjectBuilder.AbstractListBasedBuilder<MenuBar, Menu> {
        Menu helpMenu;
        @Override
        public MenuBar build() {
            return collect(MenuBar::new, MenuBar::add);
        }
        public BarBuilder helpMenu(Menu menu) {
            this.helpMenu = menu;
            return this;
        }
    }
}
