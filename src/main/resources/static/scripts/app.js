const addForm = document.getElementById("addForm");
const taskInput = document.getElementById("taskInput");
const taskList = document.getElementById("taskList");
const emptyState = document.getElementById("emptyState");
const doneCountEl = document.getElementById("doneCount");
const totalCountEl = document.getElementById("totalCount");
const clearDoneBtn = document.getElementById("clearDoneBtn");

const STORAGE_KEY = "projectHuman.tasks";

let tasks = loadTasks();

render();

addForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const title = taskInput.value.trim();
    if (!title) return;

    const newTask = {
        id: crypto.randomUUID(),
        title,
        done: false,
        createdAt: Date.now(),
    };

    tasks.unshift(newTask);
    saveTasks();
    taskInput.value = "";
    render();
});

clearDoneBtn.addEventListener("click", () => {
    tasks = tasks.filter(t => !t.done);
    saveTasks();
    render();
});

function toggleDone(id) {
    tasks = tasks.map(t => t.id === id ? { ...t, done: !t.done } : t);
    saveTasks();
    render();
}

function removeTask(id) {
    tasks = tasks.filter(t => t.id !== id);
    saveTasks();
    render();
}

function render() {
    taskList.innerHTML = "";

    if (tasks.length === 0) {
        emptyState.style.display = "block";
    } else {
        emptyState.style.display = "none";
    }

    for (const t of tasks) {
        const li = document.createElement("li");
        li.className = "item";

        const left = document.createElement("div");
        left.className = "left";

        const check = document.createElement("button");
        check.type = "button";
        check.className = "check";
        check.setAttribute("data-done", String(t.done));
        check.setAttribute("aria-label", t.done ? "Oznacz jako niezrobione" : "Oznacz jako zrobione");
        check.addEventListener("click", () => toggleDone(t.id));

        const checkIcon = document.createElement("div");
        checkIcon.className = "checkIcon";
        check.appendChild(checkIcon);

        const title = document.createElement("div");
        title.className = "title" + (t.done ? " done" : "");
        title.textContent = t.title;

        left.appendChild(check);
        left.appendChild(title);

        const actions = document.createElement("div");
        actions.className = "actions";

        const del = document.createElement("button");
        del.type = "button";
        del.className = "iconBtn iconBtn--danger";
        del.title = "Usuń";
        del.setAttribute("aria-label", "Usuń task");
        del.textContent = "✕";
        del.addEventListener("click", () => removeTask(t.id));

        actions.appendChild(del);

        li.appendChild(left);
        li.appendChild(actions);

        taskList.appendChild(li);
    }

    const total = tasks.length;
    const done = tasks.filter(t => t.done).length;

    totalCountEl.textContent = String(total);
    doneCountEl.textContent = String(done);

    clearDoneBtn.disabled = done === 0;
    clearDoneBtn.style.opacity = done === 0 ? ".5" : "1";
    clearDoneBtn.style.cursor = done === 0 ? "not-allowed" : "pointer";
}

function saveTasks() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks));
}

function loadTasks() {
    try {
        const raw = localStorage.getItem(STORAGE_KEY);
        if (!raw) return [];
        const parsed = JSON.parse(raw);
        if (!Array.isArray(parsed)) return [];
        // proste “sanitize”
        return parsed
            .filter(x => x && typeof x.title === "string" && typeof x.done === "boolean" && typeof x.id === "string")
            .slice(0, 200);
    } catch {
        return [];
    }
}
