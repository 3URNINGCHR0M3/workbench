package net.forje.workbench.console.modules;

import junit.framework.Assert;
import net.forje.workbench.EnvironmentLevel;
import net.forje.workbench.console.UserInput;
import net.forje.workbench.module.*;
import org.junit.Test;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ModuleAdapterTest {

    @Test
    public void testGetName() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        final String name = adapter.getName();
        Assert.assertEquals("Module Adapter Test Module", name);

    }

    @Test
    public void testGetDescription() throws Exception {
        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        final String desc = adapter.getDescription();
        Assert.assertEquals("Module used to test the module adapter", desc);

    }

    @Test
    public void testGetMacro() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        final String macro = adapter.getMacro();
        Assert.assertEquals("test", macro);

    }

    @Test
    public void testSupportsEnvironmentLevel() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        Assert.assertEquals(true, adapter.supportsEnvironmentLevel(EnvironmentLevel.All));

    }


    @Test
    public void testAllSupportsAll() throws Exception {
        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        Assert.assertEquals(true, adapter.supportsEnvironmentLevel(EnvironmentLevel.QA));

    }

    @Test
    public void testInvokeWithNoArguments() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final UserInput userInput = new UserInput("test", "noarg", new ArrayList());

        adapter.invoke(new TestModuleContext(), userInput);

        Assert.assertEquals(true, module._noArgsCalled);

    }

    @Test
    public void testInvokeInvalidCommand() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final UserInput userInput = new UserInput("test", "charge", new ArrayList());

        try {
            adapter.invoke(new TestModuleContext(), userInput);
            Assert.fail("Expected an exception");
        } catch (InvalidCommandException e) {
            Assert.assertEquals("command [charge] is not valid for module [test]", e.getMessage());
        }

    }

    @Test
    public void testDefaultCommand() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final UserInput userInput = new UserInput("test", "default", new ArrayList());

        adapter.invoke(new TestModuleContext(), userInput);

        Assert.assertEquals(true, module._noArgsCalled);

    }

    @Test
    public void testInvokeWithArguments() throws Exception {
        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final ArrayList arguments = new ArrayList();
        arguments.add("alpha");
        arguments.add("beta");

        final UserInput userInput = new UserInput("test", "two", arguments);

        adapter.invoke(new TestModuleContext(), userInput);

        Assert.assertEquals(true, module._twoArguments);
        Assert.assertEquals("alpha", module._arg1);
        Assert.assertEquals("beta", module._arg2);

    }

    @Test
    public void testInvokeWithTooManyArguments() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final ArrayList arguments = new ArrayList();
        arguments.add("mu");
        arguments.add("nu");
        arguments.add("tau");

        final UserInput userInput = new UserInput("test", "two", arguments);

        adapter.invoke(new TestModuleContext(), userInput);

        Assert.assertEquals(true, module._twoArguments);
        Assert.assertEquals("mu", module._arg1);
        Assert.assertEquals("nu", module._arg2);

    }

    @Test
    public void testTooFewArguments() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);

        final ArrayList arguments = new ArrayList();
        arguments.add("omega");

        final UserInput userInput = new UserInput("test", "two", arguments);

        try {
            adapter.invoke(new TestModuleContext(), userInput);
            Assert.fail("Expected an exception");
        } catch (InsufficientArgumentsException e) {
            // todo check message
        }

    }

    @Test
    public void testGetCommands() throws Exception {

        TestModule module = new TestModule();

        final ModuleAdapter adapter = new ModuleAdapter(module);
        List<CommandAdapter> commands = adapter.getCommands();

        Assert.assertEquals(2, commands.size());

    }

    // todo test constructor argument null


    @WorkbenchModule(name = "Module Adapter Test Module", description = "Module used to test the module adapter", macro = "test", environmentLevel = {EnvironmentLevel.All})
    protected static class TestModule implements Module {

        boolean _noArgsCalled = false;
        boolean _twoArguments = false;
        String _arg1 = null;
        String _arg2 = null;

        public void start() {
            // this operation is not in the context of the test
            throw new UnsupportedOperationException();
        }

        public void stop() {
            // this operation is not in the context of the test
            throw new UnsupportedOperationException();
        }

        @ModuleCommand(name = "no arg method", description = "method that takes no arguments", macro = "noarg", arguments = {})
        @ModuleDefaultCommand
        public void noArguments(ModuleContext context) {
            _noArgsCalled = true;
        }

        @ModuleCommand(name = "two arg method", description = "method that takes two arguments", macro = "two", arguments = {"one", "two"})
        public void twoArguments(ModuleContext context, String one, String two) {
            _twoArguments = true;
            _arg1 = one;
            _arg2 = two;
        }

    }

    public static class TestModuleContext implements ModuleContext {

        private final UserConsole _userConsole = new TestUserConsole();


        public UserConsole getUserConsole() {
            return _userConsole;
        }

        private static class TestUserConsole implements UserConsole {

            public char[] readPassword() {
                return new char[0];
            }

            public String readLine() {
                return null;
            }

            public Console printf(final String s, final Object... objects) {
                return null;
            }

            public Console format(final String s, final Object... objects) {
                return null;
            }

            public PrintWriter writer() {
                return null;
            }

            public void flush() throws IOException {

            }
        }

    }

}
