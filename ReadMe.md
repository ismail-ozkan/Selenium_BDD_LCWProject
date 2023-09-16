# LCW Project

This project is a QA automation framework built using Cucumber BDD (Behavior-Driven Development) framework with Selenium
and Java. It provides a structured approach for automating browser tests, ensuring efficient test case management and
easier collaboration between QA engineers and stakeholders. Maven as the build automation tool for managing dependencies
and executing tests. JUnit for assertion and validation of test results.
<br />
There are two main steps for this framework. First one is business layer that involves all teams members including non-technical persons. In this part we write our scenarios under resources folder with Gerkhin Language in plain English. Second part is implementation layer which is technical part including creating project structure and writing source code.

## Steps to Create Project

### 1. Create a maven project called

> InsiderTaskProject

### 2. Under `pom.xml`

> 1. add below property section

```xml

<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

> 2. Add dependencies

```xml

<dependencies>

    <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.10.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.2.3</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.2.3</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>me.jvt.cucumber</groupId>
        <artifactId>reporting-plugin</artifactId>
        <version>7.2.0</version>
    </dependency>

    <!--If you want to get rid of SLF4J Failed to load message from the console -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.32</version>
    </dependency>

</dependencies>
 ```

> 3. Add Build Plugins

```xml

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
            <configuration>
                <parallel>methods</parallel>
                <threadCount>4</threadCount>
                <testFailureIgnore>true</testFailureIgnore>
                <includes>
                    <include>**/CukesRunner*.java</include>
                </includes>
            </configuration>
        </plugin>
    </plugins>
</build>

```

### 3. Create a package called `com.lcw` under `src/test/java`

> 1. under `com.lcw` package, create `pages`, `runners`, `step_definitions` and `utilities `packages

> 2. Add a `configuration.properties` file to store some credentials in order to avoid hard coding if we need and
     determine which type of browser we use. add following information:

```properties
browser=chrome
homepageUrl=https://www.lcwaikiki.com/tr-TR/TR
```

### 4. Create `resources directory` under project level

> 1. Create a file with `.feature` extension to define scenarios and steps. This just a regular file.

> 2. In feature file follow cucumber BDD (Gherkin) approach

### 5. Under `step_definitions` package,

* Create separate java classes for corresponding feature file to write test cases source code.
* Create `Hooks` class for determining common steps like `Driver.getDriver().manage().window().maximize();`
  and `Driver.closeDriver();` for all scenarios by using `@Before` and `@After` annotations which come from cucumber.

#### 6. Under `runners` package, create `CukesRunner` and `FailedTestRunner` java classes to run `step_definitions` java classes test source code

> 1. Add below information above CukesRunner class name

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json"
        },

        features = "src/test/resources/features",
        glue = "com/lcw/step_definitions",
        dryRun = false,
        tags = ""
)
```

> 2. Add below information above FailedTestRunner class name

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/apcrm/step_definitions",
        features = "@target/rerun.txt"
)
```

#### 7. Under `pages` package, create each page of application corresponding `Java Classes` to store each page related web elements to achieve
_Page Object Model_

> 1. Create BasePage java abstract class and add a constructor with `PageFactory.initElements()` method to extend this
     class from other pages classes. We achieve reusability in this way.

```java
 public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
```

### 8. Under Utilities Package

##### Driver Util Class

* Implementation of _Singleton Design Pattern_.
* Switch between different _browser types_ easily
* `private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();` object allow us to
  create a new separate instance of driver for each test in parallel testing.
* I determined which type of driver which I use in `configuration.properties` file as `chrome`. Then Driver util class returns me ChromeDriver with following code:
```java
switch (browserType) {
   case "chrome":
   System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
   driverPool.set(new ChromeDriver());
   break;
```
<br/>

##### ConfigurationReader Util Class

* We use for getting some important test data from properties file. Like:
    - BrowserType
    - Username
    - Password
    - BaseURL

      <br/>
##### Browser Util Class

* There are a lot of useful and helpful methods to use throughout the automation project

### 9. Reporting

This project structure releases 3 different report. We specify under the `plugin` section in `CukesRunner`
class `Cucumber Options` annotation. All report releases under `target` directory after running project.

* `"html:target/cucumber-reports.html"` is basic cucumber report for sharing via email in Jenkins.
* `"me.jvt.cucumber.report.PrettyReports:target/cucumber"` is extended cucumber report to show all steps of scenario in
  detail.
* `"json:target/cucumber.json"` is basic json file to import test result in Jira after execution of test cases.
#### 10. Chrome Driver issue

After 116 version of Chrome, WebDriverManager doesn't support this one. In order to handle this problem, download
newest `chromedriver.exe` and put it in the project directory.
