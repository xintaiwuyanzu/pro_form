package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.form.engine.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 拦截命令的执行，使用${@link CommandPlugin} 修改命令的行为
 *
 * @author dr
 */
public class PlugInCommandExecutor implements CommandExecutor {
    /**
     * 真正的执行器
     */
    private final CommandExecutor delegate;

    /**
     * 拦截的插件
     */
    private final List<CommandPlugin> plugins;

    private CommandContextFactory commandContextFactory;


    public PlugInCommandExecutor(CommandExecutor delegate, List<CommandPlugin> plugins) {
        this.delegate = delegate;
        this.plugins = Optional.ofNullable(plugins)
                .orElseGet(Collections::emptyList)
                .stream()
                .sorted(Comparator.comparingInt(TypeComponent::order))
                .collect(Collectors.toList());
    }

    @Override
    public <T> T execute(Command<T> command) {
        CommandContext commandContext = commandContextFactory.createCommandContext();
        //遍历所有的插件，依次修改命令的行为
        for (CommandPlugin plugin : plugins) {
            if (plugin.accept(commandContext, command)) {
                try {
                    command = plugin.handle(commandContext, command);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return delegate.execute(command);
    }

    @Autowired
    public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
        this.commandContextFactory = commandContextFactory;
        if (delegate instanceof StandCommandExecutor) {
            ((StandCommandExecutor) delegate).setCommandContextFactory(commandContextFactory);
        }
    }

    public CommandExecutor getDelegate() {
        return delegate;
    }

    public List<CommandPlugin> getPlugins() {
        return plugins;
    }
}
