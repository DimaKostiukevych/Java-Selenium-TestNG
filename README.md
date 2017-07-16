## Overview
My personal educational repository for creating testing framework using popular tools, in this case I will use:
+ Java
+ Selenium Web Driver
+ TestNG

Project will be build using *Maven* as software project management tool, with surefire plugin, which will help in future with integration in continuous integration tools like Jenkins. The goal for this project is to create complete framework which could be used in real implementations.

## Execution
Tests execute by running Maven Surefire plugin configurated by testng.xml file:

`mvn clean test`

Tests run in parell, each test class run independently, max threads count is 4, different value can be set up by changing testng.xml configuration file.

### Test parameters
Test can be parametrized by parameters.xml file, where we input key as XML Tag and value between tags, i.e: `<browser>Chrome</browser>`. We receive parameters value using _ParametersReader_ class and method _getPropertyByName()_

#### Browsers
Project has been implemented to work with serveral browser set up by <browser> parameter in paramterers.xml. Available browsers:
+ Chrome
+ Firefox
+ HtmlUnit (headless execution)
+ PhantomJS (headless execution)

_Please note that WebDriver for Chrome and Firefox expects that software is already installed on machine. So If You don't have installed Chrome or/and Firefox install it in default directory._
