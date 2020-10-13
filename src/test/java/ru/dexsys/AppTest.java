package ru.dexsys;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/"
        },
        glue = {
                "java.ru.dexsys.steps"
        }
)
public class AppTest{
}
