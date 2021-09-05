package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;




public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDevesavalSemDescricao() {
        Task task = new Task();
        task.setDueDate(LocalDate.now());
        try {
            controller.save(task);
            Assert.fail("Não deve chegar Aki");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        Task task = new Task();
        task.setTask("documentação");
        try {
            controller.save(task);
            Assert.fail("Não deve chegar Aki");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }


    @Test
    public void naoDeveSalvarTarefaComDataPassada(){
        Task task = new Task();
        task.setTask("descrição");
        task.setDueDate(LocalDate.of(2020,1, 2));
        try {
            controller.save(task);
            Assert.fail("Não deve chegar Aki");
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }


    @Test
    public void salvaTarefa() throws ValidationException {
        Task task = new Task();
        task.setTask("documentação");
        task.setDueDate(LocalDate.of(2021,10,10));
        controller.save(task);
        Mockito.verify(taskRepo).save(task);
    }

}