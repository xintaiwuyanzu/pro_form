package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.form.engine.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
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

        Collection<CommandPlugin> filterPlugins = filterPlugins(commandContext, command);
        try {
            //遍历所有的插件，依次修改命令的行为
            for (CommandPlugin plugin : filterPlugins) {
                command = plugin.handle(commandContext, command);
            }
            T result = getDelegate().execute(command);
            //遍历所有的插件，依次修改命令的行为
            for (CommandPlugin plugin : filterPlugins) {
                result = plugin.postExecute(commandContext, command, result);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Collection<CommandPlugin> filterPlugins(CommandContext commandContext, Command<T> command) {
        return getPlugins().stream().filter(p -> p.accept(commandContext, command)).collect(Collectors.toList());
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
