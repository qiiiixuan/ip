# Jackie User Guide

Jackie is your best friend that keeps track of tasks for you!

---

## Adding tasks

Add tasks using the `todo`, `deadline`, and `event` keywords.

###

Example: `todo Task 1` adds the following task to the task list. 

```
[T][ ] Task 1
```

###

Example: `deadline Task 2 /by 2025-09-19` adds the following task to the task list.

```
[D][ ] Task 2 (by: 19 Sep 2025)
```

###

Example: `event Task 3 /from 2025-09-19 /to 2025-10-20` adds the following task to the task list.

```
[E][ ] Task 3 (from: 19 Sep 2025 to: 20 Oct 2025)
```

---

## Mark / Unmark

Keep track of tasks you have done or not done yet. 

###

Example: `mark 1` checks the first task of the task list. 

```
[T][X] Task 1
```

---

## Find

Search for tasks using keywords. 

###

Example: `find Task 2` returns the corresponding task.

```
[D][ ] Task 2 (by: 19 Sep 2025)
```

###

Example: `find Oct` returns the corresponding task.

```
[E][ ] Task 3 (from: 19 Sep 2025 to: 20 Oct 2025)
```

---

## Delete

Remove task from task list.

###

Example: `delete 2` removes the second task. 

---

## Sort

Sort task list according to date with `sort`. 

---

## List

View the entire task list with `list`.

---