package org.notion.base;

import org.notion.teamspaces.TaskPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskPageTest extends BaseTest {

    protected TaskPage taskPage;

    @BeforeMethod
    public void initializeCreateTaskPage() {
        taskPage = PageFactory.initElements(driver, TaskPage.class);
    }

    @Test
    public void verifyTaskCreation(){
        pauseBrowser(3);
        taskPage.taskCreation();
    }

}
