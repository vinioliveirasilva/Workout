package com.example.workout.workoutsScreen.domain.repository

import com.example.workout.exercises.domain.model.ExerciseDetails
import com.example.workout.workoutsScreen.data.source.WorkoutStorage
import com.example.workout.workoutsScreen.domain.model.Exercise
import com.example.workout.workoutsScreen.domain.model.Workout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class WorkoutRepository(
    private val storage: WorkoutStorage
) {
    fun getWorkouts(): Flow<List<Workout>> = storage.getWorkout()
    fun saveWorkouts(workouts: List<Workout>) = storage.saveWorkout(workouts)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun addWorkout(name: String, exercises: List<Exercise>) = storage.getWorkout().flatMapConcat { workouts ->
        val lastId = workouts.maxOfOrNull { it.id } ?: 0
        val newWorkouts = workouts.toMutableList()
        newWorkouts.add(
            Workout(
                id = lastId.inc(),
                icon = "fitness",
                name = name,
                exercises = exercises
            )
        )
        storage.saveWorkout(newWorkouts)
    }

    fun getExercises() = storage.getExercises()
    fun getExercises(id: Int) = storage.getWorkout().map { workouts ->
        workouts.firstOrNull { it.id == id }?.exercises ?: emptyList()
    }.map {
        it.map { exercise ->
            ExerciseDetails(
                id = exercise.id,
                name = exercise.name,
                repetitions = 0,
                restTime = 25,
                load =  10.0,
            )
        }
    }
}