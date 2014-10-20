package com.zelix.zelix_maven_plugin;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import com.zelix.ZKM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * 
 * @phase process-sources
 */
@Mojo( name = "obfuscate")
public class ObfuscationMojo extends AbstractMojo
{
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    public void execute() throws MojoExecutionException
    {
    	OpenOption x = StandardOpenOption.CREATE;
        File f = new File("test.txt");
        try {
			Files.write(Paths.get("test.txt"), "this is a mojo test".getBytes(), x);
		} catch (IOException e) {
			//..
		}
        
        Obfuscate();
    }
    
    public void Obfuscate() {
    	//TODO
    	RunZelix();
    }
    
    public void CreateCustomZelixScript(String file, String pathToZelixMasterScript, String pathToZelixCustomizationScript) {
		
		Path path = FileSystems.getDefault().getPath(pathToZelixMasterScript);
		
		String customizedZelixScript = "";
		
		try {
			customizedZelixScript = new String(Files.readAllBytes(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try {
			// this is fucking bullshit!!!
		} catch (ArrayIndexOutOfBoundsException a) {
			//
		}
		customizedZelixScript = customizedZelixScript.replace("<filepath>", file);
		/*customizedZelixScript = customizedZelixScript.replace("<obfuscatedFilepath>", pathToObfuscatedJars);
		String zelixClassPath = "";
		
		for (int i = 0; i < requiredJars.Count; i++)
		{
			zelixClassPath += "\"" + requiredJars[i] + "\"\r\n";
		}
		
		for (int i = 0; i < classPathFiles.Count; i++)
		{
			zelixClassPath += "\"" + classPathFiles[i] + "\"";
			if (i != classPathFiles.Count - 1)
				zelixClassPath += "\r\n";
		}
		
		OpenOption x;
		
		customizedZelixScript = customizedZelixScript.replace("<classpath>", "classpath " + zelixClassPath + ";");
		Files.write(path, customizedZelixScript.getBytes());*/
	}
    
    public void RunZelix() {
    	//not implemented
    	try {
			ZKM.run("zelixscript_test.txt", "log.txt", true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
