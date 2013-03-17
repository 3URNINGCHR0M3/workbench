package net.forje.workbench.console.modules;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CommandAdapterTest {

    @Test
    public void testGetCommands() throws Exception {

        final ModuleAdapter adapter = getTestModuleAdapter();

        List<CommandAdapter> commands = adapter.getCommands();

        final Map<String, CommandAdapter> adapterMap = buildMap(commands);

    }

    private static ModuleAdapter getTestModuleAdapter() {
        ModuleAdapterTest.TestModule module = new ModuleAdapterTest.TestModule();
        return new ModuleAdapter(module);
    }

    private static Map<String, CommandAdapter> buildMap(final List<CommandAdapter> commands) {

        Map<String, CommandAdapter> commandMap = new Hashtable<String, CommandAdapter>();

        for (Iterator<CommandAdapter> iterator = commands.iterator(); iterator.hasNext(); ) {
            CommandAdapter commandAdapter = iterator.next();
            final String macro = commandAdapter.getMacro();
            commandMap.put(macro, commandAdapter);
        }

        return commandMap;

    }


    @Test
    public void testImplementsComparable() throws Exception {
        Assert.assertEquals(true, Comparable.class.isAssignableFrom(CommandAdapter.class));
    }

    @Test
    public void testSortsBasedOnName() throws Exception {

        final ModuleAdapter adapter = getTestModuleAdapter();
        List<CommandAdapter> commands = adapter.getCommands();
        final Map<String, CommandAdapter> adapterMap = buildMap(commands);

        final CommandAdapter noargAdapter = adapterMap.get("noarg");
        final CommandAdapter twoAdapter = adapterMap.get("two");

        Assert.assertEquals(noargAdapter.getMacro().compareTo(twoAdapter.getMacro()), noargAdapter.compareTo(twoAdapter));
        Assert.assertEquals(twoAdapter.getMacro().compareTo(noargAdapter.getMacro()), twoAdapter.compareTo(noargAdapter));


    }

}
