package net.forje.workbench.tools;

import junit.framework.Assert;
import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.module.Module;
import net.forje.workbench.module.ModuleCommand;
import net.forje.workbench.module.ModuleContext;
import net.forje.workbench.module.WorkbenchModule;
import org.junit.Test;

public class ModuleValidatorTest {

    @org.junit.Test
    public void testNoCommandAnnotations() throws Exception {

        final Class<NoCommandsModule> moduleClass = NoCommandsModule.class;
        final ModuleValidator validator = new ModuleValidator(moduleClass);

        Assert.assertEquals(false, validator.check());

    }

    // must implement Module interface
    // must have WorkbenchModule annotation
    // command macro may not have spaces
    // command must return void
    // command must takes ModuleContext as first argument
    // empty environment list is invalid
    // command must throws exception

    @WorkbenchModule(name="No Command Module", description = "Module w/o any commands", macro = "nocommand", environmentLevel = {EnvironmentLevel.Dev})
    private static class NoCommandsModule implements Module {

        @Override
        public void start() throws Exception {

        }

        @Override
        public void stop() throws Exception {

        }

    }

    @Test
    public void testMacroWithSpacesInvalid() throws Exception {

        final Class moduleClass = MacroWithSpaces.class;
        final ModuleValidator validator = new ModuleValidator(moduleClass);
        Assert.assertEquals(false, validator.check());

    }

    @WorkbenchModule(name="Macro with spaces", description = "has a macro with a space", macro = "com mand", environmentLevel = {EnvironmentLevel.Dev})
    private static class MacroWithSpaces extends BaseTestModule {

    }

    private static class BaseTestModule implements Module {


        @Override
        public void start() throws Exception {

        }

        @Override
        public void stop() throws Exception {

        }

        @ModuleCommand(name = "name", description = "desc", macro = "name", arguments = {})
        public void dummy(final ModuleContext context) throws Exception {

        }

    }

}
