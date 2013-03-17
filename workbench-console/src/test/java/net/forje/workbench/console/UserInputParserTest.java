package net.forje.workbench.console;

import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class UserInputParserTest {

    @Test
    public void testNoArguments() throws Exception {

        String input = "alpha:beta";

        final UserInputParser parser = new UserInputParser();
        UserInput ci = parser.parse(input);

        Assert.assertEquals("alpha", ci.getModule());
        Assert.assertEquals("beta", ci.getCommand());

        final List arguments = ci.getArguments();
        Assert.assertEquals(0, arguments.size());

    }

    @Test
    public void testWithParameters() throws Exception {

        String input = "delta:omega nu mu epsilon";

        final UserInputParser parser = new UserInputParser();
        UserInput userInput = parser.parse(input);

        Assert.assertEquals("delta", userInput.getModule());
        Assert.assertEquals("omega", userInput.getCommand());

        final List arguments = userInput.getArguments();
        Assert.assertEquals(3, arguments.size());

        Assert.assertEquals("nu", arguments.get(0));
        Assert.assertEquals("mu", arguments.get(1));
        Assert.assertEquals("epsilon", arguments.get(2));

    }

    @Test
    public void testDefaultCommand() throws Exception {

        String input = "alpha";

        final UserInputParser parser = new UserInputParser();
        UserInput userInput = parser.parse(input);

        Assert.assertEquals("alpha", userInput.getModule());
        Assert.assertEquals("default", userInput.getCommand());

    }


    @Test
    public void testDefaultWithParameters() throws Exception {
        String input = "sigma gamma tau";

        final UserInputParser parser = new UserInputParser();
        UserInput userInput = parser.parse(input);

        Assert.assertEquals("sigma", userInput.getModule());
        Assert.assertEquals("default", userInput.getCommand());

        final List arguments = userInput.getArguments();
        Assert.assertEquals(2, arguments.size());

        Assert.assertEquals("gamma", arguments.get(0));
        Assert.assertEquals("tau", arguments.get(1));

    }

    @Test
    public void testEmptyString() throws Exception {
        final UserInputParser parser = new UserInputParser();
        try {
            UserInput userInput = parser.parse("");
            Assert.fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            // todo text message
        }
    }

    @Test
    public void testNull() throws Exception {
        final UserInputParser parser = new UserInputParser();
        try {
            UserInput userInput = parser.parse(null);
            Assert.fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            // todo text message
        }
    }

    @Test
    public void testAllSpaces() throws Exception {
        final UserInputParser parser = new UserInputParser();
        try {
            UserInput userInput = parser.parse("    ");
            Assert.fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            // todo text message
        }
    }

}
