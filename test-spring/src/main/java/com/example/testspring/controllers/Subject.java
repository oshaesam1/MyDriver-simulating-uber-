package com.example.testspring.controllers;

import com.example.testspring.model.DataSystem;

public interface Subject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notify(DataSystem dataSystem);
}

