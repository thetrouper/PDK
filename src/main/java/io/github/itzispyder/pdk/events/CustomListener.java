package io.github.itzispyder.pdk.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface CustomListener extends Listener {

    default <T extends JavaPlugin> CustomListener register(Class<T> pluginClass) {
        return register(pluginClass, this);
    }

    static <T extends JavaPlugin> CustomListener register(Class<T> pluginClass, CustomListener listener) {
        Bukkit.getPluginManager().registerEvents(listener, JavaPlugin.getProvidingPlugin(pluginClass));
        return listener;
    }
}