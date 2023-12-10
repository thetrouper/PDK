# PDK
The Best Plugin Development Kit

---
### Adding to Gradle

#### 1) Adding to Project
- Create a `libs` folder if you don't already have one
- Download the JAR from releases here and drag it into the `libs` folder

#### 2) Register jar
```gradle
jar {
    from {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations.runtimeClasspath.collect {
            it.isDirectory() ? it : zipTree(it);
        }
    }
}
```

#### 3) Add implementation
```gradle
dependencies {
    implementation files('libs/PDK-1.2.jar');
}
```

#### 4) Set encoding to UTF-8
```gradle
compileJava.options.encoding = 'UTF-8'
tasks.withType(JavaCompile).configureEach {
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.encoding = 'UTF-8'
        options.release = targetJavaVersion
    }
}
```

#### 5) Initialize PDK `PDK.init(this)`
```java
public final class CustomPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PDK.init(this);

        // commands
        new TestCommand().register(CustomPlugin.class);

        // listeners
        new TestListener().register(CustomPlugin.class);

        // Items
        new TestCustomItem().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
```

---

### GUI Builders
Create complex GUI's with a few simple calls!
```java
public class TestGUI {

    public static final CustomGui GUI = CustomGui.create()
            .title("&6Super Epik Title")
            .size(27)
            .onClose(e -> e.getPlayer().sendMessage("e"))
            .defineMain(e -> {
                e.setCancelled(true);
            })
            .define(11, new ItemStack(Material.BARRIER), e -> {
                ServerUtils.dispatchf(CustomPlugin.class,"kill %s", e.getWhoClicked().getUniqueId());
            })
            .define(13, new ItemStack(Material.GREEN_WOOL), e -> {
                e.getWhoClicked().sendMessage("EeeeeEEE");
                e.getWhoClicked().closeInventory();
            })
            .define(15, new ItemStack(Material.DIAMOND), e -> {
                e.getWhoClicked().getInventory().addItem(e.getCurrentItem().clone());
                e.getWhoClicked().closeInventory();
            })
            .build();
    
    public static void openFor(Player player) {
        player.openInventory(GUI.getInventory());
    }
}
```

### Item Builders
ItemMetas are too annoying for something as simple as adding lore?
```java
public class TestItem {
    
    public static final ItemStack ITEM = ItemBuilder.create()
            .name("Custom Title")
            .lore("lore 1")
            .lore("lore 2")
            .enchant(Enchantment.MENDING, 5)
            .count(64)
            .build();
}
```

### And More!
- Custom command builder
- Custom command completion tree
- Custom item with event action
- Custom console printing tree
- Block display raytracer (block display entities)
- Custom display raytracer (particles... etc)









