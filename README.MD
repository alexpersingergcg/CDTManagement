# Starter Template for Appian Smart Service Plugin

This template serves as a start up java project for smart service plugin development.

This template application will compile, build, and generate a working jar file that can be uploaded to the cloud 
server and run a smart service called `Create CDT` that will create a CDT named: _AP_H_AlexTesting_ with a single 
field: _firstName_. 

It is meant to serve as a building block to get started on a smart service plugin without the hassle of
initial zero day setup.

## SDK Version
At the time of writing this, we are using: **Java SDK 1.8**

## Building your JAR/Artifact

### JetBrains IntelliJ
In your artifact configuration you need to include the following:
 - Compiled project code
 - Manifest folder
 - appian-plugin.xml. 
 
To configure this:
1. Go to `File -> Project Structure -> Artifacts`. 
2. Click the '+' and select JAR -> Empty
3. Leave the output directory as is
4. Name the jar something along the lines: `com.<gcg or groundswellcg>.<component_name>`
5. Click on the '+' with a down arrow and select:
   - `Module Output` -> <your module>
   - `File` -> appian-plugin.xml
   - `Directory Content` -> META-INF
   
To build your jar file / artifact in intellij, follow these steps:
1. `Build -> Build Project`
2. `Build -> Build Artifacts`
   - If you have already build your artifact, you'll need to select `rebuild`
3. Your jar will be in the out/ directory configured during artifact configuration

## Remove build errors due to external libraries
This is for build errors caused by Appian classes that do not need to be included in the project build 
### JetBrains Intellij
1. Create a local folder (anywhere) to store various jars required for plugin development
   - I created a folder called "External Libraries" in my primary software folder
2. Go to `File -> Project Structure -> Modules`
3. Ensure the tab is on "Dependencies"
4. Click the '+' in the right hand corner of the grid
5. Navigate to your external lib folder and select one or more jars

## Modifying these contents
**DO NOT COMMIT ANY CHANGES TO THIS PROJECT**
 - The properties files are very sensitive to naming
    - Keep your classes, categories, and appian surfacing functions named _extremely_ similar
 - AFAIK - you need a internationalization *_en_US.properties file for each category and appian surfaced function
 - If you have a category, make sure it has the literal word `Category` at the very end 
    - Example: you have a category named: "gcgTest", ensure that you append `Category` to the end otherwise an i18n error will occur when deploying the plugin
 - Careful with versioning and naming, be unique or else you might overwrite someone else's plugin!