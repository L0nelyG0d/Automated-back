package com.example.automated.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private TaskList list;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    private String dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}

 enum Priority {
    LOW, MEDIUM, HIGH
}