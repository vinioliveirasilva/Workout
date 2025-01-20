package com.example.workout.exercises.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.exercises.domain.model.ExerciseDetails
import com.example.workout.workoutsScreen.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExerciseViewModel(
    private val id: Int,
    private val workoutRepository: WorkoutRepository
): ViewModel() {
    private val _state = MutableStateFlow(emptyList<ExerciseDetails>())
    val state = _state.asStateFlow()

    fun doOnStart() {
        viewModelScope.launch {
            workoutRepository.getExercises(id).collect { result ->
                _state.update { result }
            }
        }
    }

    fun handleAction(action: ExerciseAction) = when(action) {
        ExerciseAction.CancelEdit -> {}
        ExerciseAction.EndEdit -> {}
        ExerciseAction.StartEdit -> {}
    }
}