import org.example.dao.TasksDAO;
import org.example.models.Tasks;
import org.example.service.TasksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TasksServiceTest {
    private static TasksDAO tasksDAO;
    private static TasksService tasksService;

    @BeforeAll
    public static void setUp() {
        tasksDAO = mock(TasksDAO.class);
        tasksService = new TasksService(tasksDAO);
    }

    @Test
    public void addTaskTest() throws SQLException {
        Tasks taskToAdd = new Tasks();
        taskToAdd.setTask_id(1);
        taskToAdd.setTask_name("Sample Task");
        taskToAdd.setTask_description("Sample Description");
        taskToAdd.setStart_date(new Date(System.currentTimeMillis()));
        taskToAdd.setEnd_date(new Date(System.currentTimeMillis()));
        taskToAdd.setProject_id(1);
        taskToAdd.setAssigned_to(1);
        taskToAdd.setTaskStatus(Tasks.TaskStatus.Started);

        when(tasksDAO.addTask(taskToAdd)).thenReturn(true);

        boolean result = tasksService.addTask(taskToAdd);
        Assertions.assertTrue(result);
    }

    @Test
    public void updateTaskTest() throws SQLException {
        Tasks taskToUpdate = new Tasks();
        taskToUpdate.setTask_id(1);
        taskToUpdate.setTask_name("Updated Task");
        taskToUpdate.setTask_description("Updated Description");
        taskToUpdate.setStart_date(new Date(System.currentTimeMillis()));
        taskToUpdate.setEnd_date(new Date(System.currentTimeMillis()));
        taskToUpdate.setProject_id(1);
        taskToUpdate.setAssigned_to(1);
        taskToUpdate.setTaskStatus(Tasks.TaskStatus.In_Progress);

        when(tasksDAO.updateTask(taskToUpdate)).thenReturn(true);

        boolean result = tasksService.updateTask(taskToUpdate);
        Assertions.assertTrue(result);
    }

    @Test
    public void deleteTaskTest() throws SQLException {
        int taskIdToDelete = 1;

        when(tasksDAO.deleteTask(taskIdToDelete)).thenReturn(true);

        boolean result = tasksService.deleteTask(taskIdToDelete);
        Assertions.assertTrue(result);
    }

    @Test
    public void getAllTasksTest() throws SQLException {
        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(new Tasks(1, "Task 1", "Description 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, 1, Tasks.TaskStatus.Started));
        tasksList.add(new Tasks(2, "Task 2", "Description 2", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, 2, Tasks.TaskStatus.In_Progress));

        when(tasksDAO.getAllTasks()).thenReturn(tasksList);

        List<Tasks> result = tasksService.getAllTasks();
        Assertions.assertEquals(tasksList.size(), result.size());
        Assertions.assertEquals(tasksList, result);
    }

    @Test
    public void getTasksByProjectIdTest() throws SQLException {
        int projectId = 1;
        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(new Tasks(1, "Task 1", "Description 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), projectId, 1, Tasks.TaskStatus.Started));

        when(tasksDAO.getTasksUsingProjectId(projectId)).thenReturn(tasksList);

        List<Tasks> result = tasksService.getTasksUsingProjectId(projectId);
        Assertions.assertEquals(tasksList.size(), result.size());
        Assertions.assertEquals(tasksList, result);
    }

    @Test
    public void getTaskByIdTest() throws SQLException {
        int taskId = 1;
        Tasks task = new Tasks(taskId, "Task 1", "Description 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, 1, Tasks.TaskStatus.Started);

        when(tasksDAO.getTaskById(taskId)).thenReturn(task);

        Tasks result = tasksService.getTaskById(taskId);
        Assertions.assertEquals(task, result);
    }

    @Test
    public void getTaskByNameTest() throws SQLException {
        String taskName = "Task 1";
        Tasks task = new Tasks(1, taskName, "Description 1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, 1, Tasks.TaskStatus.Started);

        when(tasksDAO.getTaskByName(taskName)).thenReturn(task);

        Tasks result = tasksService.getTaskByName(taskName);
        Assertions.assertEquals(task, result);
    }
}
