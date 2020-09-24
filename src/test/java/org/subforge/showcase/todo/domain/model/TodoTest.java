/**
 * @package Quarkus-jqAssistant-Showcase
 *
 * @file Contrived tests with PBT
 * @copyright 2019 Christoph Kappel <unexist@subforge.org>
 * @version $Id$
 *
 * This program can be distributed under the terms of the GNU GPLv2.
 * See the file COPYING for details.
 **/

package org.subforge.showcase.todo.domain.model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.PropertyDefaults;
import net.jqwik.api.constraints.IntRange;
import org.assertj.core.api.Condition;
import org.subforge.showcase.todo.domain.model.todo.DueDate;
import org.subforge.showcase.todo.domain.model.todo.Todo;
import org.subforge.showcase.todo.domain.model.todo.TodoBase;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;

@PropertyDefaults(tries = 10)
public class TodoTest {
    private static final int UNIVERSITY_START_AS_UNIXTIME = 1601031600;

    @Property
    public void testCreateTodo(@ForAll String anyStr) {
        Todo todo = new Todo();

        todo.setTitle(anyStr);
        todo.setDescription(anyStr);

        assertThat(todo.getTitle()).isNotNull();
        assertThat(todo.getDescription()).isNotNull();
    }

    @Property
    public void testCreateTodoWithDate(@ForAll String anyStr,
                                       @ForAll @IntRange(min = TodoTest.UNIVERSITY_START_AS_UNIXTIME) int unixtime) {
        Todo todo = new Todo();

        todo.setTitle(anyStr);
        todo.setDescription(anyStr);

        DueDate dueDate = new DueDate();

        dueDate.setStart(LocalDate.now());
        dueDate.setDue(Instant.ofEpochMilli(unixtime * 1000L)
                .atZone(ZoneId.systemDefault()).toLocalDate());

        todo.setDueDate(dueDate);

        /* Arbitrary and contrived test */
        Condition<Todo> cond1 = new Condition<>(t ->
                t.getDueDate().getStart().isBefore(t.getDueDate().getDue()),
                "Start date is b efore due date");
        Condition<Todo> cond2 = new Condition<>(TodoBase::getDone,
                "Todo must not be done");

        assertThat(todo).is(allOf(cond1, cond2));
    }
}
