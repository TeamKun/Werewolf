package net.kunmc.lab.werewolf.command.config.set;

import dev.kotx.flylib.command.Command;
import kotlin.Unit;
import net.kunmc.lab.werewolf.config.ConfigManager;
import net.kunmc.lab.werewolf.config.ConfigMeta;

class ConfigItem extends Command {
    public ConfigItem(ConfigMeta config) {
        super(config.path);

        String name = config.path;
        Class clazz = config.type;

        usage(builder -> {
            if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                builder.integerArgument("integer");
            }

            if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                builder.doubleArgument("double");
            }

            if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
                builder.textArgument("boolean", suggestionBuilder -> {
                    suggestionBuilder.suggest("true");
                    suggestionBuilder.suggest("false");
                });
            }

            if (clazz.equals(String.class)) {
                builder.textArgument("String");
            }

            builder.executes(ctx -> {
                Object value = ctx.getTypedArgs().get(0);
                String setValue = value.toString();
                if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
                    Boolean boolValue = Boolean.parseBoolean(value.toString());
                    ConfigManager.setConfig(config.path, boolValue);
                    setValue = boolValue.toString();
                } else {
                    ConfigManager.setConfig(config.path, value);
                }

                ctx.success(name + "の値を" + setValue + "に設定しました.");
                return Unit.INSTANCE;
            });
        });
    }
}