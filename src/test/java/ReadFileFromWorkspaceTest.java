import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import javaposse.jobdsl.dsl.ScriptRequest;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReadFileFromWorkspaceTest {
    @Test
    public void canReadFileFromWorkspaceWhenCalledDirectly() throws Exception {
        MemoryJobManagement jm = new MemoryJobManagement();
        jm.getAvailableFiles().put("someFile", "echo \"I am masquerading as a script in someFile\"");

        FileReader fileReader = runScript(jm, "src/test/resources/directly.groovy");

        assertEquals(1, jm.getSavedConfigs().size());

        fileReader.close();
    }

    @Test
    public void canReadFileFromWorkspaceViaHelperClass() throws Exception {
        MemoryJobManagement jm = new MemoryJobManagement();
        jm.getAvailableFiles().put("someFile", "echo \"I am masquerading as a script in someFile\"");

        FileReader fileReader = runScript(jm, "src/test/resources/indirectly.groovy");

        assertEquals(1, jm.getSavedConfigs().size());

        fileReader.close();
    }

    @Test
    public void canReadFileFromWorkspaceViaHelperClassWithWorkaround() throws Exception {
        MemoryJobManagement jm = new MemoryJobManagement();
        jm.getAvailableFiles().put("someFile", "echo \"I am masquerading as a script in someFile\"");

        FileReader fileReader = runScript(jm, "src/test/resources/indirectly-with-workaround.groovy");

        assertEquals(1, jm.getSavedConfigs().size());

        fileReader.close();
    }

    private FileReader runScript(MemoryJobManagement jm, String scriptPath) throws IOException {
        FileReader fileReader = new FileReader(scriptPath);
        String jobDslScript = IOUtils.toString(fileReader);
        ScriptRequest scriptRequest = new ScriptRequest(null, jobDslScript, new File(".").toURI().toURL());
        DslScriptLoader.runDslEngine(scriptRequest, jm);
        return fileReader;
    }
}
