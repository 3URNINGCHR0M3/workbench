package net.forje.workbench.console;

import net.forje.workbench.module.ModuleContext;
import net.forje.workbench.module.UserConsole;

public class ModuleContextImpl implements ModuleContext {

    private final UserConsole _userConsole;

    public ModuleContextImpl(final UserConsole userConsole) {
        _userConsole = userConsole;
    }

    public UserConsole getUserConsole() {
        return _userConsole;
    }

}
