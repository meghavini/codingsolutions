// File: CSharp/TodoApp.cs
using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;

class Todo {
    public int Id { get; set; }
    public string Task { get; set; }
    public bool Done { get; set; }
}

class Program {
    const string FILE = "todos.json";
    static List<Todo> todos = new List<Todo>();

    static void Main() {
        Load();
        Console.WriteLine("1-Add 2-List 3-Toggle 4-Exit");
        while (true) {
            var k = Console.ReadLine();
            if (k == "1") Add();
            else if (k == "2") List();
            else if (k == "3") Toggle();
            else if (k == "4") { Save(); break; }
        }
    }

    static void Load() {
        if (File.Exists(FILE)) {
            var json = File.ReadAllText(FILE);
            todos = JsonSerializer.Deserialize<List<Todo>>(json) ?? new List<Todo>();
        }
    }

    static void Save() {
        var json = JsonSerializer.Serialize(todos, new JsonSerializerOptions{WriteIndented=true});
        File.WriteAllText(FILE, json);
    }

    static void Add() {
        Console.Write("Task: ");
        var task = Console.ReadLine();
        int id = todos.Count == 0 ? 1 : todos[^1].Id + 1;
        todos.Add(new Todo{Id = id, Task = task, Done = false});
        Save();
        Console.WriteLine("Added.");
    }

    static void List() {
        foreach (var t in todos) Console.WriteLine($"{t.Id}. [{(t.Done ? 'x' : ' ')}] {t.Task}");
    }

    static void Toggle() {
        Console.Write("ID: ");
        if (int.TryParse(Console.ReadLine(), out int id)) {
            var t = todos.Find(x => x.Id == id);
            if (t != null) { t.Done = !t.Done; Save(); Console.WriteLine("Toggled."); }
            else Console.WriteLine("Not found.");
        }
    }
}
