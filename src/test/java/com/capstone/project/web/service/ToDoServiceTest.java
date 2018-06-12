package com.capstone.project.web.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capstone.project.web.model.Todo;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToDoServiceTest {

	@InjectMocks
	TodoService toDoservice;
	List<Todo> list = null;

	@Before
	public void setUp()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		list = new ArrayList<Todo>(Arrays.asList(new Todo(1, "Devops", "Learn Devops", new Date(), false),
				new Todo(3, "jenkins", "Learn Jenkins", new Date(), false)));
		Field field = TodoService.class.getDeclaredField("todos");
		field.setAccessible(true);
		field.set(null, list);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void retrieveTodosTest() {
		List<Todo> list = toDoservice.retrieveTodos("Devops");
		Assert.assertEquals(1, list.size());

	}

	@Test
	public void retrieveTodoTest() {
		Todo toDo = toDoservice.retrieveTodo(1);
		Assert.assertEquals(1, toDo.getId());

	}

	@Test
	public void retrieveTodoFailTest() {
		Todo toDo = toDoservice.retrieveTodo(10);
		Assert.assertNull(toDo);

	}

	@Test
	public void updateTodoTest() {

		Todo toDo = list.get(0);
		toDo.setUser("jenkins1");
		toDoservice.updateTodo(toDo);
		Assert.assertEquals("jenkins1", toDo.getUser());

	}

	@Test
	public void deleteTodoTest() {
		toDoservice.deleteTodo(1);
		Assert.assertEquals(1, list.size());

	}

	@Test
	public void addTodoTest() {
		toDoservice.addTodo("Javass", "Learn Javas", new Date(), false);
		Assert.assertEquals(3, list.size());

	}

}
