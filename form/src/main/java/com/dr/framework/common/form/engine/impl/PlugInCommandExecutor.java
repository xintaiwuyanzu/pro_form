package com.dr.framework.common.form.engine.impl;

import com.dr.framework.common.form.engine.*;

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
    private CommandExecutor delegate;

    /**
     * 拦截的插件
     */
    private List<CommandPlugin> plugins;


    private CommandContextFactory commandContextFactory;


    public PlugInCommandExecutor(CommandExecutor delegate, List<CommandPlugin> plugins, CommandContextFactory commandContextFactory) {
        this.delegate = delegate;
        this.commandContextFactory = commandContextFactory;
        this.plugins = Optional.ofNullable(plugins)
                .orElseGet(Collections::emptyList)
                .stream()
                .sorted(Comparator.comparingInt(TypeObject::order))
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

    public CommandExecutor getDelegate() {
        return delegate;
    }

    public List<CommandPlugin> getPlugins() {
        return plugins;
    }
}
